package com.cqqyd2014.finance.logic;

import org.hibernate.Session;

public class FinanceGoodsPriceLogic {
	
	public com.cqqyd2014.finance.model.FinanceGoodsPrice getModelFromView(com.cqqyd2014.hibernate.entities.VFinanceGoodsPrice fgp_h,java.util.Date now) throws Exception{
		com.cqqyd2014.finance.model.FinanceGoodsPrice fgp=new com.cqqyd2014.finance.model.FinanceGoodsPrice();
		fgp.setEnd_dat(fgp_h.getId().getEndDat());
		fgp.setGoods_id(fgp_h.getId().getCId());
		fgp.setGoods_name(fgp_h.getId().getCName());
		fgp.setPrice(fgp_h.getId().getPrice());
		fgp.setStart_dat(fgp_h.getId().getStartDat());
		fgp.setUnit(fgp_h.getId().getUnit());
		fgp.setCom_id(fgp_h.getId().getComId());
		if (com.cqqyd2014.util.DateUtil.getDistanceSecends(now, fgp_h.getId().getEndDat())>0){
			//过期时间比当前时间更早，价格已经过期
			fgp.setEffective_now(false);
			fgp.setIn_the_futurn(false);
			fgp.setOut_date(true);
		}
		if (com.cqqyd2014.util.DateUtil.getDistanceSecends(fgp_h.getId().getEndDat(), now)>0&&
				com.cqqyd2014.util.DateUtil.getDistanceSecends(now, fgp_h.getId().getStartDat())>0){
			
			//当前有效的价格
			fgp.setEffective_now(true);
			fgp.setIn_the_futurn(false);
			fgp.setOut_date(false);
		}
		if (com.cqqyd2014.util.DateUtil.getDistanceSecends(fgp_h.getId().getStartDat(), now)>0){
			//未来的价格
			fgp.setEffective_now(false);
			fgp.setIn_the_futurn(true);
			fgp.setOut_date(false);
		}
		return fgp;
	}
	
	
	public java.util.ArrayList<com.cqqyd2014.finance.model.FinanceGoodsPrice> getArrayListModelFromArrayListView(java.util.ArrayList<com.cqqyd2014.hibernate.entities.VFinanceGoodsPrice> vfgps_h,java.util.Date now) throws Exception{
		java.util.ArrayList<com.cqqyd2014.finance.model.FinanceGoodsPrice> fgps=new java.util.ArrayList<>();
		for (int i=0;i<vfgps_h.size();i++) {
			com.cqqyd2014.finance.model.FinanceGoodsPrice fgp=getModelFromView(vfgps_h.get(i),now);
			fgps.add(fgp);
		}
		return fgps;
	}
	
	public void save(Session session,com.cqqyd2014.finance.model.FinanceGoodsPrice fgp) {
		com.cqqyd2014.hibernate.entities.FinanceGoodsPrice fgp_h=new com.cqqyd2014.hibernate.entities.FinanceGoodsPrice();
		fgp_h.setEndDat(fgp.getEnd_dat());
		fgp_h.setGoodsId(fgp.getGoods_id());
		fgp_h.setId(new com.cqqyd2014.hibernate.entities.FinanceGoodsPriceId(fgp.getCom_id(), com.cqqyd2014.util.StringUtil.getUUID()));
		fgp_h.setPrice(fgp.getPrice());
		fgp_h.setStartDat(fgp.getStart_dat());
	}

}
