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

		//ͨ��������������if else������
				try {
					Class clazz = this.getClass();
					//ͨ���ֽ�������ȡ������ָ���ķ���
						//����1������������
						//����2���Ƿ����Ĳ�������
					Method method = clazz.getMethod(request.getParameter("method"), HttpServletRequest.class,HttpServletResponse.class);
					//��ȡ������ִ��
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