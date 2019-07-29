package com.web;

import com.domain.Student;
import com.domain.Subject;
import com.domain.Task;
import com.google.gson.Gson;
import com.service.UserService;
import com.serviceImpl.UserServiceImpl;
import com.utils.BaseServlet;
import com.utils.PageUtils;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class UserServlet extends BaseServlet {
	//学生登录
	public String login(HttpServletRequest request, HttpServletResponse response){
		// TODO Auto-generated method stub
		try {
			String username = request.getParameter("username");
			String password = request.getParameter("password");
			Student student = new Student();
			student.setUsername(username);
			student.setPassword(password);
			UserService userService=new UserServiceImpl();
			Student stu=userService.login(student);
			if(stu!=null){
				HttpSession session = request.getSession();
				//通过subjectId查询数据库，获取subjectId对应的专业名
				String subjectName=userService.find_subjectById(stu.getSubjectId());
				stu.setSubjectName(subjectName);
				session.setAttribute("student", stu);
				return "/index.jsp";
			}else {
				request.setAttribute("msg","用户或者密码错误！！");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "/login.jsp";
	}

	//查询所有的专业
	public String find_subject(HttpServletRequest request, HttpServletResponse response){
		try {
			UserService userService=new UserServiceImpl();
			List<Subject> subjects=userService.find_subject();
			request.setAttribute("subjects",subjects);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "/register_user.jsp";
	}
	
	//学生注册
	public String register(HttpServletRequest request, HttpServletResponse response){
		String path=new String();
		try {
			Student student = new Student();
			student.setStuName(request.getParameter("stuName"));
			student.setGender(request.getParameter("gender"));
			student.setAge(Integer.parseInt(request.getParameter("age")));
			student.setUsername(request.getParameter("username"));
			student.setPassword(request.getParameter("password"));
			student.setStuNum(request.getParameter("stuNum"));
			student.setTeamName(request.getParameter("teamName"));
			student.setSubjectId(Integer.parseInt(request.getParameter("subjectId")));
			UserService userService=new UserServiceImpl();
			Student stu=userService.find(student);
			if(stu==null) {
				userService.registerStu(student);
				response.sendRedirect("/student_sys/login.jsp");
			}else {
				request.setAttribute("msg","失败,该用户已存在");
				path="/register_user.jsp";
			}
		} catch (Exception e) {
			// TODO: handle exception
			request.setAttribute("msg", "出错了....");
			path="/error.jsp";
			e.printStackTrace();
		}
		return path;
	}
	
	//用户注销
	public String invalidate(HttpServletRequest request, HttpServletResponse response){
		request.getSession().removeAttribute("student");
		return "/login.jsp";
	}
	
	//修改密码
	public String edit_pwd(HttpServletRequest request, HttpServletResponse response){
		try {
			
			String old_pwd = request.getParameter("old_pwd");
			String new_pwd = request.getParameter("new_pwd");
			String username = request.getParameter("username");
			Student student = new Student();
			student.setUsername(username);
			student.setPassword(old_pwd);
			UserService userService=new UserServiceImpl();
			//查找数据库，判断该用户名密码是否正确
			Student stu = userService.find(student);
			if(stu!=null) {
				stu.setPassword(new_pwd);
				userService.update_pwd(stu);
				response.getWriter().println(true);
			}else {
				response.getWriter().println(false);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	//编辑功能
	public String edit(HttpServletRequest request, HttpServletResponse response){
		try {
			UserService userService=new UserServiceImpl();
			List<Subject> subjects = userService.find_subject();
			request.setAttribute("subjects", subjects);			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "/edit_user.jsp";
	}
	
	//编辑确认
	public String edit_sure(HttpServletRequest request, HttpServletResponse response){
		try {
			
			Student student = new Student();
			student.setId(Integer.parseInt(request.getParameter("id")));
			student.setStuName(request.getParameter("stuName"));
			student.setGender(request.getParameter("gender"));
			student.setAge(Integer.parseInt(request.getParameter("age")));
			student.setUsername(request.getParameter("username"));
			student.setStuNum(request.getParameter("stuNum"));
			student.setTeamName(request.getParameter("teamName"));
			student.setSubjectId(Integer.parseInt(request.getParameter("subjectId")));
			UserService userService=new UserServiceImpl();
			
			//更新
			userService.update(student);
			//根据id查找该学生
			Student stu=userService.findStudent(student.getId());
			//通过专业id查找出专业名
			stu.setSubjectName(userService.find_subjectById(stu.getSubjectId()));
			request.getSession().setAttribute("student", stu);
			return "/info.jsp";
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		request.setAttribute("msg","编辑用户信息失败...");
		return "/error.jsp";
	}
	
	//学生查看自己对应的作业信息
	public String showtask(HttpServletRequest request, HttpServletResponse response){
		try {
			//接受分页参数
			int page = Integer.parseInt(request.getParameter("page"));
			int rows = Integer.parseInt(request.getParameter("rows"));
			
			int userId = Integer.parseInt(request.getParameter("userId"));
			UserService userService=new UserServiceImpl();
			PageUtils<Task> tasks=userService.findTask(page,rows,userId);
			
			Gson gson = new Gson();
			String json = gson.toJson(tasks);
			response.getWriter().println(json);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return null;
	}
}
