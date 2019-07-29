package com.web;

import java.util.Date;
import java.util.List;
import java.util.Random;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.domain.Attendance;
import com.domain.Course;
import com.domain.Sign;
import com.domain.Student;
import com.domain.Teacher;
import com.google.gson.Gson;
import com.service.AttendService;
import com.serviceImpl.AttendServiceImp;
import com.utils.BaseServlet;
import com.utils.PageUtils;
import com.utils.StatusName;

@WebServlet("/attend")
public class AttendServlet extends BaseServlet {
	//进行该教师课程的回显
	public String showCourse(HttpServletRequest request, HttpServletResponse response){
		try {
			//获取教师名(直接从session域中获取)
			Teacher teacher=(Teacher)request.getSession().getAttribute("teacher");
			String teachName = teacher.getTeachName();
			AttendService attendService=new AttendServiceImp();
			List<Course> courses=attendService.findCourseByTeach(teachName);
			request.setAttribute("courses",courses);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "attend_teach.jsp";
	}
	
	//返回该课程对应的班级
	public String showTeam(HttpServletRequest request, HttpServletResponse response){
		try {
			//获取课程名
			String courseName = request.getParameter("courseName");
			AttendService attendService=new AttendServiceImp();
			List<Student> teamlist=attendService.findTeamByCourse(courseName);
			request.setAttribute("teamlist", teamlist);
			request.setAttribute("courseValue", courseName);
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return "/attend?method=showCourse";
	}
	
	//获取考勤码
	public String getKqm(HttpServletRequest request, HttpServletResponse response){
		try {
			HttpSession session = request.getSession();
			//将 课程名 和 班级名 教师名 存放到session域中
			String courseName = request.getParameter("courseName");
			String teamName = request.getParameter("teamName");
			session.setAttribute("courseName", courseName);
			session.setAttribute("teamName", teamName);
			//从session域中获取 登录教师的信息
			Teacher teacher =(Teacher)session.getAttribute("teacher");
			//这里，存教师名，是用于学生签到
			session.setAttribute("teachName",teacher.getTeachName());
			
			//如果从前台获取的课程名和班级名不为空，则把该班级下的所有学生先存进签到表(sign表)，默认初始值为缺勤
			if(courseName!=null&&teamName!=null) {
				//查询出该班级的所有学生
				AttendService attendService=new AttendServiceImp();
				List<Student> students=attendService.findStuByTeam(teamName);
				//遍历插入该班级的没一名学生
				for (Student student : students) {
					Sign sign = new Sign();
					sign.setClassName(teamName);
					sign.setCourseName(courseName);
					sign.setTeachName(teacher.getTeachName());
					sign.setStuName(student.getStuName());
					sign.setStatus(StatusName.status_two);
					sign.setStuNum(student.getStuNum());
					//设置开始时间
					Date date = new Date();
					session.setAttribute("startTime", date);
					sign.setStartTime(date);
					attendService.insertSign(sign);
				}
			}
			
			StringBuilder sb = new StringBuilder();
			for(int i=0;i<4;i++) {
				sb.append(new Random().nextInt(9));
			}
			session.setAttribute("num",sb.toString());
			response.getWriter().println(sb.toString());
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return null;
	}
	
	//展示考勤信息（教师）
	public String showAttend(HttpServletRequest request, HttpServletResponse response){
		try {
			//获取分页的请求参数
			int page = Integer.parseInt(request.getParameter("page"));
			int rows = Integer.parseInt(request.getParameter("rows"));
			String teachName = request.getParameter("teachName");
			HttpSession session = request.getSession();
			String courseName = (String)session.getAttribute("courseName");
			String teamName = (String)session.getAttribute("teamName");
			AttendService attendService=new AttendServiceImp();
			PageUtils<Attendance> attendances=null;
			attendances=attendService.findAllAttend(page,rows,teachName);
			Gson gson = new Gson();
			//转换为json格式，发送给前端
			String json = gson.toJson(attendances);
			response.getWriter().println(json);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		return null;
	}
	
	
	//销毁session中的考勤码，并且向attend表插入统计的数据
	public String endKq(HttpServletRequest request, HttpServletResponse response){
		try {
			HttpSession session = request.getSession();
			//销毁考勤码
			session.removeAttribute("num");
			//向attendance表中插入这一次考勤的信息
			AttendService attendService=new AttendServiceImp();
			//获取这一次考勤的开始时间
			Date startTime = (Date)session.getAttribute("startTime");
			//获取这一次考勤的总人数
			Integer sumNum=attendService.findsumNum(startTime);
			//获取这一次考勤的出勤人数
			Integer attendNum =attendService.findAttendNum(startTime,StatusName.status_one);
			//获取这一次考勤的缺勤人数
			Integer missNum=sumNum-attendNum;
			
			String className = (String)session.getAttribute("teamName");
			String courseName = (String)session.getAttribute("courseName");
			String teachName = (String)session.getAttribute("teachName");
			
			//获取缺课学生姓名
			List<String> stuNums=attendService.findMissName(startTime,StatusName.status_two);
			
			//遍历这些缺勤的学生，进行字符串的拼接
			StringBuffer sb = new StringBuffer();
			for(int i=0;i<stuNums.size();i++) {
				if(i!=stuNums.size()-1) {
					sb.append(stuNums.get(i)+",");
				}
			}
			String missName=sb.toString();
			//进行一次考勤的封装
			Attendance attendance = new Attendance();
			attendance.setAttendNum(attendNum);
			attendance.setClassName(className);
			attendance.setCourseName(courseName);
			attendance.setDate(startTime);
			attendance.setMissName(missName);
			attendance.setMissNum(missNum);
			attendance.setSumNum(sumNum);
			attendance.setTeachName(teachName);
			attendService.insetAttendance(attendance);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return null;
	}
	
	//学生点击签到 签到情况展示
	public String stuSign(HttpServletRequest request, HttpServletResponse response){
		try {
			
			//学生先进行签到(先获取考勤开始的时间)
			HttpSession session = request.getSession();
			Date startTime = (Date)session.getAttribute("startTime");
			//获取学生学号
			String stuNum = request.getParameter("stuNum");
			AttendService attendService=new AttendServiceImp();
			attendService.stuSign(stuNum,startTime,StatusName.status_one,new Date());
			
			//放前端发送展示数据
			//获取分页参数
			int page = Integer.parseInt(request.getParameter("page"));
			int rows = Integer.parseInt(request.getParameter("rows"));
			String courseName = (String)session.getAttribute("courseName");
			String teachName = (String)session.getAttribute("teachName");
			PageUtils<Sign> signs=attendService.showSign(page,rows,stuNum,courseName,teachName);
			Gson gson = new Gson();
			String json = gson.toJson(signs);
			response.getWriter().println(json);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return null;
	}
	
	//展示具体一次考勤的详细信息
	public String showAttend_xq(HttpServletRequest request, HttpServletResponse response){
		try {
			//获取这一考勤的id
			String attendId = request.getParameter("attendId");
			//获取分页参数
			int page = Integer.parseInt(request.getParameter("page"));
			int rows = Integer.parseInt(request.getParameter("rows"));
			AttendService attendService=new AttendServiceImp();
			PageUtils<Sign> signs=attendService.showAttend_xq(page,rows,attendId);
			Gson gson = new Gson();
			String json = gson.toJson(signs);
			response.getWriter().print(json);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return null;
	}
}
