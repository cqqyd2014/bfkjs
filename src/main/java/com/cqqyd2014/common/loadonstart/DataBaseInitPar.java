package com.cqqyd2014.common.loadonstart;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;

import org.hibernate.HibernateException;
import org.hibernate.Session;

import com.cqqyd2014.hibernate.HibernateSessionFactory;

@WebServlet(name="database_init_par",urlPatterns = "/database_init_par",loadOnStartup=1)
@SuppressWarnings("serial")
public class DataBaseInitPar extends HttpServlet{

	@Override
	public void init(ServletConfig config) throws ServletException {
		// TODO Auto-generated method stub
		
		Session session = HibernateSessionFactory.getSession();
		try{
			
			 //取得Application对象   
	        ServletContext application=config.getServletContext();
	         
			java.util.ArrayList<com.cqqyd2014.hibernate.entities.SysPar> sps=com.cqqyd2014.hibernate.dao.SysParDAO.getArrayListEntites(session);
			for (int i=0,len=sps.size();i<len;i++){
				com.cqqyd2014.hibernate.entities.SysPar sp=sps.get(i);
				application.setAttribute(sp.getCode(), sp.getValue());
			}
	}

	catch (HibernateException e) {

		System.out.println("启动顺序1错误："+e.toString());

	} 
	
	
	
	finally {
		HibernateSessionFactory.closeSession();
	}
		
		
	}

}