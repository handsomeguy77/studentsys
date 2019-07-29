package com.web;

import java.util.List;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.domain.Course;
import com.domain.Subject;
import com.google.gson.Gson;
import com.service.CourseService;
import com.service.UserService;
import com.serviceImpl.CourseServiceImpl;
import com.serviceImpl.UserServiceImpl;
import com.utils.BaseServlet;
import com.utils.PageUtils;
import com.utils.TermName;

@WebServlet("/course")
public class CourseServlet extends BaseServlet {

	//����������ݵĻ��ԣ���ת��course.jspҳ��
	public String show(HttpServletRequest request, HttpServletResponse response){
		
		try {
			//��ѯ�����е�ѧ��
			UserService userService = new UserServiceImpl();
			List<Subject> subjects = userService.find_subject();
			request.setAttribute("subjects", subjects);
			//��ѯ�����е�ѧ��
			List<String> terms = TermName.getTerm();
			request.setAttribute("terms", terms);
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return "/course.jsp";
	}
	
	//�γ�չʾ
	public String showCourse(HttpServletRequest request, HttpServletResponse response){
		try {
			//��ȡ��ҳ���������
			int page = Integer.parseInt(request.getParameter("page"));
			int rows = Integer.parseInt(request.getParameter("rows"));
			CourseService courseService= new CourseServiceImpl();
			PageUtils<Course> pageData=courseService.find(page,rows);
			Gson gson = new Gson();
			//ת����json����
			String json = gson.toJson(pageData);
			
			response.getWriter().println(json);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return null;
	}
	
	//�γ����
	public String add(HttpServletRequest request, HttpServletResponse response){
		try {
			Course course = new Course();
			course.setClassName(request.getParameter("className"));
			course.setSubjectId(Integer.parseInt(request.getParameter("subjectId")));
			course.setTerm(request.getParameter("term"));
			
			//����service��
			CourseService courseService= new CourseServiceImpl();
			courseService.add(course);
			
		} catch (Exception e) {
			// TODO: handle exception
			e.getMessage();
		}
		
		request.setAttribute("msg", "��ӳɹ�");
		return "/course?method=show";
	}
	
	//ѧ���γ̵Ĳ�ѯ
	public String showCourse_user(HttpServletRequest request, HttpServletResponse response){
		try {
			int page = Integer.parseInt(request.getParameter("page"));
			int rows = Integer.parseInt(request.getParameter("rows"));
			int subjectId = Integer.parseInt(request.getParameter("subjectId"));
			
			CourseService courseService= new CourseServiceImpl();
			PageUtils<Course> pageData=courseService.find_user(page,rows,subjectId);
			Gson gson = new Gson();
			String json = gson.toJson(pageData);
			response.getWriter().println(json);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return null;
	}
	
}
