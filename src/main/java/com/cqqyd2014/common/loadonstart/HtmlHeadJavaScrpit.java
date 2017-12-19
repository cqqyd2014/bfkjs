package com.cqqyd2014.common.loadonstart;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;

import org.hibernate.HibernateException;
import org.hibernate.Session;

import com.cqqyd2014.hibernate.HibernateSessionFactory;



@WebServlet(name="html_head_javaspcrit",urlPatterns = "/html_head_javaspcrit",loadOnStartup=1)
@SuppressWarnings("serial")
public class HtmlHeadJavaScrpit extends HttpServlet{

	@Override
	public void init(ServletConfig config) throws ServletException {
		// TODO Auto-generated method stub
		
		Session session = HibernateSessionFactory.getSession();
		try{
			
			 //取得Application对象   
	        ServletContext application=config.getServletContext();
	         
			String jquery_version=com.cqqyd2014.hibernate.dao.SysParDAO.getStringValueByCode(session, "jquery_ver");
			 //设置Application属性   
	        application.setAttribute("jquery_version", jquery_version); 
			String easyui_version=com.cqqyd2014.hibernate.dao.SysParDAO.getStringValueByCode(session, "easyui_ver");
			application.setAttribute("easyui_version", easyui_version);
			String temp_save_time=com.cqqyd2014.hibernate.dao.SysParDAO.getStringValueByCode(session, "temp_save_time");
			application.setAttribute("temp_save_time", temp_save_time);
			String interval_time=com.cqqyd2014.hibernate.dao.SysParDAO.getStringValueByCode(session, "interval_time");
			application.setAttribute("interval_time", interval_time);
			//System.out.println("启动基本参数成功");
			String jquery_ui_version=com.cqqyd2014.hibernate.dao.SysParDAO.getStringValueByCode(session, "jquery_ui_ver");
			application.setAttribute("jquery_ui_version", jquery_ui_version);
	}

	catch (HibernateException e) {

		System.out.println("启动顺序1错误："+e.toString());

	} 
	
	
	
	finally {
		HibernateSessionFactory.closeSession();
	}
		
		
	}

}
