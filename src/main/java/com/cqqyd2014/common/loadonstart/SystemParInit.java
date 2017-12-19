package com.cqqyd2014.common.loadonstart;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;

import org.hibernate.HibernateException;
import org.hibernate.Session;

import com.cqqyd2014.hibernate.HibernateSessionFactory;

@WebServlet(name="system_par_init",urlPatterns = "/system_par_init",loadOnStartup=2)
@SuppressWarnings("serial")
public class SystemParInit extends HttpServlet{

	@Override
	public void init(ServletConfig config) throws ServletException {
		// TODO Auto-generated method stub
		
		
		
			
			 //取得Application对象   
	        ServletContext application=config.getServletContext();
	         
			application.setAttribute("context_path", application.getContextPath());
	
		
		
	}

}