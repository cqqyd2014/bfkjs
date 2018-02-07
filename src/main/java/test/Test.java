package test;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.cqqyd2014.service.inter.SysUserService;
import com.cqqyd2014.util.exception.ServcieException;

public class Test {
	 public static void main(String[] args) {
		  ClassPathXmlApplicationContext ctx=new ClassPathXmlApplicationContext("applicationContext-db.xml");
		  SysUserService mp=(SysUserService) ctx.getBean("sysUserService");
		  try {
			System.out.println(mp.checkLoginName("wam", "dfdf", "CQQY").getLogin_name());
		} catch (ServcieException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 
	  }


}
