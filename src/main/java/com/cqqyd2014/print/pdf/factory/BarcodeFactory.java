package com.cqqyd2014.print.pdf.factory;

import java.util.ArrayList;

import com.cqqyd2014.wh.model.Goods;

public class BarcodeFactory {
	public static ArrayList<Goods> getBeanCollection(){
        ArrayList<Goods> dataList=new ArrayList<>();
         dataList.add(new Goods());
         
     return dataList;
 }

}
