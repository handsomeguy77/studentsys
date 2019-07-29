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
	//���иý�ʦ�γ̵Ļ���
	public String showCourse(HttpServletRequest request, HttpServletResponse response){
		try {
			//��ȡ��ʦ��(ֱ�Ӵ�session���л�ȡ)
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
	
	//���ظÿγ̶�Ӧ�İ༶
	public String showTeam(HttpServletRequest request, HttpServletResponse response){
		try {
			//��ȡ�γ���
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
	
	//��ȡ������
	public String getKqm(HttpServletRequest request, HttpServletResponse response){
		try {
			HttpSession session = request.getSession();
			//�� �γ��� �� �༶�� ��ʦ�� ��ŵ�session����
			String courseName = request.getParameter("courseName");
			String teamName = request.getParameter("teamName");
			session.setAttribute("courseName", courseName);
			session.setAttribute("teamName", teamName);
			//��session���л�ȡ ��¼��ʦ����Ϣ
			Teacher teacher =(Teacher)session.getAttribute("teacher");
			//������ʦ����������ѧ��ǩ��
			session.setAttribute("teachName",teacher.getTeachName());
			
			//�����ǰ̨��ȡ�Ŀγ����Ͱ༶����Ϊ�գ���Ѹð༶�µ�����ѧ���ȴ��ǩ����(sign��)��Ĭ�ϳ�ʼֵΪȱ��
			if(courseName!=null&&teamName!=null) {
				//��ѯ���ð༶������ѧ��
				AttendService attendService=new AttendServiceImp();
				List<Student> students=attendService.findStuByTeam(teamName);
				//��������ð༶��ûһ��ѧ��
				for (Student student : students) {
					Sign sign = new Sign();
					sign.setClassName(teamName);
					sign.setCourseName(courseName);
					sign.setTeachName(teacher.getTeachName());
					sign.setStuName(student.getStuName());
					sign.setStatus(StatusName.status_two);
					sign.setStuNum(student.getStuNum());
					//���ÿ�ʼʱ��
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
	
	//չʾ������Ϣ����ʦ��
	public String showAttend(HttpServletRequest request, HttpServletResponse response){
		try {
			//��ȡ��ҳ���������
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
			//ת��Ϊjson��ʽ�����͸�ǰ��
			String json = gson.toJson(attendances);
			response.getWriter().println(json);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		return null;
	}
	
	
	//����session�еĿ����룬������attend�����ͳ�Ƶ�����
	public String endKq(HttpServletRequest request, HttpServletResponse response){
		try {
			HttpSession session = request.getSession();
			//���ٿ�����
			session.removeAttribute("num");
			//��attendance���в�����һ�ο��ڵ���Ϣ
			AttendService attendService=new AttendServiceImp();
			//��ȡ��һ�ο��ڵĿ�ʼʱ��
			Date startTime = (Date)session.getAttribute("startTime");
			//��ȡ��һ�ο��ڵ�������
			Integer sumNum=attendService.findsumNum(startTime);
			//��ȡ��һ�ο��ڵĳ�������
			Integer attendNum =attendService.findAttendNum(startTime,StatusName.status_one);
			//��ȡ��һ�ο��ڵ�ȱ������
			Integer missNum=sumNum-attendNum;
			
			String className = (String)session.getAttribute("teamName");
			String courseName = (String)session.getAttribute("courseName");
			String teachName = (String)session.getAttribute("teachName");
			
			//��ȡȱ��ѧ������
			List<String> stuNums=attendService.findMissName(startTime,StatusName.status_two);
			
			//������Щȱ�ڵ�ѧ���������ַ�����ƴ��
			StringBuffer sb = new StringBuffer();
			for(int i=0;i<stuNums.size();i++) {
				if(i!=stuNums.size()-1) {
					sb.append(stuNums.get(i)+",");
				}
			}
			String missName=sb.toString();
			//����һ�ο��ڵķ�װ
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
	
	//ѧ�����ǩ�� ǩ�����չʾ
	public String stuSign(HttpServletRequest request, HttpServletResponse response){
		try {
			
			//ѧ���Ƚ���ǩ��(�Ȼ�ȡ���ڿ�ʼ��ʱ��)
			HttpSession session = request.getSession();
			Date startTime = (Date)session.getAttribute("startTime");
			//��ȡѧ��ѧ��
			String stuNum = request.getParameter("stuNum");
			AttendService attendService=new AttendServiceImp();
			attendService.stuSign(stuNum,startTime,StatusName.status_one,new Date());
			
			//��ǰ�˷���չʾ����
			//��ȡ��ҳ����
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
	
	//չʾ����һ�ο��ڵ���ϸ��Ϣ
	public String showAttend_xq(HttpServletRequest request, HttpServletResponse response){
		try {
			//��ȡ��һ���ڵ�id
			String attendId = request.getParameter("attendId");
			//��ȡ��ҳ����
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
