package com.filter;

import java.io.IOException;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

public class MyFilter implements Filter {

	@Override
	public void destroy() {
		// TODO Auto-generated method stub

	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
			throws IOException, ServletException {
		// TODO Auto-generated method stub
		
		final HttpServletRequest request=(HttpServletRequest)req;
		res.setCharacterEncoding("utf-8");
		
		HttpServletRequest strongRequest = (HttpServletRequest)Proxy.newProxyInstance(
				request.getClass().getClassLoader(),
				request.getClass().getInterfaces(),
				new InvocationHandler() {
					
					@Override
					public Object invoke(Object arg0, Method method, Object[] arg2) throws Throwable {
						// TODO Auto-generated method stub
						if("getParameter".equals(method.getName())){
							String m = request.getMethod();
							if("get".equalsIgnoreCase(m)){
								String value = (String)method.invoke(request, arg2);
								return new String(value.getBytes("iso-8859-1"),"utf-8");
							}
							if("post".equalsIgnoreCase(m)){
								request.setCharacterEncoding("utf-8");
								return method.invoke(request, arg2);
							}
						}
						return method.invoke(request, arg2);
					}
				});
		
		chain.doFilter(strongRequest, res);
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub

	}

}
