package com.cqqyd2014.print.pdf.factory;

import java.util.ArrayList;

import com.cqqyd2014.print.pdf.NormalBill;
import com.cqqyd2014.wh.model.PrePackageM;

public class PrepackageBarcodeFactory {
	public static ArrayList<com.cqqyd2014.wh.model.PrePackageM> getBeanCollection(){
        ArrayList<PrePackageM> dataList=new ArrayList<>();
         dataList.add(new PrePackageM());
         
     return dataList;
	}
}
