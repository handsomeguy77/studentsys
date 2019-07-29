package com.utils;

import java.io.IOException;
import java.lang.reflect.Method;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class BaseServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		//通过反射解决上面多个if else的问题
				try {
					Class clazz = this.getClass();
					//通过字节码对象获取到该类指定的方法
						//参数1：方法的名字
						//参数2：是方法的参数类型
					Method method = clazz.getMethod(request.getParameter("method"), HttpServletRequest.class,HttpServletResponse.class);
					//获取到方法执行
					String value = (String)method.invoke(clazz.newInstance(), request,response);
					if(value!=null){
						request.getRequestDispatcher(value).forward(request, response);
					}
				} catch (Exception e) {
					// TODO: handle exception
					e.printStackTrace();
				}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}