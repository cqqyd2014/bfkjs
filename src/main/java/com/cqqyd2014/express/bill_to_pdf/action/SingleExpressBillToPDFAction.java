package com.cqqyd2014.express.bill_to_pdf.action;

import java.util.ArrayList;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.hibernate.Session;

import com.cqqyd2014.express.common.ExpressBillModel;
import com.cqqyd2014.express.sf.BillSf;
@ParentPackage("struts-default")
@Namespace("/express")

@Results({@Result(name = "success", type = "stream", params = {  
        "contentType", "application/pdf",  
        "inputName", "inputStream", "contentDisposition",  
        "attachment;filename=\"${file_name}\"", "bufferSize",  
        "4096" })})  
public class SingleExpressBillToPDFAction extends ExpressBillToPDFAction{

	@Override
	public ArrayList<ExpressBillModel> initializeBeanArray(Session session, String order_no, String seq) {
		// TODO Auto-generated method stub
		java.util.ArrayList<ExpressBillModel> list=new java.util.ArrayList<>();
		if (express_bill_templet.equals("sf")){
			BillSf bill_sf=new BillSf();
			list.add(bill_sf);
		}
		
		return list;
	}

	@Action(value="single_express_bill_print", results = { @Result(name = "success", type = "stream", params = {  
	        "contentType", "application/pdf",  
	        "inputName", "inputStream", "contentDisposition",  
	        "attachment;filename=\"${file_name}\"", "bufferSize",  
	        "4096"  }) })  
	public String single_express_bill_print() throws Exception {
		// TODO Auto-generated method stub
		return SUCCESS; 
	}

}
