package com.cqqyd2014.express.sf.bsp;



import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.hibernate.Session;



import com.cqqyd2014.express.sf.dom4j.common.Request;
import com.cqqyd2014.order.model.Order;
import com.cqqyd2014.util.HttpRequest;
import com.cqqyd2014.util.exception.HttpExcpetion;

public abstract class BspHttpClient {
	
	com.cqqyd2014.order.model.Order order;
	com.cqqyd2014.order.model.DeliverBill db;
	public com.cqqyd2014.order.model.DeliverBill getDb() {
		return db;
	}
	public void setDb(com.cqqyd2014.order.model.DeliverBill db) {
		this.db = db;
	}
	public com.cqqyd2014.order.model.Order getOrder() {
		return order;
	}
	public void setOrder(com.cqqyd2014.order.model.Order order) {
		this.order = order;
	}

	java.util.ArrayList<com.cqqyd2014.order.model.OrderFrom> ofs;
	
	public java.util.ArrayList<com.cqqyd2014.order.model.OrderFrom> getOfs() {
		return ofs;
	}
	public void setOfs(java.util.ArrayList<com.cqqyd2014.order.model.OrderFrom> ofs) {
		this.ofs = ofs;
	}

	com.cqqyd2014.order.model.OrderFrom of;
	public com.cqqyd2014.order.model.OrderFrom getOf() {
		return of;
	}
	public void setOf(com.cqqyd2014.order.model.OrderFrom of) {
		this.of = of;
	}
	String response_uuid;
	java.util.Date response_time;
	String service_name;
	public String getService_name() {
		return service_name;
	}
	public void setService_name(String service_name) {
		this.service_name = service_name;
	}
	public String getResponse_uuid() {
		return response_uuid;
	}
	public void setResponse_uuid(String response_uuid) {
		this.response_uuid = response_uuid;
	}
	public java.util.Date getResponse_time() {
		return response_time;
	}
	public void setResponse_time(java.util.Date response_time) {
		this.response_time = response_time;
	}
	java.util.ArrayList<Order> orders;
	java.util.ArrayList<com.cqqyd2014.order.model.DeliverBill> dbs;

	public java.util.ArrayList<com.cqqyd2014.order.model.DeliverBill> getDbs() {
		return dbs;
	}
	public void setDbs(java.util.ArrayList<com.cqqyd2014.order.model.DeliverBill> dbs) {
		this.dbs = dbs;
	}
	int orders_num;
	public int getOrders_num() {
		return orders_num;
	}
	public void setOrders_num(int orders_num) {
		this.orders_num = orders_num;
	}
	String xml;
	
	
	
	String verifyCode;
	/*
	String checkword;
	String accessCode;
	String post_addr;
	
	String month_pay_account;
	*/

	com.cqqyd2014.hibernate.entities.SfAccount sfa;


	Session session;

	public Session getSession() {
		return session;
	}
	public void setSession(Session session) {
		this.session = session;
	}
	public void beforeInitBill() throws BspException{
		response_uuid=com.cqqyd2014.util.StringUtil.getUUID();
		response_time=new java.util.Date();
		
		
		com.cqqyd2014.hibernate.dao.SfAccountDAO sfadao=new com.cqqyd2014.hibernate.dao.SfAccountDAO();
		
		if (orders!=null) {
			orders_num=10;
			if (orders.size()>1){
				if (orders_num<orders.size()){
					throw new BspException("","批量处理的订单数量为"+orders.size()+"，大于限制"+orders_num);
				}
			}
			setSfa(sfadao.getObjectByComId(session, orders.get(0).getCom_id()));
		}
		else {
			setSfa(sfadao.getObjectByComId(session, order.getCom_id()));
		}
		
		
		/*
		accessCode=sfa.getAccessCode();
		checkword=sfa.getCheckword();
		month_pay_account=sfa.getSfMonthPayAccount();
		post_addr=sfa.getPostAddr();
		*/
		
	}
	
	public java.util.ArrayList<Order> getOrders() {
		return orders;
	}
	public void setOrders(java.util.ArrayList<Order> orders) {
		this.orders = orders;
	}
	public abstract String initBill() ;
	
	public String  afterInitBill(Request r) {
		r.setHead(this.getSfa().getAccessCode());
		  xml= r.make_xml();
		return xml;
	}
	
	


	public com.cqqyd2014.hibernate.entities.SfAccount getSfa() {
		return sfa;
	}


	public void setSfa(com.cqqyd2014.hibernate.entities.SfAccount sfa) {
		this.sfa = sfa;
	}



