package com.cqqyd2014.print.pdf.factory;

import java.util.ArrayList;

import com.cqqyd2014.print.pdf.NormalBill;
import com.cqqyd2014.wh.model.Goods;

public class NormalBillFactory {
	public static ArrayList<NormalBill> getBeanCollection(){
        ArrayList<NormalBill> dataList=new ArrayList<>();
         dataList.add(new NormalBill());
         
     return dataList;
 }
}
