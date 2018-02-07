package com.cqqyd2014.service.impl;

import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.cqqyd2014.dao.inter.MenuDDAO;
import com.cqqyd2014.dao.inter.MenuMDAO;
import com.cqqyd2014.dao.inter.SysUserDAO;
import com.cqqyd2014.dao.inter.UserParDAO;
import com.cqqyd2014.service.inter.SysUserService;
import com.opensymphony.xwork2.ActionContext;
@Service("sysUserService")
public class SysUserServiceImpl implements SysUserService {
	
	
	SysUserDAO sysUserDAO;
	UserParDAO userParDAO;
	MenuDDAO menuDDAO;

	
	public MenuDDAO getMenuDDAO() {
		return menuDDAO;
	}
	@Resource(name="menuDDAO")
	public void setMenuDDAO(MenuDDAO menuDDAO) {
		this.menuDDAO = menuDDAO;
	}
	public UserParDAO getUserParDAO() {
		return userParDAO;
	}
	@Resource(name="userParDAO")
	public void setUserParDAO(UserParDAO userParDAO) {
		this.userParDAO = userParDAO;
	}
	public SysUserDAO getSysUserDAO() {
		return sysUserDAO;
	}
	@Resource(name="sysUserDAO")
	public void setSysUserDAO(SysUserDAO sysUserDAO) {
		this.sysUserDAO = sysUserDAO;
	}
	@Transactional(propagation = Propagation.REQUIRED,isolation=Isolation.READ_COMMITTED,rollbackFor=com.cqqyd2014.util.exception.ServcieException.class)
	@Override
	public com.cqqyd2014.system.model.SysUser checkLoginName(String login_name,String password,String com_id) throws com.cqqyd2014.util.exception.ServcieException{
		// TODO Auto-generated method stub
		com.cqqyd2014.system.model.SysUser su=sysUserDAO.getModelByLoginName( login_name, com_id);
		if(su==null){
			throw new com.cqqyd2014.util.exception.ServcieException("该用户不存在");
		}
		if(!su.getPassword_md5().equals(com.cqqyd2014.util.StringUtil.md5(password))){
			throw new com.cqqyd2014.util.exception.ServcieException("用户名密码错误");
		}
		Map<String,Object> session_http = ActionContext.getContext().getSession();
			
			// System.out.println("设置信息");
			session_http.put("user_name", su.getUser_name());
			session_http.put("user_login", su.getLogin_name());
			session_http.put("user_id", su.getUser_id());
			
			//设置用户参数
			java.util.List<com.cqqyd2014.system.model.UserPar> ups=
					userParDAO.getArrayListModelByUserId(su.getUser_id(), com_id);
					//com.cqqyd2014.usergroup.logic.UserParLogic.getArrayListModelEntityFromArrayListEntity(com.cqqyd2014.hibernate.dao.UserParDAO.getArrayListEntityByUserId(session, com_id, b.getId()));
			for (int i=0,len=ups.size();i<len;i++){
				com.cqqyd2014.system.model.UserPar up=ups.get(i);
				session_http.put(up.getPar_code(),up.getPar_value());
			}
			
			//设置权限
			
			java.util.List<com.cqqyd2014.system.model.MenuD> menuds=menuDDAO.getListModelByUserId(su.getUser_id(), com_id);
			java.util.ArrayList<String> menu_array=new java.util.ArrayList<String>();
			for (int i=0;i<menuds.size();i++) {
				menu_array.add(menuds.get(i).getM_id()+menuds.get(i).getM_d_id());
			}
			setOnline(su.getUser_id(), com_id);
			

			HttpServletRequest request = ServletActionContext.getRequest();
			String ip = com.cqqyd2014.util.IPUtil.getIpAddr(request);
			com.cqqyd2014.hibernate.dao.SysLogDAO sldao = new com.cqqyd2014.hibernate.dao.SysLogDAO();
			//sldao.saveLog(session, b.getId(), "登录系统,来自IP：" + ip, "1", com_id);
			
			
		
		
		return su;
	}
	@Override
	public void setOnline(String user_id, String com_id) {
		// TODO Auto-generated method stub
		
		sysUserDAO.setOnline(user_id,com_id);
		
	}

}