	public  BspHttpClient(){
		
	}

	
	public BspHttpClient(Session session,java.util.ArrayList<com.cqqyd2014.order.model.DeliverBill> dbs
			) throws BspException {
		java.util.ArrayList<com.cqqyd2014.order.model.Order> orders=new java.util.ArrayList<>();
		java.util.ArrayList<com.cqqyd2014.order.model.OrderFrom> ofs=new java.util.ArrayList<>();
		this.setDbs(dbs);
		setSession(session);
		com.cqqyd2014.hibernate.dao.VOrderFromDAO vfdao=new com.cqqyd2014.hibernate.dao.VOrderFromDAO();
		com.cqqyd2014.hibernate.dao.VOrderMainDAO vodao=new com.cqqyd2014.hibernate.dao.VOrderMainDAO();
		com.cqqyd2014.hibernate.dao.VOrderDetailDAO voddao=new com.cqqyd2014.hibernate.dao.VOrderDetailDAO();
		
		
		
		for (int i=0;i<dbs.size();i++) {
			com.cqqyd2014.order.model.DeliverBill db=dbs.get(i);
			com.cqqyd2014.hibernate.entities.VOrderMain vom=vodao.getVOrderMain(session, db.getCom_id(), db.getOrder_no());
			java.util.ArrayList<com.cqqyd2014.hibernate.entities.VOrderDetail> vods=voddao.getArrayListViewsByOrderNo(session, db.getCom_id(), db.getOrder_no());
			
			com.cqqyd2014.order.model.Order order=com.cqqyd2014.order.logic.OrderLogic.getOrderModelFromHiberanteEntities(vom,vods);
			orders.add(order);
			String order_from=dbs.get(i).getOrder_no().substring(3, 5);
			com.cqqyd2014.hibernate.entities.VOrderFrom vof=vfdao.getViewByComIdType(session, db.getCom_id(), order_from);
			com.cqqyd2014.order.model.OrderFrom of=com.cqqyd2014.order.logic.OrderFromLogic.getModelFromEntityView(vof);
			ofs.add(of);
		}
		this.setOrders(orders);
		this.setOfs(ofs);
		beforeInitBill();

		
	}
	public BspHttpClient(Session session,com.cqqyd2014.order.model.DeliverBill db
			) throws BspException {
		com.cqqyd2014.hibernate.dao.VOrderFromDAO vfdao=new com.cqqyd2014.hibernate.dao.VOrderFromDAO();
		com.cqqyd2014.hibernate.dao.VOrderMainDAO vodao=new com.cqqyd2014.hibernate.dao.VOrderMainDAO();
		com.cqqyd2014.hibernate.dao.VOrderDetailDAO voddao=new com.cqqyd2014.hibernate.dao.VOrderDetailDAO();
		
		
		com.cqqyd2014.hibernate.entities.VOrderMain vom=vodao.getVOrderMain(session, db.getCom_id(), db.getOrder_no());
		java.util.ArrayList<com.cqqyd2014.hibernate.entities.VOrderDetail> vods=voddao.getArrayListViewsByOrderNo(session, db.getCom_id(), db.getOrder_no());
		com.cqqyd2014.order.model.Order order=com.cqqyd2014.order.logic.OrderLogic.getOrderModelFromHiberanteEntities(vom,vods);
		setOrder(order);
		this.setDb(db);
		setSession(session);
		String order_from=order.getOrder_no().substring(3, 5);
		com.cqqyd2014.hibernate.entities.VOrderFrom vof=vfdao.getViewByComIdType(session, db.getCom_id(), order_from);
		com.cqqyd2014.order.model.OrderFrom of=com.cqqyd2014.order.logic.OrderFromLogic.getModelFromEntityView(vof);
		this.setOf(of);
		beforeInitBill();

		
	}
	

	public Object post(String xml) throws Exception{

		setXml(xml);
		return post();
	}

