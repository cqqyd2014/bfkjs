package com.cqqyd2014.express.sf.servlet;

import java.io.IOException;
import java.net.URLDecoder;

import javax.servlet.ServletException;
import javax.servlet.ServletInputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name="ReceiveRoute",urlPatterns="/express/sf/receive_route")
public class ReceiveRouteServlet extends HttpServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//super.doPost(req, resp);
		System.out.println("servelt");
		ServletInputStream ris = req.getInputStream();
		StringBuilder content = new StringBuilder();  
	    byte[] b = new byte[1024];  
	    int lens = -1;  
	    while ((lens = ris.read(b)) > 0) {  
	        content.append(new String(b, 0, lens));  
	    }  
	    ris.close();
	    String strcont = URLDecoder.decode(content.toString(),"UTF-8");
	    
	    System.out.println(strcont);
	}

}
