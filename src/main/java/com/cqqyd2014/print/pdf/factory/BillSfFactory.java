package com.cqqyd2014.print.pdf.factory;

import java.util.ArrayList;

import com.cqqyd2014.express.sf.BillSf;



public class BillSfFactory {
	public static ArrayList<com.cqqyd2014.express.sf.BillSf> getBeanCollection(){
        ArrayList<com.cqqyd2014.express.sf.BillSf> dataList=new ArrayList<>();
         dataList.add(new com.cqqyd2014.express.sf.BillSf());
         
     return dataList;
 }
}
