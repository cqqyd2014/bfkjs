package com.cqqyd2014.logistics.logic;

public final class VehicleLogic {
	public static com.cqqyd2014.logistics.model.Vehicle getModelFromView(com.cqqyd2014.hibernate.entities.VLogisticsVehicleFee lv){
		com.cqqyd2014.logistics.model.Vehicle v=new com.cqqyd2014.logistics.model.Vehicle();
		v.setVehicle_code(lv.getId().getVehicle());
		v.setVehicle_name(lv.getId().getVehicleName());
		v.setCom_id(lv.getId().getComId());
		v.setEffective(lv.getId().getEffective());
		v.setFirst_fee(lv.getId().getFirstFee());
		v.setLogistics_code(lv.getId().getLogistics());
		v.setLogistics_name(lv.getId().getLogisticsName());
		v.setNext_fee(lv.getId().getNextFee());
		v.setVechicle_order(lv.getId().getVehicleOrder());
		return v;
	}
	public static java.util.ArrayList<com.cqqyd2014.logistics.model.Vehicle> getArrayListModelFromArrayListView(java.util.ArrayList<com.cqqyd2014.hibernate.entities.VLogisticsVehicleFee> lvs_h){
		java.util.ArrayList<com.cqqyd2014.logistics.model.Vehicle> vs=new java.util.ArrayList<>();
		for (int i=0,j=lvs_h.size();i<j;i++){
			com.cqqyd2014.logistics.model.Vehicle v=getModelFromView(lvs_h.get(i));
			vs.add(v);
		}
		return vs;
	}

}
