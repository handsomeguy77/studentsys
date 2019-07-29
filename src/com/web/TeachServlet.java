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
	//��ʦ��¼
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
				request.setAttribute("msg","�û�������������󣡣�");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "/login_teach.jsp";
	}

	//��ʦע��
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
				if(zw.equalsIgnoreCase("��ʦ")) {
					//���ø��ſγ̶�Ӧ��ָ����ʦ
					CourseService courseService =new CourseServiceImpl();
					courseService.updateCourse(courseName,teachName);
				}
//				response.sendRedirect("/student_sys/login_teach.jsp");
				path="/login_teach.jsp";
			}else {
				request.setAttribute("msg", "ʧ�ܣ��ý�ʦ�Ѵ���");
				path="/register_teach.jsp";
			}
		} catch (Exception e) {
			request.setAttribute("msg", "������....");
			path="/error.jsp";
			e.printStackTrace();
		}
		
		return path;
	}
	
	//�û�ע��
	public String invalidate(HttpServletRequest request, HttpServletResponse response){
		request.getSession().removeAttribute("teacher");
		return "/login_teach.jsp";
	}
	
	
	public String register_cli(HttpServletRequest request, HttpServletResponse response){
		try {
			//��ѯ���пγ�
			CourseService courseService =new CourseServiceImpl();
			List<Course> courses=courseService.findAllCourse();
			//��ѯ���д��ڵİ༶
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
	
	//��ʦͨ���༶���Ʋ�ѯ�����е�ѧ��
	public String findByTeam(HttpServletRequest request, HttpServletResponse response){
		try {
			//��ȡ�������
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
	
	//��ѯ�����еĿγ̣�����ѡ��
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
	
	//��ӿγ�
	public String add_course(HttpServletRequest request, HttpServletResponse response){
		Connection connection=null;
		try {
			//����ǰ̨����
			String teacherId = request.getParameter("teacherId");
			String teacheName=request.getParameter("teacheName");
			String courseName = request.getParameter("courseName");
			
			//��ȡ���ӣ���������Ĺ���
			connection = JDBCUtils.getConnection();
			//��ȡThreadLocal
			JDBCUtils.getThreadLocal().set(connection);
			//��ʦ�����ѡ�е���һ�ſγ̣�ͨ�������������ַ�����ƴ�ӣ�
			TeachService teachService=new TeachServiceImpl();
			//1.��ѯ���ý�ʦ���е����пγ�
			Teacher teacher=teachService.findById(teacherId);
			StringBuffer sb_courseName= new StringBuffer(teacher.getCourseName());
			//2.����ƴ��
			sb_courseName.append(","+courseName);
			//3.����
			//3.1���ÿ�������
			connection.setAutoCommit(false);
			teachService.addCourse(teacherId,sb_courseName);
			
			//�γ�ѡ�ϸ��ον�ʦ��ͨ�������������ַ�����ƴ�ӣ�
			CourseService courseService =new CourseServiceImpl();
			//1.��ѯ���ÿγ̶�Ӧ�Ľ�ʦ
			Course course=courseService.findByCourseName(courseName);
			StringBuffer sb_teacherName = new StringBuffer(course.getTeachName());
			//2.����ƴ��
			sb_teacherName.append(","+teacheName);
			//3.����
			courseService.addTeach(courseName,sb_teacherName);
			//3.1�ύ����
			connection.commit();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}finally {
			try {
				//ʧ�ܽ��лع�
				connection.rollback();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return "/course_teach_show.jsp";
	}
	
	//�ý�ʦ��Ӧ�����пγ�չʾ
	public String showCourse_teach(HttpServletRequest request, HttpServletResponse response){
		try {
			
			//��ȡǰ̨����
			//��ҳ����
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
