package com.cqqyd2014.system.logic;

public class MenuDLogic {
	
	public static com.cqqyd2014.system.model.MenuD getModelFromView1(com.cqqyd2014.hibernate.entities.VUserMenuD vumd){
		com.cqqyd2014.system.model.MenuD md=new com.cqqyd2014.system.model.MenuD();
		md.setM_d_id(vumd.getId().getMenuDId());
		md.setM_d_js_method(vumd.getId().getMenuDJsMethod());
		md.setM_d_js_url(vumd.getId().getMenuDJsUrl());
		md.setM_d_name(vumd.getId().getMenuDName());
		md.setM_id(vumd.getId().getMenuId());
		md.setUserid(vumd.getId().getUserId());
		return md;
	}
	
	public static java.util.ArrayList<com.cqqyd2014.system.model.MenuD> getArrayListModelFromView1(java.util.ArrayList<com.cqqyd2014.hibernate.entities.VUserMenuD> vumds){
		java.util.ArrayList<com.cqqyd2014.system.model.MenuD> mds=new java.util.ArrayList<>();
		for (int i=0;i<vumds.size();i++) {
			com.cqqyd2014.system.model.MenuD md=getModelFromView1(vumds.get(i));
			mds.add(md);
		}
		
		return mds;
	}
	

}