	public Object post() throws BspException{
		Object o=null;
		java.util.LinkedHashMap<String, String > map=new java.util.LinkedHashMap<>();
		//pars.add(new BasicNameValuePair("xml", xml));
		map.put("xml", xml);
		System.out.println(xml);
		//System.out.println(checkword);
		makeVerifyCode(xml,this.getSfa().getCheckword());
		map.put("verifyCode", verifyCode);
		//pars.add(new BasicNameValuePair("verifyCode", verifyCode));
		//System.out.println(verifyCode);
		String rs="";
		boolean http_sucess_flag=true;
		try {
			rs = HttpRequest.sendPost(this.getSfa().getPostAddr(), map);
		} catch (HttpExcpetion e1) {
			// TODO Auto-generated catch block
			//http传输数据出错
			http_sucess_flag=false;
			com.cqqyd2014.hibernate.entities.HttpErrorLog hel=new com.cqqyd2014.hibernate.entities.HttpErrorLog();
			com.cqqyd2014.hibernate.entities.HttpErrorLogId heli=new com.cqqyd2014.hibernate.entities.HttpErrorLogId(orders.get(0).getCom_id(), com.cqqyd2014.util.StringUtil.getUUID());
			hel.setId(heli);
			hel.setCode(e1.getCode());
			hel.setHttpUrl(e1.getUrl());
			hel.setMessage(e1.getMessage());
			hel.setOpTime(new java.util.Date());
			getSession().save(hel);
			
		}
		System.out.println("顺丰POST返回数据："+rs);
		String error_code="";
		String error_message="";
		if (http_sucess_flag){
			
		
		try{
			o=processPostBack(rs);
			if (o==null) {
				//返回数据为空
				return null;
			}
			com.cqqyd2014.hibernate.entities.SfResponseBack sb=new com.cqqyd2014.hibernate.entities.SfResponseBack();
			com.cqqyd2014.hibernate.entities.SfResponseBackId sbid=new com.cqqyd2014.hibernate.entities.SfResponseBackId();
			if (orders!=null) {
				sbid.setComId(orders.get(0).getCom_id());
			}
			else {
				sbid.setComId(order.getCom_id());
			}
			
			sbid.setUuid(response_uuid);
			sb.setServiceName(service_name);
			sb.setId(sbid);
			sb.setCustId(sfa.getSfMonthPayAccount());
			sb.setHead(this.getSfa().getAccessCode());
			sb.setOpTime(response_time);
			sb.setResponseText(rs);
			getSession().save(sb);
		}
		catch (BspException e){
			
			error_code=e.code;
			error_message=e.message;
			
			
			//对于顺丰返回错误代码的处理
			/*
			if (error_code.equals("8016")){
				//orderid已经存在重复下单，查询orderid得到运单号
				BspHttpClientOrderSearch bhcos=new BspHttpClientOrderSearch(session, orders);
				bhcos.setSeqs(seqs);
				
				bhcos.initBill();
				bhcos.post();
				session.flush();
				
			}
			*/
		}
		}
		com.cqqyd2014.hibernate.entities.SfPostLog fpl=new com.cqqyd2014.hibernate.entities.SfPostLog();
		com.cqqyd2014.hibernate.entities.SfPostLogId fpli=new com.cqqyd2014.hibernate.entities.SfPostLogId();
		fpl.setCustId(sfa.getSfMonthPayAccount());
		fpl.setErrorCode(error_code);
		fpl.setErrorMessage(error_message);
		if (orders!=null) {
			fpli.setComId(orders.get(0).getCom_id());
		}
		else {
			fpli.setComId(order.getCom_id());
		}
		
		fpli.setUuid(response_uuid);
		fpl.setServiceName(service_name);
		fpl.setId(fpli);
		fpl.setOpTime(response_time);
		fpl.setPostText(xml);
		getSession().save(fpl);
		
		return o;
		
		
	}

	public abstract Object afterProcessPostBack(Element body) throws BspException;

	public  Object processPostBack(String post_back) throws BspException{

		Document document=null;
		try {
			document = DocumentHelper.parseText(post_back);
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("不能解析xml"+post_back);
		}  
		
		Element root = document.getRootElement();  
		
		//String service=root.attribute("service").getText();
		String head=root.element("Head").getText();
		
	
         
         
        if (head.equals("OK")){
        	//System.out.println(res.getBody());
        	return afterProcessPostBack(root.element("Body"));
        	
        	
        }
        if (head.equals("ERR")){
        	Element error=root.element("ERROR");
        	System.out.println("提交数据顺丰BSP系统返回错误！错误代码:+"+error.attribute("code").getText()+"，错误信息："+error.getText());
        	throw new BspException(error.attribute("code").getText(),error.getText());
        }
        
        
        
        return null;
	}


	public String getXml() {
		return xml;
	}
	public void setXml(String xml) {
		this.xml = xml;
	}

	public String makeVerifyCode(String xml,String checkword){
		String tmp=xml+checkword;
		this.verifyCode=com.cqqyd2014.util.StringUtil.md5EncryptAndBase64(tmp);
		return verifyCode;
		
	}
	public void setDecodeURIXml(String xml) throws UnsupportedEncodingException{
		setXml(URLDecoder.decode(xml, "UTF-8"));
	}

}
