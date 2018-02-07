package com.cqqyd2014.order.taobao_import.logic;

import java.math.BigDecimal;
import java.util.Map.Entry;

import org.hibernate.Session;

import com.cqqyd2014.order.logic.OrderLogic;
import com.cqqyd2014.util.table.Table;
import com.cqqyd2014.util.table.element.Row;


/*
 * 将两组数据处理为订单
 * 时间戳20170708
 * 
 * 原始数据是
 * "订单编号","买家会员名","买家支付宝账号","买家应付货款","买家应付邮费","买家支付积分","总金额","返点积分","买家实际支付金额","买家实际支付积分","订单状态","买家留言","收货人姓名","收货地址 ","运送方式","联系电话 ","联系手机","订单创建时间","订单付款时间 ","宝贝标题 ","宝贝种类 ","物流单号 ","物流公司","订单备注","宝贝总数量","店铺Id","店铺名称","订单关闭原因","卖家服务费","买家服务费","发票抬头","是否手机订单","分阶段订单信息","特权订金订单id","是否上传合同照片","是否上传小票","是否代付","定金排名","修改后的sku","修改后的收货地址","异常信息","天猫卡券抵扣","集分宝抵扣","是否是O2O交易","退款金额","预约门店"
="35153131473293579","tb6125470","1432480331@qq.com","201.12","0.00","0","201.12","0","201.12","0","买家已付款，等待卖家发货","","薛先生langer","广东省 广州市 越秀区 黄花岗街道环市东路云鹤南街云鹤园50号云鹤轩31楼3101(510180)","快递","","'13925130165","2017-07-08 00:42:32","2017-07-08 00:42:39","【顺丰包邮】原厂授权日本制吉川COOK-PAL雪平锅不锈钢","1","","","","1","0","勤驿达海外生活","订单未关闭","0","0元","","手机订单","",="","否","否","否","","","","","","","","0.00",""
="35082869905648884","琳郗薷","654985392@qq.com","186.15","0.00","0","186.15","0","186.15","0","买家已付款，等待卖家发货","","鲁华英","四川省 成都市 其它区 盛邦街333号中海兰庭9栋1单元603室(610041)","快递","","'13882206320","2017-07-07 22:29:59","2017-07-07 22:32:46","【顺丰包邮】原厂授权日本制吉川COOK-PAL雪平锅不锈钢","1","","","","1","0","勤驿达海外生活","订单未关闭","0","0元","","手机订单","",="","否","否","否","","","","","","","","0.00",""
="13293638796527443","阿珊小镇二","asxz2@126.com","331.55","0.00","0","331.55","0","331.55","0","买家已付款，等待卖家发货","","吴尚栢","广西壮族自治区 南宁市 青秀区 青秀山管理委员会南宁市青秀区民族大道170#莱茵湖畔B2-2-102室(000000)","快递","","'15277188038","2017-07-07 20:42:50","2017-07-07 20:43:01","【顺丰包邮】原厂授权代理日本制藤次郎F-503三德刀厨刀菜刀VG10","1","","","","1","0","勤驿达海外生活","订单未关闭","0","0元","","","",="","否","否","否","","","","","","","","0.00",""
="13230536893603030","congheng天下","316404966@qq.com","989.00","0.00","0","989.00","0","989.00","0","买家已付款，等待卖家发货","","李亮","安徽省 马鞍山市 花山区 塘西街道绿洲茗苑6栋304号（葛羊路绿洲花园附近）(243000)","快递","","'13865604107","2017-07-07 19:46:26","2017-07-07 19:48:31","【顺丰包邮】原厂授权代理日本制藤次郎F-521牛刀主厨刀SG2粉末钢","1","","","","1","0","勤驿达海外生活","订单未关闭","0","0元","","","",="","否","否","否","","","","","","","","0.00",""
="29911142123210814","tb27452426","13554301820","236.55","0.00","0","236.55","0","236.55","0","买家已付款，等待卖家发货","","刘丽华","湖北省 武汉市 洪山区 杨园南路欢乐大道口嘉隆小区30号商铺(430070)","快递","","'18086400812","2017-07-07 18:59:20","2017-07-07 19:00:46","【顺丰包邮】原厂授权代理日本制藤次郎F-312牛刀主厨刀VG10","1","","","","1","0","勤驿达海外生活","订单未关闭","0","0元","","手机订单","",="","否","否","否","","","","","","","","0.00",""

"订单编号","标题","价格","购买数量","外部系统编号","商品属性","套餐信息","备注","订单状态","商家编码"
="35153131473293579","【顺丰包邮】原厂授权日本制吉川COOK-PAL雪平锅不锈钢","239.00","1","YO-PAN-20-WITHGLASS","颜色分类：20厘米锅-第三方玻璃盖","","","买家已付款，等待卖家发货","YO-PAN-20-WITHGLASS",
="35082869905648884","【顺丰包邮】原厂授权日本制吉川COOK-PAL雪平锅不锈钢","219.00","1","YO-PAN-20","颜色分类：20厘米锅-不含盖","","","买家已付款，等待卖家发货","YO-PAN-20",
="13293638796527443","【顺丰包邮】原厂授权代理日本制藤次郎F-503三德刀厨刀菜刀VG10","349.00","1","TOJIRO-F-503","","","","买家已付款，等待卖家发货","TOJIRO-F-503",
="13230536893603030","【顺丰包邮】原厂授权代理日本制藤次郎F-521牛刀主厨刀SG2粉末钢","1009.00","1","TOJIRO-F-521","","","","买家已付款，等待卖家发货","TOJIRO-F-521",
="29911142123210814","【顺丰包邮】原厂授权代理日本制藤次郎F-312牛刀主厨刀VG10","249.00","1","TOJIRO-F-312","","","","买家已付款，等待卖家发货","TOJIRO-F-312",

 * 
 */
