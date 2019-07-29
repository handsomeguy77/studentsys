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

	//进行添加数据的回显，跳转到course.jsp页面
	public String show(HttpServletRequest request, HttpServletResponse response){
		
		try {
			//查询出所有的学科
			UserService userService = new UserServiceImpl();
			List<Subject> subjects = userService.find_subject();
			request.setAttribute("subjects", subjects);
			//查询出所有的学期
			List<String> terms = TermName.getTerm();
			request.setAttribute("terms", terms);
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return "/course.jsp";
	}
	
	//课程展示
	public String showCourse(HttpServletRequest request, HttpServletResponse response){
		try {
			//获取分页的请求参数
			int page = Integer.parseInt(request.getParameter("page"));
			int rows = Integer.parseInt(request.getParameter("rows"));
			CourseService courseService= new CourseServiceImpl();
			PageUtils<Course> pageData=courseService.find(page,rows);
			Gson gson = new Gson();
			//转换成json数据
			String json = gson.toJson(pageData);
			
			response.getWriter().println(json);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return null;
	}
	
	//课程添加
	public String add(HttpServletRequest request, HttpServletResponse response){
		try {
			Course course = new Course();
			course.setClassName(request.getParameter("className"));
			course.setSubjectId(Integer.parseInt(request.getParameter("subjectId")));
			course.setTerm(request.getParameter("term"));
			
			//调用service层
			CourseService courseService= new CourseServiceImpl();
			courseService.add(course);
			
		} catch (Exception e) {
			// TODO: handle exception
			e.getMessage();
		}
		
		request.setAttribute("msg", "添加成功");
		return "/course?method=show";
	}
	
	//学生课程的查询
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
