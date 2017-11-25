package com.cqqyd2014.quota.logic;

import org.hibernate.Session;

import com.cqqyd2014.util.message.IfMessage;

public class QuotaTransLogic {
	
	public synchronized static com.cqqyd2014.util.message.IfMessage changeQuota(Session session,String com_id,String userid,String op_userid,String type_id,java.math.BigDecimal amount,String memo,String orderno,String wronguuid){
		IfMessage im=new IfMessage();
		//得到当前的备用金及冻结情况
		
		com.cqqyd2014.hibernate.dao.SysUserDAO sudao=new com.cqqyd2014.hibernate.dao.SysUserDAO();
		com.cqqyd2014.hibernate.entities.SysUser sysuser=sudao.getUserFromUserid(session, userid, com_id);
		java.math.BigDecimal begin_amount=sysuser.getQuotaCurrent();
		java.math.BigDecimal begin_freez_amount=sysuser.getQuotaFreez();
		java.math.BigDecimal add_amount=sysuser.getQuotaAdd();
		java.math.BigDecimal substract_amount=sysuser.getQuotaSubtract();
		com.cqqyd2014.hibernate.entities.QuotaTrans qt=new com.cqqyd2014.hibernate.entities.QuotaTrans();
		com.cqqyd2014.hibernate.entities.QuotaTransId qtid=new com.cqqyd2014.hibernate.entities.QuotaTransId();
		qtid.setComId(com_id);
		String uuid=com.cqqyd2014.util.StringUtil.getUUID();
		qtid.setUuid(uuid);
		qt.setId(qtid);
		qt.setBeginAmount(begin_amount);
		qt.setBeginFreezAmount(begin_freez_amount);
		qt.setMemo(memo);
		qt.setOpTime(new java.util.Date());
		qt.setOpUserid(op_userid);
		qt.setUserid(userid);
		qt.setTransType(type_id);
		qt.setOrderno(orderno);
		qt.setWrongUuid(wronguuid);
		qt.setStatus("正常记录");
		switch(type_id){
        default:
            System.out.println("打印默认值");
            break;
        case "0001":
        	//普通充值
            qt.setEndAmount(begin_amount.add(amount));
            
            sysuser.setQuotaAdd(add_amount.add(amount));
            qt.setEndFreezAmount(begin_freez_amount);

            qt.setTransAmount(amount);
            qt.setTransFreezAmount(new java.math.BigDecimal("0"));

            break;
        case "0002":
        	//订单冻结
        	//如果余额不够，不能冻结，返回失败
        	if(begin_amount.compareTo(amount)==-1){
        		im.setIf_ok(false);;
        		im.setMessage("当前余额为"+begin_amount+"，订单需要冻结"+amount+"，冻结失败，不能付款");
        		return im;
        	}
            qt.setEndAmount(begin_amount.subtract(amount));
            
            qt.setEndFreezAmount(begin_freez_amount.add(amount));
            qt.setTransAmount(amount.multiply(new java.math.BigDecimal("-1")));
            qt.setTransFreezAmount(amount);
            break;
        case "0003":
        	//退单解冻
        	
            qt.setEndAmount(begin_amount.add(amount));
            qt.setEndFreezAmount(begin_freez_amount.subtract(amount));
            qt.setTransAmount(amount);
            qt.setTransFreezAmount(amount.multiply(new java.math.BigDecimal("-1")));
            break;
        case "0004":
        	//解冻扣费，发货
        	qt.setEndAmount(begin_amount);
        	qt.setEndFreezAmount(begin_freez_amount.subtract(amount));
        	qt.setTransAmount(new java.math.BigDecimal("0"));
            qt.setTransFreezAmount(amount.multiply(new java.math.BigDecimal("-1")));
            break;
        case "0005":
        	//退单退费,其实与充值是一样的
        	 qt.setEndAmount(begin_amount.add(amount));
             
             sysuser.setQuotaAdd(add_amount.add(add_amount));
             qt.setEndFreezAmount(begin_freez_amount);

             qt.setTransAmount(amount);
             qt.setTransFreezAmount(new java.math.BigDecimal("0"));
            break;
        case "0006":
        	//扣手续费
        	 qt.setEndAmount(begin_amount.subtract(amount));
             
             
             qt.setEndFreezAmount(begin_freez_amount);

             qt.setTransAmount(amount.multiply(new java.math.BigDecimal("-1")));
             qt.setTransFreezAmount(new java.math.BigDecimal("0"));
            break;
        case "0007":
        	//错充冲销
        	 qt.setEndAmount(begin_amount.subtract(amount));
        	 sysuser.setQuotaAdd(add_amount.subtract(amount));
             
             qt.setEndFreezAmount(begin_freez_amount);

             qt.setTransAmount(amount.multiply(new java.math.BigDecimal("-1")));
             qt.setTransFreezAmount(new java.math.BigDecimal("0"));
            break;
        
    }
		sysuser.setQuotaCurrent(qt.getEndAmount());
		sysuser.setQuotaFreez(qt.getEndFreezAmount());

		sysuser.setQuotaSubtract(sysuser.getQuotaAdd().subtract(qt.getEndFreezAmount()).subtract(qt.getEndAmount()));
		session.save(qt);
		session.saveOrUpdate(sysuser);
		im.setIf_ok(true);
		im.setMessage(uuid);
		return im;
		
	}
	
	public com.cqqyd2014.quota.model.QuotaTrans transFromEnties(com.cqqyd2014.hibernate.entities.VQuotaTrans qt_h){
		com.cqqyd2014.quota.model.QuotaTrans qt=new com.cqqyd2014.quota.model.QuotaTrans();
		qt.setBegin_amount(qt_h.getId().getBeginAmount());
		qt.setBegin_freez_amount(qt_h.getId().getBeginFreezAmount());
		qt.setCom_id(qt_h.getId().getComId());
		qt.setEnd_amount(qt_h.getId().getEndAmount());
		qt.setEnd_freez_amount(qt_h.getId().getEndFreezAmount());
		qt.setMemo(qt_h.getId().getMemo());
		qt.setOp_time(qt_h.getId().getOpTime());
		qt.setOrder_no(qt_h.getId().getOrderno());
		qt.setTrans_amount(qt_h.getId().getTransAmount());
		qt.setTrans_freez_aount(qt_h.getId().getTransFreezAmount());
		qt.setTrans_type(qt_h.getId().getTransType());
		qt.setTrans_type_name(qt_h.getId().getTransTypeName());
		qt.setOp_user_id(qt_h.getId().getOpUserid());
		qt.setOp_user_name(qt_h.getId().getOpUserName());
		qt.setUuid(qt_h.getId().getUuid());
		qt.setWrong_uuid(qt_h.getId().getWrongUuid());
		qt.setStatus(qt_h.getId().getStatus());
		return qt;
	}
	public java.util.ArrayList<com.cqqyd2014.quota.model.QuotaTrans> transFromArrayEnties(java.util.ArrayList<com.cqqyd2014.hibernate.entities.VQuotaTrans> vqts){
		java.util.ArrayList<com.cqqyd2014.quota.model.QuotaTrans> qts=new java.util.ArrayList<>();
		for (int i=0;i<vqts.size();i++){
			qts.add(transFromEnties(vqts.get(i)));
		}
		return qts;
	}

}
