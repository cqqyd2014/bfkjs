package com.cqqyd2014.util.taobao;

import com.cqqyd2014.util.message.StringUtilIndexesOfChar;

public class OrderAutoAnalysis {
	String copyStirng;
	String order_no;
	String user_name;
	String province;
	String city;
	String district;
	String user_addr;
	String tell2;
	public String getTell2() {
		return tell2;
	}
	public void setTell2(String tell2) {
		this.tell2 = tell2;
	}
	public String getUser_addr() {
		return user_addr;
	}
	public void setUser_addr(String user_addr) {
		this.user_addr = user_addr;
	}
	String tell;
	String post;
	public String getUser_name() {
		return user_name;
	}
	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}
	
	public String getProvince() {
		return province;
	}
	public void setProvince(String province) {
		this.province = province;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getDistrict() {
		return district;
	}
	public void setDistrict(String district) {
		this.district = district;
	}
	
	public String getTell() {
		return tell;
	}
	public void setTell(String tell) {
		this.tell = tell;
	}
	public String getPost() {
		return post;
	}
	public void setPost(String post) {
		this.post = post;
	}
	public OrderAutoAnalysis(String copyStirng) throws OrderAutoAnalysisException {
		super();
		this.copyStirng = copyStirng;
		auto_analysis();
	}
	public String getCopyStirng() {
		return copyStirng;
	}
	public void setCopyStirng(String copyStirng) {
		this.copyStirng = copyStirng;
	}
	public String getOrder_no() {
		return order_no;
	}
	public void setOrder_no(String order_no) {
		this.order_no = order_no;
	}
	public  void auto_analysis() throws OrderAutoAnalysisException{
		/*
		 * 第一种类型 手机端返回
		 * 先根据回车分段为5段
		 * 第一段：亲! 请核对下订单哦：
		 * 第二段：订单号：27966122355340802
		 * 第思弹：实付: 236.55元
		 * 第五弹：收货信息: 王华,河南省,洛阳市,涧西区,郑州路街道河南洛阳   龙鳞路与联盟路交叉口    洛阳耐火材料研究院家属院15号楼,13937941200,471003
		 * 
		 * 
		 * 第二种 电脑千牛返回
		 * 
		 * 亲！请核对下地址哦:
买家姓名：蒋欣欣
联系方式：18027286335 020-34152631
收货地址：吉林省 白城市 洮北区 长庆街道长庆南街18号楼4单元401室
买家邮编：137000
商品属性: 颜色分类:18厘米锅-不含盖 (数量：1)


第三种，电脑端千牛，点复制

 沈勰 ，18221023027 ，， 上海 上海市 金山区 朱泾镇秀州街450弄  秀州阁 9号202室，201540

		 */
		
		String[] res ;
		copyStirng.replaceAll("\n\n", "");
			res= copyStirng.split("\n");
			int len=res.length;
			switch (len){
			case 5:
				//手机端，第一行应该为“亲! 请核对下订单哦："
				if (res[0].equals("亲! 请核对下订单哦：")){
					order_no=res[1].substring(res[1].indexOf("：")+1, res[1].length()).trim();
					
					String line4=res[4].substring(res[4].indexOf(": ")+1, res[4].length());
					
					String[] res2=line4.split(",");
					
					if (res2.length==7||res2.length==8){
						user_name=res2[0].trim();
						province=res2[1];
						city=res2[2];
						district=res2[3];
						//地址中间不能为逗号
						user_addr=res2[4].trim();
						/*
						 * 
						 * 收货信息: 于晓君,广东省,广州市,海珠区,瑞宝街道东晓南路锦丽居A7栋2304房,020-34152631,18027286335,510260
						 * 电话可能为两组，或者一组
						 * 
						 */
						if (res2.length==7){
							tell2="";
							tell=res2[5].trim();
							post=res2[6];
						}
						if (res2.length==8){
							tell2=res2[5].trim();
							tell=res2[6].trim();
							post=res2[7];
						}
						
						
					}
					else{
						throw new OrderAutoAnalysisException ("手机端自动分析的数据，地址字段少于7个字节，或者大于8个字节");
					}
				}else{
					throw new OrderAutoAnalysisException ("手机端数据，第一段不是“亲! 请核对下订单哦：”");
				}
				break;
			case 6:
				if (res[0].equals("亲！请核对下地址哦:")){
					order_no="0000000000";//无法提取
					user_name=res[1].substring(res[1].indexOf("：")+1, res[1].length()).trim();
					//联系方式：18027286335 020-34152631
					
					String tell_temp=res[2].substring(res[2].indexOf("：")+1, res[2].length());
					String[] tell_array=tell_temp.split(" ");
					if (tell_array.length==1){
						tell=tell_array[0].trim();
						tell2="";
					}
					if (tell_array.length==2){
						tell=tell_array[0].trim();
						tell2=tell_array[1].trim();
					}
					
					post=res[4].substring(res[4].indexOf("：")+1, res[4].length()).trim();
					String line3=res[3].substring(res[3].indexOf(": ")+1, res[3].length());
					province=line3.substring(line3.indexOf("：")+1,line3.indexOf(" "));
					line3=line3.substring(line3.indexOf(" ")+1,line3.length());
					city=line3.substring(0,line3.indexOf(" "));
					line3=line3.substring(line3.indexOf(" ")+1,line3.length());
					district=line3.substring(0,line3.indexOf(" "));
					line3=line3.substring(line3.indexOf(" ")+1,line3.length());
					user_addr=line3.trim();
					//System.out.println(line3);
					
				}else{
					throw new OrderAutoAnalysisException ("电脑端自动分析的数据，第一段不是“亲！请核对下地址哦:”");
				}
				break;
			case 1:
				//手机端复制
				//宋晨妍 13588297721 上海,上海市,虹口区,四川北路街道东宝兴路258弄1-3号凯润金城2栋1904号房间A,200080
				//电脑端复制
				//沈勰 ，18221023027 ，， 上海 上海市 金山区 朱泾镇秀州街450弄  秀州阁 9号202室，201540
				//测试时那个标志位出现在前
				String[] flags={" ","，"};
				com.cqqyd2014.util.StringUtil su=new com.cqqyd2014.util.StringUtil();
				StringUtilIndexesOfChar ch=su.whoIsFirst(res[0], flags);
				switch (ch.getFlag()){
				case " ":
					//手机端复制情况
					String[] res2=res[0].split(" ");
					switch (res2.length){
					case 3:
						user_name=res2[0];
						tell=res2[1];
						tell2="";
						String line3=res2[2];
						String[] res3=line3.split(",");
						if (res3.length==5){
							province=res3[0];
							city=res3[1];
							district=res3[2];
							user_addr=res3[3];
							post=res3[4];
							order_no="0000000000";//无法提取
						}
						else{
							throw new OrderAutoAnalysisException ("输入数据为"+res.length+"行，来源手机端端，地址应该为5段");
						}
						
						
						break;
					
					default:
						throw new OrderAutoAnalysisException ("输入数据为"+res.length+"行，来源手机端端数据应该为3、4组");
					}
					
					break;
				case "，":
					//电脑端复制情况
					String[] res3=res[0].split("，");
					switch (res3.length){
					case 5:
						
						user_name=res3[0];
						tell=res3[1];
						tell2=res3[2];
						String line3=res3[3];
						province=line3.substring(0,line3.indexOf(" "));
						line3=line3.substring(line3.indexOf(" ")+1,line3.length());
						city=line3.substring(0,line3.indexOf(" "));
						line3=line3.substring(line3.indexOf(" ")+1,line3.length());
						district=line3.substring(0,line3.indexOf(" "));
						line3=line3.substring(line3.indexOf(" ")+1,line3.length());
						user_addr=line3.trim();
						post=res3[4];
						order_no="0000000000";//无法提取
						break;
					default:
						throw new OrderAutoAnalysisException ("输入数据为"+res.length+"行，来源电脑端数据应该为5组");
					}
					break;
				
				default:
					throw new OrderAutoAnalysisException ("输入数据为"+res.length+"行，系统不能识别为电脑或者手机端复制，请确认数据来源");
				}
					break;
				default:
					throw new OrderAutoAnalysisException ("输入数据为"+res.length+"行，不能判断为何种类型分隔符，空格OR逗号");
					
				}
				
			
				
		
		
		
	}

}