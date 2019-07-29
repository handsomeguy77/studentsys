package com.web;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.domain.Course;
import com.domain.Student;
import com.domain.Teacher;
import com.google.gson.Gson;
import com.service.CourseService;
import com.service.TeachService;
import com.service.UserService;
import com.serviceImpl.CourseServiceImpl;
import com.serviceImpl.TeachServiceImpl;
import com.serviceImpl.UserServiceImpl;
import com.utils.BaseServlet;
import com.utils.JDBCUtils;
import com.utils.PageUtils;

@WebServlet("/teach")
public class TeachServlet extends BaseServlet {
	//老师登录
	public String login(HttpServletRequest request, HttpServletResponse response){
		try {
			
			Teacher teacher = new Teacher();
			teacher.setUsername(request.getParameter("username"));
			teacher.setPassword(request.getParameter("password"));
			TeachService teachService=new TeachServiceImpl();
			Teacher teach=teachService.login(teacher);
			if(teach!=null) {
				request.getSession().setAttribute("teacher",teach);
				return "/index_teach.jsp";
			}else {
				request.setAttribute("msg","用户名或者密码错误！！");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "/login_teach.jsp";
	}

	//老师注册
	public String register(HttpServletRequest request, HttpServletResponse response){
		String path=new String();
		try {
			Teacher teacher = new Teacher();
			teacher.setUsername(request.getParameter("username"));
			teacher.setPassword(request.getParameter("password"));
			
			String teachName = request.getParameter("teachName");
			String zw = request.getParameter("zw");
			String courseName = request.getParameter("courseName");
			String teamName = request.getParameter("teamName");
			teacher.setTeachName(teachName);
			teacher.setZw(zw);
			teacher.setCourseName(courseName);
			teacher.setTeamName(teamName);
			
			TeachService teachService=new TeachServiceImpl();
			Teacher teach=teachService.find(teacher);
			if(teach==null) {
				teachService.register(teacher);
				if(zw.equalsIgnoreCase("教师")) {
					//设置该门课程对应的指导教师
					CourseService courseService =new CourseServiceImpl();
					courseService.updateCourse(courseName,teachName);
				}
//				response.sendRedirect("/student_sys/login_teach.jsp");
				path="/login_teach.jsp";
			}else {
				request.setAttribute("msg", "失败，该教师已存在");
				path="/register_teach.jsp";
			}
		} catch (Exception e) {
			request.setAttribute("msg", "出错了....");
			path="/error.jsp";
			e.printStackTrace();
		}
		
		return path;
	}
	
	//用户注销
	public String invalidate(HttpServletRequest request, HttpServletResponse response){
		request.getSession().removeAttribute("teacher");
		return "/login_teach.jsp";
	}
	
	
	public String register_cli(HttpServletRequest request, HttpServletResponse response){
		try {
			//查询所有课程
			CourseService courseService =new CourseServiceImpl();
			List<Course> courses=courseService.findAllCourse();
			//查询所有存在的班级
			UserService userService =new UserServiceImpl();
			List<Student> students=userService.find_teamName();
			
			request.setAttribute("courses", courses);
			request.setAttribute("students", students);
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return "/register_teach.jsp";
	}
	
	//教师通过班级名称查询出所有的学生
	public String findByTeam(HttpServletRequest request, HttpServletResponse response){
		try {
			//获取请求参数
			int page = Integer.parseInt(request.getParameter("page"));
			int rows = Integer.parseInt(request.getParameter("rows"));
			String teamName = request.getParameter("teamName");
			TeachService teachService=new TeachServiceImpl();
			PageUtils<Student> pu=teachService.findByTeam(page,rows,teamName);
			Gson gson = new Gson();
			String json = gson.toJson(pu);
			response.getWriter().println(json);
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return null;
	}
	
	//查询出所有的课程，进行选课
	public String findCourse(HttpServletRequest request, HttpServletResponse response){
		try {
			CourseService courseService =new CourseServiceImpl();
			List<Course> courses = courseService.findAllCourse();
			request.setAttribute("courses", courses);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return "/course_teach_add.jsp";
	}
	
	//添加课程
	public String add_course(HttpServletRequest request, HttpServletResponse response){
		Connection connection=null;
		try {
			//接受前台参数
			String teacherId = request.getParameter("teacherId");
			String teacheName=request.getParameter("teacheName");
			String courseName = request.getParameter("courseName");
			
			//获取连接，进行事务的管理
			connection = JDBCUtils.getConnection();
			//获取ThreadLocal
			JDBCUtils.getThreadLocal().set(connection);
			//教师添加上选中的这一门课程（通过逗号来进行字符串的拼接）
			TeachService teachService=new TeachServiceImpl();
			//1.查询出该教师已有的所有课程
			Teacher teacher=teachService.findById(teacherId);
			StringBuffer sb_courseName= new StringBuffer(teacher.getCourseName());
			//2.进行拼接
			sb_courseName.append(","+courseName);
			//3.保存
			//3.1设置开启事务
			connection.setAutoCommit(false);
			teachService.addCourse(teacherId,sb_courseName);
			
			//课程选上该任课教师（通过逗号来进行字符串的拼接）
			CourseService courseService =new CourseServiceImpl();
			//1.查询出该课程对应的教师
			Course course=courseService.findByCourseName(courseName);
			StringBuffer sb_teacherName = new StringBuffer(course.getTeachName());
			//2.进行拼接
			sb_teacherName.append(","+teacheName);
			//3.保存
			courseService.addTeach(courseName,sb_teacherName);
			//3.1提交事务
			connection.commit();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}finally {
			try {
				//失败进行回滚
				connection.rollback();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return "/course_teach_show.jsp";
	}
	
	//该教师对应的所有课程展示
	public String showCourse_teach(HttpServletRequest request, HttpServletResponse response){
		try {
			
			//获取前台参数
			//分页参数
			int page = Integer.parseInt(request.getParameter("page"));
			int rows = Integer.parseInt(request.getParameter("rows"));
			String teachName = request.getParameter("teachName");
			TeachService teachService=new TeachServiceImpl();
			PageUtils<Course> courses=teachService.findAllCourseByTeachName(page,rows,teachName);
			Gson gson = new Gson();
			String json = gson.toJson(courses);
			response.getWriter().println(json);
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return null;
	}
}
