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
	//点击左边menu作业添加的时候，实现该教师的课程回显
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
	
	//添加作业，保存
	public String add_task_save(HttpServletRequest request, HttpServletResponse response){
		try {
			//获取前台的请求参数
			Task task = new Task();
			int className = Integer.parseInt(request.getParameter("className"));
			task.setClassName(className);
			task.setTaskName(request.getParameter("taskName"));
			task.setTeachId(Integer.parseInt(request.getParameter("teachId")));
			
			task.setStartDate(new SimpleDateFormat("yyyy-MM-dd").parse(request.getParameter("startDate")));
			task.setStopDate(new SimpleDateFormat("yyyy-MM-dd").parse(request.getParameter("stopDate")));
			//设置布置作业的时间
			task.setDate(new Date());
			
			TaskService taskService=new TaskServiceImpl();
			//返回值是刚插入数据的id值
			int taskId = taskService.addTask(task);
			
			//添加的这一门作业 向所有选修该门课程的同学 发布
			//先查询出该门课程对应的所有选修学生
			List<Student> students=taskService.findAllStu(className);
			//遍历，插入数据到task_stu中间表中
			for (Student student : students) {
				taskService.insertTask_stu(student.getId(),taskId);
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return "/task_teach_show.jsp";
	}
	
	//展示该教师的所有作业
	public String showtask(HttpServletRequest request, HttpServletResponse response){
		try {
			//获取请求参数
			int page = Integer.parseInt(request.getParameter("page"));
			int rows = Integer.parseInt(request.getParameter("rows"));
			//获取该教师的id
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