public class TwoFileToOrders {
	Table table_main;
	Table table_detail;
	java.util.ArrayList<com.cqqyd2014.order.model.Order> orders;
	
	public com.cqqyd2014.util.message.IfMessage process(Session session,Table t1,Table t2,String com_id,String order_from,String user_id) throws Exception{
		com.cqqyd2014.util.message.IfMessage msg=new com.cqqyd2014.util.message.IfMessage();
		if (t1.equals(t2)){
			throw new Exception("上传的数据为同一个表格，不能拆分为订单主表和明细表");
		}
		clean(t1);
		clean(t2);
		
		if (t1.getHeadColsIndex(1).equals("买家会员名")){
			table_main=t1;
		}
		if (t1.getHeadColsIndex(1).equals("标题")){
			table_detail=t1;
		}
		
		if (t2.getHeadColsIndex(1).equals("买家会员名")){
			table_main=t2;
		}
		if (t2.getHeadColsIndex(1).equals("标题")){
			table_detail=t2;
		}
		//过滤主表，只需要“订单状态”为“买家已付款，等待卖家发货”的记录
		java.util.ArrayList<Row> filter_rows=table_main.getRowFilter("订单状态", "买家已付款，等待卖家发货");
		table_main.setRows(filter_rows);
		//生成订单主表信息
		orders=new java.util.ArrayList<>();
		//所有订单需要发出的商品
		java.util.HashMap<String, java.math.BigDecimal> all_detail_map=new java.util.HashMap<>();
		for (int i=0;i<table_main.getRowCount();i++){
			com.cqqyd2014.order.model.Order order=new com.cqqyd2014.order.model.Order();
			order.setCom_id(com_id);
			
			java.util.Date now=com.cqqyd2014.util.DateUtil.FullStringToJDate(table_main.getRowFied(i, "订单付款时间 "));
			order.setOrder_dat(now);
			
			/*分解收货地址
			 * 
			 * 四川省 成都市 其它区 盛邦街333号中海兰庭9栋1单元603室(610041)
			 */
			String addr_line=table_main.getRowFied(i, "收货地址 ");
			String province=addr_line.substring(0,addr_line.indexOf(" "));
			addr_line=addr_line.substring(addr_line.indexOf(" ")+1,addr_line.length());
			String city=addr_line.substring(0,addr_line.indexOf(" "));
			addr_line=addr_line.substring(addr_line.indexOf(" ")+1,addr_line.length());
			String district=addr_line.substring(0,addr_line.indexOf(" "));
			addr_line=addr_line.substring(addr_line.indexOf(" ")+1,addr_line.length());
			String user_addr=addr_line.trim();
			order.setCity(city);
			order.setDistrict(district);
			order.setProvince(province);
			order.setUser_addr(user_addr);
			//电话前面有个单引号'13925130165
			String tell=table_main.getRowFied(i, "联系手机");
			if (tell.length()>1){
				order.setTell(tell.substring(1, tell.length()));
			}
			else{
				order.setTell("");
			}
			String tell2=table_main.getRowFied(i, "联系电话 ");
			if (tell2.length()>1){
				order.setTell2(tell2.substring(1, tell2.length()));
			}
			else{
				order.setTell2("");
			}
			
			order.setUser_name(table_main.getRowFied(i, "收货人姓名"));
			order.setOriginal_id(table_main.getRowFied(i, "订单编号"));
			order.setMemo("淘宝批量导入，会员名："+table_main.getRowFied(i, "买家会员名")+"/买家支付宝账号："+table_main.getRowFied(i, "买家支付宝账号")+"/买家应付货款："+table_main.getRowFied(i, "买家应付货款")+"/买家实际支付金额："+table_main.getRowFied(i, "买家实际支付金额")+"/买家留言："+table_main.getRowFied(i, "买家留言"));
			order.setActual_amount(new java.math.BigDecimal(table_main.getRowFied(i, "买家实际支付金额")));
			//处理订单明细，从detail表中提取订单号的rows
			Table table_tmp_detail=new Table();
			table_tmp_detail.setRows(table_detail.getRowFilter("订单编号", table_main.getRowFied(i, "订单编号")));
			table_tmp_detail.setHead(table_detail.getHead());
			java.util.HashMap<String, java.math.BigDecimal> detail_map=new java.util.HashMap<>();
			com.cqqyd2014.hibernate.dao.TaobaoGoodsDDAO tgddao=new com.cqqyd2014.hibernate.dao.TaobaoGoodsDDAO();
			for (int j=0;j<table_tmp_detail.getRowCount();j++){
				//得到外部编码
				String outer_code=table_tmp_detail.getRowFied(j, "外部系统编号");
				java.math.BigDecimal num=new java.math.BigDecimal(table_tmp_detail.getRowFied(j, "购买数量"));
				//得到这个外部编码对应的goods_id
				java.util.ArrayList<com.cqqyd2014.hibernate.entities.TaobaoGoodsD> tgds=tgddao.getListByGId(session, com_id, outer_code, order_from);
				for (int k=0;k<tgds.size();k++){
					com.cqqyd2014.hibernate.entities.TaobaoGoodsD tgd=tgds.get(k);
					java.math.BigDecimal old_num=detail_map.get(tgd.getId().getGoodsId());
					if (old_num==null){
						//新的元素
						detail_map.put(tgd.getId().getGoodsId(), tgd.getNum());
					}
					else{
						detail_map.put(tgd.getId().getGoodsId(), tgd.getNum().add(old_num));
					}
					//累计所有订单的商品
					java.math.BigDecimal all_old_num=all_detail_map.get(tgd.getId().getGoodsId());
					if (all_old_num==null){
						//新的元素
						all_detail_map.put(tgd.getId().getGoodsId(), tgd.getNum());
					}
					else{
						all_detail_map.put(tgd.getId().getGoodsId(), tgd.getNum().add(all_old_num));
					}
					
				}
				
				
				
				
			}
			//detail_map是订单明细雏形，转换为order_detail
			java.util.ArrayList<com.cqqyd2014.order.model.OrderDetail> ods=new java.util.ArrayList<>();
			for (Entry<String, BigDecimal> entry : detail_map.entrySet()) {  
				  
			    //System.out.println("Key = " + entry.getKey() + ", Value = " + entry.getValue());
				com.cqqyd2014.order.model.OrderDetail od=new com.cqqyd2014.order.model.OrderDetail();
				od.setGoods_id(entry.getKey());
				od.setNum(entry.getValue());
				ods.add(od);
			  
			}  
			order.setDetails(ods);
			
			orders.add(order);
			
		}
		//库存是否够发货
		//all_detail_map
		com.cqqyd2014.hibernate.dao.VUserPriceAvailableDAO gidao=new com.cqqyd2014.hibernate.dao.VUserPriceAvailableDAO();
		for (Entry<String, BigDecimal> entry : all_detail_map.entrySet()) {
			com.cqqyd2014.usergroup.model.UserPrice vupa=gidao.getGoodsInfo(session, entry.getKey(), com_id, user_id, new java.util.Date());
			if (vupa.getYue().compareTo(entry.getValue())==-1){
				throw new Exception("商品"+entry.getKey()+"数量不够，这批订单需要"+entry.getValue()+"存货现有"+vupa.getYue());
			}
		  
		}
		
		for  (int i=0;i<orders.size();i++){
			//ol.save(orders.get(i));
			
		}

		
		return msg;
	}
	//所有订单号前有=需要去除，所欲字段去除前后引号
	public void clean(Table temp) throws Exception{
		//1、去除=号
		for(int i=0;i<temp.getRowCount();i++){
			String old_value=temp.getRowFied(i, 0);
			temp.setField(i, 0, old_value.substring(1,old_value.length()));
		}
		//2、去除冒号
		for(int i=0;i<temp.getRowCount();i++){
			for (int j=0;j<temp.getColCount();j++){
				String old_value=temp.getRowFied(i, j);
				if (old_value.length()>0){
				if (old_value.substring(0,1).equals("\"")&&old_value.substring(old_value.length()-1,old_value.length()).equals("\"")){
					//System.out.println(old_value);
					String new_value= old_value.substring(1,old_value.length()-1);
					temp.setField(i, j,new_value);
				}
				}
				
			}
			
		}
		
		
		
		
	}

}
