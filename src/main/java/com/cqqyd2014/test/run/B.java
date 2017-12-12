package com.cqqyd2014.test.run;

public class B extends A {

	@Override
	public String abc() {
		// TODO Auto-generated method stub
		super.abc();
		return "子";
		
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		java.util.ArrayList<String> a=new java.util.ArrayList<String>();
		a.add("a");
		java.util.ArrayList<String> b=new java.util.ArrayList<String>();
		b.add("b");
		a.removeAll(b);
		System.out.println(a);
	}

}
