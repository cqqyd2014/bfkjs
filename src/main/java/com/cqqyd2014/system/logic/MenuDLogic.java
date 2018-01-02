package com.cqqyd2014.system.logic;

public class MenuDLogic {
	
	public static com.cqqyd2014.system.model.MenuD getModelFromView1(com.cqqyd2014.hibernate.entities.VUserMenuD vumd){
		com.cqqyd2014.system.model.MenuD md=new com.cqqyd2014.system.model.MenuD();
		md.setM_d_id(vumd.getId().getMenuDId());
		md.setM_d_js_method(vumd.getId().getMenuDJsMethod());
		md.setMenu_d_js_url(vumd.getId().getMenuDJsUrl());
		md.setD_name(vumd.getId().getMenuDName());
		md.setM_id(vumd.getId().getMenuId());
		md.setUserid(vumd.getId().getUserId());
		md.setWeb_attention(vumd.getId().getWebAttention());
		md.setGet_num_class(vumd.getId().getGetNumClass());
		md.setGet_num_method(vumd.getId().getGetNumMethod());
		md.setWeb_attention_tips(vumd.getId().getWebAttentionTips());
		md.setWeb_url(vumd.getId().getMenuDJsUrl().substring(3));
		return md;
	}
	
	public static java.util.ArrayList<com.cqqyd2014.system.model.MenuD> getArrayListModelFromArrayListView(java.util.ArrayList<com.cqqyd2014.hibernate.entities.VUserMenuD> vumds){
		java.util.ArrayList<com.cqqyd2014.system.model.MenuD> mds=new java.util.ArrayList<>();
		for (int i=0;i<vumds.size();i++) {
			com.cqqyd2014.system.model.MenuD md=getModelFromView1(vumds.get(i));
			mds.add(md);
		}
		
		return mds;
	}
	

}
