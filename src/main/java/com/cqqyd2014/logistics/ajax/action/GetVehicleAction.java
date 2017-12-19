package com.cqqyd2014.logistics.ajax.action;

import java.util.Map;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.InterceptorRef;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.hibernate.HibernateException;

import com.cqqyd2014.annotation.Authority;
import com.cqqyd2014.common.action.UserLoginedAction;
import com.cqqyd2014.hibernate.HibernateSessionFactory;

@ParentPackage("bfkjs-json-default")
@Namespace("/logistics")
@SuppressWarnings("serial")
public class GetVehicleAction extends UserLoginedAction {
	private Map<String, Object> msg;

	public Map<String, Object> getMsg() {
		return msg;
	}

	public void setMsg(Map<String, Object> msg) {
		this.msg = msg;
	}

	public String logistics_code;

	public String getLogistics_code() {
		return logistics_code;
	}

	public void setLogistics_code(String logistics_code) {
		this.logistics_code = logistics_code;
	}
	public boolean not_air;

	public boolean isNot_air() {
		return not_air;
	}

	public void setNot_air(boolean not_air) {
		this.not_air = not_air;
	}

	@Action(value = "get_vehicle", results = {
			@Result(type = "json", params = { "root", "msg" }) }, interceptorRefs = {

					@InterceptorRef("defaultStack"), @InterceptorRef("authorityInterceptor") })
	@Authority(module = "get_vehicle", privilege = "*", error_url = "authority_ajax_error")
	@Override
	public String execute() {
		// TODO Auto-generated method stub
		super.execute();
		sm.setAuth_success(true);
		session = HibernateSessionFactory.getSession();

		try {

			java.util.ArrayList<com.cqqyd2014.logistics.model.Vehicle> vs = com.cqqyd2014.logistics.logic.VehicleLogic
					.getArrayListModelFromArrayListView(
							com.cqqyd2014.hibernate.dao.LogisticsVehicleFeeDAO.getArrayListEntities(session, logistics_code,not_air,com_id));

			sm.setO(vs);
			sm.setSuccess(true);

		} catch (HibernateException e) {
			if (null != tx) {
				tx.rollback();// 撤销事务

			}
			System.out.println(e.getMessage());
			e.printStackTrace();

		}

		finally {
			HibernateSessionFactory.closeSession();
		}

		msg = sm.toMap();
		return "success";
	}

}