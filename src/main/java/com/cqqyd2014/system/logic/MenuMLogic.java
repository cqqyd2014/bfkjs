package com.cqqyd2014.system.logic;

public class MenuMLogic {
	
	public static com.cqqyd2014.system.model.MenuM getModelFromView(com.cqqyd2014.hibernate.entities.VUserMenuM vumm,java.util.ArrayList<com.cqqyd2014.hibernate.entities.VUserMenuD> vumds){
		com.cqqyd2014.system.model.MenuM mm=new com.cqqyd2014.system.model.MenuM();
		mm.setM_desc(vumm.getId().getDesc());
		mm.setM_id(vumm.getId().getMenuId());
		mm.setM_name(vumm.getId().getMenuName());
		mm.setUserid(vumm.getId().getUserId());
		mm.setMenu_d(com.cqqyd2014.system.logic.MenuDLogic.getArrayListModelFromView1(vumds));
		return mm;
	}
	public static com.cqqyd2014.system.model.MenuM getModelFromView(com.cqqyd2014.hibernate.entities.VUserMenuM vumm){
		com.cqqyd2014.system.model.MenuM mm=new com.cqqyd2014.system.model.MenuM();
		mm.setM_desc(vumm.getId().getDesc());
		mm.setM_id(vumm.getId().getMenuId());
		mm.setM_name(vumm.getId().getMenuName());
		mm.setUserid(vumm.getId().getUserId());
		
		return mm;
	}
	public static java.util.ArrayList<com.cqqyd2014.system.model.MenuM> getArrayListModelFromArrayListView(java.util.ArrayList<com.cqqyd2014.hibernate.entities.VUserMenuM> vumms){
		java.util.ArrayList<com.cqqyd2014.system.model.MenuM> mms=new java.util.ArrayList<>();
		for (int i=0;i<vumms.size();i++) {
			com.cqqyd2014.system.model.MenuM mm=getModelFromView(vumms.get(i));
			mms.add(mm);
		}
		return mms;
	}

}
