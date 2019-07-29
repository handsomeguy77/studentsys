package com.web;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.domain.Course;
import com.domain.Student;
import com.domain.Task;
import com.google.gson.Gson;
import com.service.CourseService;
import com.service.TaskService;
import com.serviceImpl.CourseServiceImpl;
import com.serviceImpl.TaskServiceImpl;
import com.utils.BaseServlet;
import com.utils.PageUtils;

@WebServlet("/task")
public class TaskServlet extends BaseServlet {
	//������menu��ҵ��ӵ�ʱ��ʵ�ָý�ʦ�Ŀγ̻���
	public String add_task(HttpServletRequest request, HttpServletResponse response){
		
		try {
			CourseService courseService=new CourseServiceImpl();
			List<Course> courses = courseService.findAllCourse();
			request.setAttribute("courses", courses);
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return "/task_teach_add.jsp";
	}
	
	//�����ҵ������
	public String add_task_save(HttpServletRequest request, HttpServletResponse response){
		try {
			//��ȡǰ̨���������
			Task task = new Task();
			int className = Integer.parseInt(request.getParameter("className"));
			task.setClassName(className);
			task.setTaskName(request.getParameter("taskName"));
			task.setTeachId(Integer.parseInt(request.getParameter("teachId")));
			
			task.setStartDate(new SimpleDateFormat("yyyy-MM-dd").parse(request.getParameter("startDate")));
			task.setStopDate(new SimpleDateFormat("yyyy-MM-dd").parse(request.getParameter("stopDate")));
			//���ò�����ҵ��ʱ��
			task.setDate(new Date());
			
			TaskService taskService=new TaskServiceImpl();
			//����ֵ�Ǹղ������ݵ�idֵ
			int taskId = taskService.addTask(task);
			
			//��ӵ���һ����ҵ ������ѡ�޸��ſγ̵�ͬѧ ����
			//�Ȳ�ѯ�����ſγ̶�Ӧ������ѡ��ѧ��
			List<Student> students=taskService.findAllStu(className);
			//�������������ݵ�task_stu�м����
			for (Student student : students) {
				taskService.insertTask_stu(student.getId(),taskId);
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return "/task_teach_show.jsp";
	}
	
	//չʾ�ý�ʦ��������ҵ
	public String showtask(HttpServletRequest request, HttpServletResponse response){
		try {
			//��ȡ�������
			int page = Integer.parseInt(request.getParameter("page"));
			int rows = Integer.parseInt(request.getParameter("rows"));
			//��ȡ�ý�ʦ��id
			int teachId = Integer.parseInt(request.getParameter("teachId"));
			TaskService taskService=new TaskServiceImpl();
			PageUtils<Task> tasks=taskService.findAllTask(page,rows,teachId);
			
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
