package com.cqqyd2014.test.run;

import java.io.StringWriter;
import java.math.BigDecimal;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;


public class TestSN {

	public static void main(String[] args) throws JAXBException {
		// TODO Auto-generated method stub

		new TestSN();
	}

	public TestSN()  {
		java.util.ArrayList<String> str1=com.cqqyd2014.wh.logic.GoodsLogic.decodeBarcode("0720201010300700072155");
		System.out.println(str1.get(0));
	
	}
	

}
