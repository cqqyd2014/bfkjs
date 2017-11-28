package com.cqqyd2014.wh.logic;

import org.hibernate.Session;

public class GoodsInfoLogic {
	
	
	
	public static String getNewSnCode(Session session,String com_id){
		com.cqqyd2014.hibernate.dao.VSnCodeFlagDAO vsfdao=new com.cqqyd2014.hibernate.dao.VSnCodeFlagDAO();
		java.math.BigDecimal current=vsfdao.getCurrentFlag(session, com_id);
		java.math.BigDecimal new_flag=current.add(new java.math.BigDecimal("1"));
		String r=com.cqqyd2014.util.NumberUtil.numToStr0(new_flag.longValue(), 4);
		return r;
		
	}
	
	
	public static void save(Session session,com.cqqyd2014.wh.model.GoodsInfo gi) {
		com.cqqyd2014.hibernate.entities.GoodsInfo g=new com.cqqyd2014.hibernate.entities.GoodsInfo();
		g.setBarcode(gi.getBarcode());
		g.setCHs(gi.getHs_code());
		g.setCMemo(gi.getMemo());
		g.setCName(gi.getGoods_name());
		g.setCSpec(gi.getSpec());
		g.setGrossWeight(gi.getGross_weight());
		g.setId(new com.cqqyd2014.hibernate.entities.GoodsInfoId(gi.getGoods_id(), gi.getCom_id()));
		g.setInUse(gi.isIn_use());
		g.setNetWeight(gi.getNet_weight());
		g.setNotAir(gi.isNot_air());
		g.setOrigin(gi.getCountry_id());
		g.setPackageWeight(gi.getPackage_weight());
		g.setSnCode(gi.getSn_code());
		g.setUnit(gi.getUnit_code());
		g.setShortName(gi.getShort_name());
		
		session.saveOrUpdate(g);
	}
	
	public static java.util.HashMap<String, String> getHashMap(java.util.ArrayList<com.cqqyd2014.hibernate.entities.VGoodsInfo> vgis) throws Exception{
		java.util.ArrayList<com.cqqyd2014.wh.model.GoodsInfo> gis=getArrayModelFromArrayEntityV(vgis);
		return com.cqqyd2014.util.HashMapTools.convertArrayListToHashMap(gis, "getGoods_id", "getGoods_name");
	}

	
public static java.util.ArrayList<com.cqqyd2014.wh.model.GoodsInfo> getArrayModelFromArrayEntityV(java.util.ArrayList<com.cqqyd2014.hibernate.entities.VGoodsInfo> vgis){
	java.util.ArrayList<com.cqqyd2014.wh.model.GoodsInfo> gis=new java.util.ArrayList<>();
	for (int i=0;i<vgis.size();i++){
		gis.add(getModelFromView(vgis.get(i)));
		
	}
	return gis;
}

public static com.cqqyd2014.wh.model.GoodsInfo getModelFromView(com.cqqyd2014.hibernate.entities.VGoodsInfo vgi){
	com.cqqyd2014.wh.model.GoodsInfo g=new com.cqqyd2014.wh.model.GoodsInfo();
	g.setIn_use(vgi.getId().getInUse());
	g.setGoods_id(vgi.getId().getCId());
	g.setGoods_name(vgi.getId().getCName());
	g.setHs_code(vgi.getId().getCHs());
	g.setMemo(vgi.getId().getCMemo());
	g.setNet_weight(vgi.getId().getNetWeight());
	g.setNot_air(vgi.getId().getNotAir());
	g.setCountry_id(vgi.getId().getOrigin());
	g.setCountry_name(vgi.getId().getCCountry());
	g.setPackage_weight(vgi.getId().getPackageWeight());
	g.setSn_code(vgi.getId().getSnCode());
	g.setSpec(vgi.getId().getCSpec());
	g.setShort_name(vgi.getId().getShortName());
	g.setUnit_name(vgi.getId().getCUnit());
	g.setUnit_code(vgi.getId().getUnit());
	
	g.setGross_weight(vgi.getId().getGrossWeight());
	g.setCom_id(vgi.getId().getComId());
	
	return g;
}
}
