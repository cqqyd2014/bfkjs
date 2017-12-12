package com.cqqyd2014.usergroup.logic;

public class UserPriceLogic {
	
	public java.util.ArrayList<com.cqqyd2014.usergroup.model.UserPrice> getArrayModelFromArrayEntities(java.util.ArrayList<com.cqqyd2014.hibernate.entities.VUserPriceAvailable> vupas,java.util.Date now) throws Exception{
		java.util.ArrayList<com.cqqyd2014.usergroup.model.UserPrice> ups=new java.util.ArrayList<>();
		for (int i=0;i<vupas.size();i++) {
			com.cqqyd2014.usergroup.model.UserPrice up=getModelFromEntityView(vupas.get(i),now);
			ups.add(up);
		}
		return ups;
		
	}
	public com.cqqyd2014.usergroup.model.UserPrice getModelFromEntityView(com.cqqyd2014.hibernate.entities.VUserPriceAvailable vupa,java.util.Date now) throws Exception{
		com.cqqyd2014.usergroup.model.UserPrice up=new com.cqqyd2014.usergroup.model.UserPrice();
		up.setYue(vupa.getId().getSumAvailable());
		up.setCom_id(vupa.getId().getComId());
		up.setEffective(vupa.getId().getEffective());
		up.setEnd_time(vupa.getId().getEndTime());
		up.setGoods_id(vupa.getId().getGoodsId());
		up.setPrice(vupa.getId().getUserPrice());
		up.setStart_time(vupa.getId().getStartTime());
		up.setUuid(vupa.getId().getUserpriceUuid());
		up.setGoods_name(vupa.getId().getCName());
		up.setUserid(vupa.getId().getUserId());
		up.setUnit(vupa.getId().getUnit());
		if (com.cqqyd2014.util.DateUtil.getDistanceSecends(now, vupa.getId().getEndTime())>0){
			//过期时间比当前时间更早，价格已经过期
			up.setEffective_now(false);
			up.setIn_the_futurn(false);
			up.setOut_date(true);
		}
		if (com.cqqyd2014.util.DateUtil.getDistanceSecends(vupa.getId().getEndTime(), now)>0&&
				com.cqqyd2014.util.DateUtil.getDistanceSecends(now, vupa.getId().getStartTime())>0){
			
			//当前有效的价格
			up.setEffective_now(true);
			up.setIn_the_futurn(false);
			up.setOut_date(false);
		}
		if (com.cqqyd2014.util.DateUtil.getDistanceSecends(vupa.getId().getStartTime(), now)>0){
			//未来的价格
			up.setEffective_now(false);
			up.setIn_the_futurn(true);
			up.setOut_date(false);
		}
		return up;
	}
	
	
	public java.util.ArrayList<com.cqqyd2014.usergroup.model.UserPrice> getArrayModelFromArrayEnties(java.util.ArrayList<com.cqqyd2014.hibernate.entities.UserPrice> ups_h,java.util.Date now) throws Exception{
		java.util.ArrayList<com.cqqyd2014.usergroup.model.UserPrice> ups=new java.util.ArrayList<>();
		com.cqqyd2014.usergroup.logic.UserPriceLogic upl=new com.cqqyd2014.usergroup.logic.UserPriceLogic();
		for (int i=0;i<ups_h.size();i++){
			com.cqqyd2014.usergroup.model.UserPrice up=upl.getModelFromEnties(ups_h.get(i), now);
			ups.add(up);
			
		}
		return ups;
	}
	public java.util.ArrayList<com.cqqyd2014.usergroup.model.UserPrice> getArrayModelFromArrayEntiesV(java.util.ArrayList<com.cqqyd2014.hibernate.entities.VUserPrice> ups_h,java.util.Date now) throws Exception{
		java.util.ArrayList<com.cqqyd2014.usergroup.model.UserPrice> ups=new java.util.ArrayList<>();
		com.cqqyd2014.usergroup.logic.UserPriceLogic upl=new com.cqqyd2014.usergroup.logic.UserPriceLogic();
		for (int i=0;i<ups_h.size();i++){
			com.cqqyd2014.usergroup.model.UserPrice up=upl.getModelFromEnties(ups_h.get(i), now);
			ups.add(up);
			
		}
		return ups;
	}
	
	public com.cqqyd2014.usergroup.model.UserPrice getModelFromEnties(com.cqqyd2014.hibernate.entities.VUserPrice up_h,java.util.Date now) throws Exception{
		com.cqqyd2014.usergroup.model.UserPrice up=new com.cqqyd2014.usergroup.model.UserPrice();
		up.setCom_id(up_h.getId().getComId());
		up.setEnd_time(up_h.getId().getEndTime());
		if (com.cqqyd2014.util.DateUtil.getDistanceSecends(now, up_h.getId().getEndTime())>0){
			//过期时间比当前时间更早，价格已经过期
			up.setEffective_now(false);
			up.setIn_the_futurn(false);
			up.setOut_date(true);
		}
		if (com.cqqyd2014.util.DateUtil.getDistanceSecends(up_h.getId().getEndTime(), now)>0&&
				com.cqqyd2014.util.DateUtil.getDistanceSecends(now, up_h.getId().getStartTime())>0){
			
			//当前有效的价格
			up.setEffective_now(true);
			up.setIn_the_futurn(false);
			up.setOut_date(false);
		}
		if (com.cqqyd2014.util.DateUtil.getDistanceSecends(up_h.getId().getStartTime(), now)>0){
			//未来的价格
			up.setEffective_now(false);
			up.setIn_the_futurn(true);
			up.setOut_date(false);
		}
		up.setStart_time(up_h.getId().getStartTime());
		up.setGoods_id(up_h.getId().getGoodsId());
		up.setPrice(up_h.getId().getUserPrice());
		up.setUuid(up_h.getId().getUserpriceUuid());
		up.setGoods_name(up_h.getId().getCName());
		up.setUserid(up_h.getId().getUserId());
		up.setUnit(up_h.getId().getUnit());
		return up;
		
			
	}
	
	
	public com.cqqyd2014.usergroup.model.UserPrice getModelFromEnties(com.cqqyd2014.hibernate.entities.UserPrice up_h,java.util.Date now) throws Exception{
		com.cqqyd2014.usergroup.model.UserPrice up=new com.cqqyd2014.usergroup.model.UserPrice();
		up.setCom_id(up_h.getId().getComId());
		up.setEnd_time(up_h.getEndTime());
		if (com.cqqyd2014.util.DateUtil.getDistanceSecends(now, up_h.getEndTime())>0){
			//过期时间比当前时间更早，价格已经过期
			up.setEffective_now(false);
			up.setIn_the_futurn(false);
			up.setOut_date(true);
		}
		if (com.cqqyd2014.util.DateUtil.getDistanceSecends(up_h.getEndTime(), now)>0&&
				com.cqqyd2014.util.DateUtil.getDistanceSecends(now, up_h.getStartTime())>0){
			
			//当前有效的价格
			up.setEffective_now(true);
			up.setIn_the_futurn(false);
			up.setOut_date(false);
		}
		if (com.cqqyd2014.util.DateUtil.getDistanceSecends(up_h.getStartTime(), now)>0){
			//未来的价格
			up.setEffective_now(false);
			up.setIn_the_futurn(true);
			up.setOut_date(false);
		}
		up.setStart_time(up_h.getStartTime());
		up.setGoods_id(up_h.getGoodsId());
		up.setPrice(up_h.getUserPrice());
		up.setUuid(up_h.getId().getUuid());
		
		return up;
		
			
	}

}
