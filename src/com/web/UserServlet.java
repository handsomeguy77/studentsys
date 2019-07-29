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
	//ѧ����¼
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
				//ͨ��subjectId��ѯ���ݿ⣬��ȡsubjectId��Ӧ��רҵ��
				String subjectName=userService.find_subjectById(stu.getSubjectId());
				stu.setSubjectName(subjectName);
				session.setAttribute("student", stu);
				return "/index.jsp";
			}else {
				request.setAttribute("msg","�û�����������󣡣�");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "/login.jsp";
	}

	//��ѯ���е�רҵ
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
	
	//ѧ��ע��
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
				request.setAttribute("msg","ʧ��,���û��Ѵ���");
				path="/register_user.jsp";
			}
		} catch (Exception e) {
			// TODO: handle exception
			request.setAttribute("msg", "������....");
			path="/error.jsp";
			e.printStackTrace();
		}
		return path;
	}
	
	//�û�ע��
	public String invalidate(HttpServletRequest request, HttpServletResponse response){
		request.getSession().removeAttribute("student");
		return "/login.jsp";
	}
	
	//�޸�����
	public String edit_pwd(HttpServletRequest request, HttpServletResponse response){
		try {
			
			String old_pwd = request.getParameter("old_pwd");
			String new_pwd = request.getParameter("new_pwd");
			String username = request.getParameter("username");
			Student student = new Student();
			student.setUsername(username);
			student.setPassword(old_pwd);
			UserService userService=new UserServiceImpl();
			//�������ݿ⣬�жϸ��û��������Ƿ���ȷ
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
	
	//�༭����
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
	
	//�༭ȷ��
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
			
			//����
			userService.update(student);
			//����id���Ҹ�ѧ��
			Student stu=userService.findStudent(student.getId());
			//ͨ��רҵid���ҳ�רҵ��
			stu.setSubjectName(userService.find_subjectById(stu.getSubjectId()));
			request.getSession().setAttribute("student", stu);
			return "/info.jsp";
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		request.setAttribute("msg","�༭�û���Ϣʧ��...");
		return "/error.jsp";
	}
	
	//ѧ���鿴�Լ���Ӧ����ҵ��Ϣ
	public String showtask(HttpServletRequest request, HttpServletResponse response){
		try {
			//���ܷ�ҳ����
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
