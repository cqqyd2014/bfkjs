package com.cqqyd2014.print.pdf.factory;

import java.util.ArrayList;

public class GoodsFactory {
	public static ArrayList<com.cqqyd2014.wh.model.Goods> getBeanCollection(){
        ArrayList<com.cqqyd2014.wh.model.Goods> dataList=new ArrayList<>();
         dataList.add(new com.cqqyd2014.wh.model.Goods());
         
     return dataList;
 }
}
