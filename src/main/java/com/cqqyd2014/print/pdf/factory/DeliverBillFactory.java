package com.cqqyd2014.print.pdf.factory;

import java.util.ArrayList;



public class DeliverBillFactory {
	public static ArrayList<com.cqqyd2014.order.model.DeliverBill> getBeanCollection(){
        ArrayList<com.cqqyd2014.order.model.DeliverBill> dataList=new ArrayList<>();
         dataList.add(new com.cqqyd2014.order.model.DeliverBill());
         
     return dataList;
 }
}
