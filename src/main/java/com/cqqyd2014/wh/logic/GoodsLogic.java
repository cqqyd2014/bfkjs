package com.cqqyd2014.wh.logic;


import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.cqqyd2014.hibernate.HibernateSessionFactory;
import com.cqqyd2014.util.exception.AjaxSuccessMessageException;
import com.cqqyd2014.util.message.IfMessage;

public class GoodsLogic {
	
	
	public static com.cqqyd2014.util.AjaxSuccessMessage getAjaxMessageModelByBarcode(com.cqqyd2014.util.AjaxSuccessMessage sm,Session session,String goods_barcode,String com_id) throws AjaxSuccessMessageException{
		
		

			com.cqqyd2014.hibernate.dao.VGoodsDAO vgdao=new com.cqqyd2014.hibernate.dao.VGoodsDAO();
			com.cqqyd2014.hibernate.entities.VGoods vg=vgdao.getEntityViewByBarcode(session, com_id, goods_barcode);
			if (vg==null) {
				throw new com.cqqyd2014.util.exception.AjaxSuccessMessageException("找不到这个条码"+goods_barcode);
			}

			com.cqqyd2014.wh.model.Goods g=com.cqqyd2014.wh.logic.GoodsLogic.getModelFromEntity(vg);
			sm.setO(g);
			
			com.cqqyd2014.hibernate.dao.VDeliverMDAO vomdao=new com.cqqyd2014.hibernate.dao.VDeliverMDAO();
			java.util.ArrayList<com.cqqyd2014.hibernate.entities.VDeliverM> voms=vomdao.getArrayListViewByBarcode(session, g.getBarcode(), com_id);
			
			java.util.ArrayList<com.cqqyd2014.order.model.DeliverBill> dbs=com.cqqyd2014.order.logic.DeliverMLogic.getArrayListModelFromArrayListView(voms);
			
			sm.setO2(dbs);
			
			
			com.cqqyd2014.hibernate.dao.VGoodsMoveDAO vgmdao=new com.cqqyd2014.hibernate.dao.VGoodsMoveDAO();
			java.util.ArrayList<com.cqqyd2014.wh.model.GoogsMoveLog> vmls=vgmdao.getModelListByBarcode(session, com_id, goods_barcode);
			
			sm.setO3(vmls);
			com.cqqyd2014.hibernate.dao.VWeixinScanQrLogDAO wdao=new com.cqqyd2014.hibernate.dao.VWeixinScanQrLogDAO();
			java.util.ArrayList<com.cqqyd2014.hibernate.entities.VWeixinScanQrLog> logs_h=wdao.getLogsByBarcode(session, com_id,goods_barcode);
			java.util.ArrayList<com.cqqyd2014.weixin.model.ScanQrLog> logs=com.cqqyd2014.weixin.logic.ScanQrLogLogic.getArrayListModelFromArrayListViews(logs_h);
			sm.setO4(logs);
			
			
			com.cqqyd2014.hibernate.dao.VDeliverDDAO vddao=new com.cqqyd2014.hibernate.dao.VDeliverDDAO();
			java.util.ArrayList<com.cqqyd2014.hibernate.entities.VDeliverD> vdds=vddao.getViewByGoodsBarcode(session,  goods_barcode, com_id);
			java.util.ArrayList<com.cqqyd2014.order.model.DeliverBillDetail> dbds=com.cqqyd2014.order.logic.DeliverDLogic.getArrayModelFromArrayView(vdds);
			sm.setO5(dbds);
			
			
			sm.setSuccess(true);
			sm.setSound("picked_ok");
			

		
		return sm;
	}
	
	
	
	
	public static void save(Session session,com.cqqyd2014.wh.model.Goods g) {
		com.cqqyd2014.hibernate.entities.Goods g_h=new com.cqqyd2014.hibernate.entities.Goods();
		g_h.setContractId(g.getCountry_id());
		g_h.setCreateDat(g.getCreate_dat());
		g_h.setCurrentStorage(g.getStorage_id());
		g_h.setCurrentWh(g.getWh_id());
		g_h.setEffective(g.isEffective());
		g_h.setGoodsId(g.getGoods_id());
		g_h.setId(new com.cqqyd2014.hibernate.entities.GoodsId(g.getBarcode(), g.getCom_id()));
		g_h.setIntoWhUuid(g.getInto_wh_uuid());
		g_h.setMaker(g.getMaker());
		g_h.setPrinted(g.isPrinted());
		g_h.setUneffectiveDat(g.getUneffective_dat());
		g_h.setUneffectiveUserid(g.getUneffective_userid());
		
		session.save(g_h);
	}
	
	
		/*
		 * 库房in和not_in只能是其中一种状态
		 * 库位in和not_in只能是其中一种状态
		 * 
		 */

	
	
	public static IfMessage if_available_in_wh_storage(Session session,com.cqqyd2014.wh.model.Goods g
			,java.util.ArrayList<String> whs_in
			,java.util.ArrayList<String> whs_not_in
			,java.util.ArrayList<String> storages_in
			,java.util.ArrayList<String> storages_not_in) {
		
		IfMessage ir=new IfMessage();
		if (g==null) {
			ir.setIf_ok(false);
			ir.setMessage("条码为NULL");
			return ir;
		}
		
		
		//1、测试编码是否可用
		
				if (g.isEffective()==false) {
					ir.setIf_ok(false);
					ir.setMessage("该商品条码已经作废");
					return ir;
				}
				if (g.isPrinted()==false) {
					ir.setIf_ok(false);
					ir.setMessage("该商品条码未打印");
					return ir;
				}
				//2、测试仓库
				
				String warehouse=g.getWh_id();
				//如果whs_in是判断在某个仓库中
				if (whs_in!=null) {
					int wh_id=com.cqqyd2014.util.ArrayListTools.indexOfArrayList(whs_in, warehouse);
					if (wh_id==-1) {
						ir.setIf_ok(false);
						ir.setMessage("该商品条码当前库房"+warehouse+"不在制定库房之内："+com.cqqyd2014.util.ArrayListTools.arrayListToArray(whs_in));
						return ir;
					}
				}
				//如果whs_not_in是判断不再某仓库中
				if (whs_not_in!=null) {
					int wh_id=com.cqqyd2014.util.ArrayListTools.indexOfArrayList(whs_not_in, warehouse);
					if (wh_id>-1) {
						ir.setIf_ok(false);
						ir.setMessage("该商品条码当前库房"+warehouse+"在制定库房之内："+com.cqqyd2014.util.ArrayListTools.arrayListToArray(whs_not_in));
						return ir;
					}
				}
				
				//3、测试库位
				String storage=g.getStorage_id();
				//如果storage_in不为空，是判断在某库位中
				if (storages_in!=null) {
					int storage_id=com.cqqyd2014.util.ArrayListTools.indexOfArrayList(storages_in, storage);
					if (storage_id==-1) {
						ir.setIf_ok(false);
						ir.setMessage("该商品条码当前库位"+storage_id+"不在制定库房之内："+com.cqqyd2014.util.ArrayListTools.arrayListToArray(storages_in));
						return ir;
					}
				}
				//如果storage_in为空，是判断不在某库位中
				if (storages_not_in!=null) {
					int storage_id=com.cqqyd2014.util.ArrayListTools.indexOfArrayList(storages_not_in, storage);
					if (storage_id>-1) {
						ir.setIf_ok(false);
						ir.setMessage("该商品条码当前库位"+storage_id+"不在制定库房之内："+com.cqqyd2014.util.ArrayListTools.arrayListToArray(storages_not_in));
						return ir;
					}
				}
				ir.setIf_ok(true);
				
				
				
				
				
				return ir;
	}

	/*
	 * 生成校验码V2，商品唯一编码的校验码，2位 编码规则，前4位品名，中间四位是“19771108”加上一个序列数字，然后12个数字偶数位倒排
	 * 
	 * 
	 * 每一位乘以1,6,3,8,3,1,6,9,7,5得数相加，与99取余数， 作为最后两位
	 * 
	 * 老版举例，商品0001，第一个序列数字为1， 第一步生成“000119771109” 第二部偶数位倒排生成“6901117791100”
	 * 新版举例，商品0001，第一个序列数字为1， 第一步生成“00012007070800000001” 第二步骤偶数位倒排生成“01002000080707000100”
	 */
	public static String makeCheckNo2(String no) {
		// 偶数位倒序
		int length=no.length();

		for (int i = 0; i < length/4; i++) {
			String first = no.substring(i * 2 + 1, i * 2 + 2);

			String last = no.substring(no.length() - (i * 2) - 1, no.length() - (i * 2));
			String befor_fist = no.substring(0, i * 2 + 1);
			String middle = no.substring(i * 2 + 2, no.length() - (i * 2) - 1);
			String flow_last = no.substring(no.length() - (i * 2), no.length());

			no = befor_fist + last + middle + first + flow_last;
			//System.out.println(no);

		}
		

		// 求余数
		String cn = "";
		int all = Integer.parseInt(no.substring(0, 1)) * 1 + Integer.parseInt(no.substring(1, 2)) * 6
				+ Integer.parseInt(no.substring(2, 3)) * 3 + Integer.parseInt(no.substring(3, 4)) * 8
				+ Integer.parseInt(no.substring(4, 5)) * 3 + Integer.parseInt(no.substring(5, 6)) * 1
				+ Integer.parseInt(no.substring(6, 7)) * 6 + Integer.parseInt(no.substring(7, 8)) * 9
				+ Integer.parseInt(no.substring(8, 9)) * 7 + Integer.parseInt(no.substring(9, 10)) * 5
				+ Integer.parseInt(no.substring(10, 11)) * 3 + Integer.parseInt(no.substring(11, 12)) * 5
				+ Integer.parseInt(no.substring(13, 14)) * 7 + Integer.parseInt(no.substring(14, 15)) * 7
				+ Integer.parseInt(no.substring(15, 16)) * 9 + Integer.parseInt(no.substring(16, 17)) * 4
				+ Integer.parseInt(no.substring(17, 18)) * 3 + Integer.parseInt(no.substring(18, 19)) * 3
				+ Integer.parseInt(no.substring(19, 20)) * 2;
		int yu = all % 99;
		if (yu <= 9) {
			cn = "0" + String.valueOf(yu);
		} else {
			cn = String.valueOf(yu);
		}
		return no + cn;
	}

	// 解码sn，第一个（前四位）为商品编码，第二个（中间8位）为序列号，第三个（后两位）为检验码
	// 新版解码sn，第一个（前四位）为商品编码，第二个（中间16位）为序列号，第三个（后两位）为检验码
	public static java.util.ArrayList<String> decodeBarcode(String sn) {
		sn=sn.replaceAll(" ", "");
		java.util.ArrayList<String> decode_str = new java.util.ArrayList<>();
		if (sn.length()==14){
			
		}
		String check_code = sn.substring(sn.length()-2, sn.length());

		String no = sn.substring(0, sn.length()-2);

		for (int i = 0; i < no.length()/4; i++) {
			String first = no.substring(i * 2 + 1, i * 2 + 2);

			String last = no.substring(no.length() - (i * 2) - 1, no.length() - (i * 2));
			String befor_fist = no.substring(0, i * 2 + 1);
			String middle = no.substring(i * 2 + 2, no.length() - (i * 2) - 1);
			String flow_last = no.substring(no.length() - (i * 2), no.length());

			no = befor_fist + last + middle + first + flow_last;

		}
		// System.out.println(no);
		String sn_code = no.substring(0, 4);
		// System.out.println(sn_code);
		String se = no.substring(4, sn.length()-2);
		// System.out.println(se);
		decode_str.add(sn_code);
		decode_str.add(se);
		decode_str.add(check_code);

		return decode_str;
	}
	
	//从 barcode得到goods_id
		public static String getGoodsIdByBarcode(Session session,String goods_barcode,String com_id) {
			java.util.ArrayList<String> decode_str=decodeBarcode(goods_barcode);
			String sn_code=decode_str.get(0);
			com.cqqyd2014.hibernate.dao.VGoodsInfoDAO gidao=new com.cqqyd2014.hibernate.dao.VGoodsInfoDAO();
			com.cqqyd2014.hibernate.entities.VGoodsInfo gi=gidao.getGoodsInfoBySnCode(session, sn_code, com_id);
			
			return gi.getId().getCId();
		}

	public static  IfMessage if_available_checksn(String barcode) {
		IfMessage ir=new IfMessage();
		//0、条码符合规则
		if (barcode.length()!=22&&barcode.length()!=14) {
			ir.setIf_ok(false);
			ir.setMessage("该商品条码长度不符合规则，应为14（旧版），或22（新版）");
			return ir;
		}
		try {

			String check_code = barcode.substring(barcode.length()-2, barcode.length());
			String no = barcode.substring(0, barcode.length()-2);
			String cn = "";
			int all = Integer.parseInt(no.substring(0, 1)) * 1 + Integer.parseInt(no.substring(1, 2)) * 6
					+ Integer.parseInt(no.substring(2, 3)) * 3 + Integer.parseInt(no.substring(3, 4)) * 8
					+ Integer.parseInt(no.substring(4, 5)) * 3 + Integer.parseInt(no.substring(5, 6)) * 1
					+ Integer.parseInt(no.substring(6, 7)) * 6 + Integer.parseInt(no.substring(7, 8)) * 9
					+ Integer.parseInt(no.substring(8, 9)) * 7 + Integer.parseInt(no.substring(9, 10)) * 5
					+ Integer.parseInt(no.substring(10, 11)) * 3 + Integer.parseInt(no.substring(11, 12)) * 5
					+ Integer.parseInt(no.substring(13, 14)) * 7 + Integer.parseInt(no.substring(14, 15)) * 7
					+ Integer.parseInt(no.substring(15, 16)) * 9 + Integer.parseInt(no.substring(16, 17)) * 4
					+ Integer.parseInt(no.substring(17, 18)) * 3 + Integer.parseInt(no.substring(18, 19)) * 3
					+ Integer.parseInt(no.substring(19, 20)) * 2;
			int yu = all % 99;
			if (yu <= 9) {
				cn = "0" + String.valueOf(yu);
			} else {
				cn = String.valueOf(yu);
			}
			if (check_code.equals(cn)) {
				ir.setIf_ok(true);
				//ir.setMessage("该商品条码长度规则:"+e.getMessage());
				return ir;
			} else {
				ir.setIf_ok(false);
				ir.setMessage("该商品条码解码错误");
				return ir;
			}
		} catch (Exception e) {
			//任何错误，包括里面含有字母，都返回false
			ir.setIf_ok(false);
			ir.setMessage("该商品条码解码异常:"+e.getMessage());
			return ir;
		}
	}

	
	
	
	//将数据库Goods实体类转换为model
	
	public static com.cqqyd2014.wh.model.Goods getModelFromEntity(com.cqqyd2014.hibernate.entities.VGoods vg){
		com.cqqyd2014.wh.model.Goods g=new com.cqqyd2014.wh.model.Goods();
		g.setPrinted(vg.getId().getPrinted());
		g.setStorage_id(vg.getId().getCurrentStorage());
		g.setStorage_name(vg.getId().getStorageName());
		g.setBarcode(vg.getId().getGoodsBarcode());
		g.setGoods_name(vg.getId().getCName());
		g.setGoods_id(vg.getId().getGoodsId());
		g.setContract_price(vg.getId().getContractPrice());
		g.setCountry_id(vg.getId().getContractId());
		g.setIn_dat(vg.getId().getInDat());
		g.setContract_paper_dat(vg.getId().getPaperDat());
		g.setEffective(vg.getId().getEffective());
		g.setHs_code(vg.getId().getCHs());
		g.setNot_air(vg.getId().getNotAir());
		g.setCountry_id(vg.getId().getOrigin());
		g.setCountry_name(vg.getId().getCCountryName());
		g.setSn_code(vg.getId().getSnCode());
		g.setSpec(vg.getId().getSnSpec());
		g.setUnit(vg.getId().getUnit());
		g.setGross_weight(vg.getId().getGrossWeight());
		g.setNet_weight(vg.getId().getNetWeight());
		g.setPackage_weight(vg.getId().getPackageWeight());
		g.setWh_id(vg.getId().getCurrentWh());
		g.setWh_name(vg.getId().getWhName());
		g.setSupply_id(vg.getId().getSupplyId());
		g.setSupply_name(vg.getId().getSupplyName());
		g.setUneffective_userid(vg.getId().getUneffectiveUserid());
		g.setUneffective_dat(vg.getId().getUneffectiveDat());
		g.setCom_id(vg.getId().getComId());
		g.setCreate_dat(vg.getId().getCreateDat());
		g.setInto_wh_uuid(vg.getId().getIntoWhUuid());
		g.setMaker(vg.getId().getMaker());
		g.setContract_no(vg.getId().getContractId());
		return g;
		
	}
	public static java.util.ArrayList<com.cqqyd2014.wh.model.Goods> getArrayModelFromArrayEntityV(java.util.ArrayList<com.cqqyd2014.hibernate.entities.VGoods> goods_entity_list){
		java.util.ArrayList<com.cqqyd2014.wh.model.Goods> glist=new java.util.ArrayList<>();
		for (int i=0;i<goods_entity_list.size();i++){
			com.cqqyd2014.hibernate.entities.VGoods goods_entity=goods_entity_list.get(i);
			com.cqqyd2014.wh.model.Goods g=getModelFromEntity(goods_entity);
			glist.add(g);
		}
		return glist;
	}
	
	
	
	
	
	//生成条码的处理
	
	public static void makeBarcode(Session session,String com_id,String goods_id,int num,String user_id,String contract_id,java.math.BigDecimal contract_price){
		
		
		
		
			com.cqqyd2014.hibernate.dao.VGoodsInfoDAO gid=new com.cqqyd2014.hibernate.dao.VGoodsInfoDAO();
			String snCode=gid.getSnCode(session, goods_id, com_id);
			
			//com.cqqyd2014.hibernate.dao.SnMaxDAO smd=new com.cqqyd2014.hibernate.dao.SnMaxDAO();
			java.util.Date today=com.cqqyd2014.util.DateUtil.JDateToStartDate(new java.util.Date());
			String compat_today=com.cqqyd2014.util.DateUtil.JDateToCompatString(today);
			//得到最后一个编码的序列值
			long end=com.cqqyd2014.hibernate.dao.SnMaxDAO.getNew(session, num,"GOODS_",com_id,today);

			/*
			 * 老板条码是19771108为基数
			 */
			
			 //long start=19771108L;
			/*
			 * 新版基数为0，在于数据库存存储的当日商品基数，8位
			 */
			
			for (long j=0;j<num;j++){
				long current=end-j;
				//写入数据库数据
				
				
				
				/*
				 * 老板是19771108加上一个数字，总长度14位
				 */
				//String no=snCode+String.valueOf(current);
				/*
				 * 新版是20170708年月日8位加上8位的序列
				 * 
				 */
				com.cqqyd2014.wh.model.Goods g=new com.cqqyd2014.wh.model.Goods();
				String no=snCode+compat_today+com.cqqyd2014.util.NumberUtil.numToStr0(current, 8);
				g.setBarcode(makeCheckNo2(no));
				if (contract_price!=null){
					g.setContract_price(contract_price);
				}
				else{
					g.setContract_price(new java.math.BigDecimal(0));
				}
				g.setEffective(true);
				g.setGoods_id(goods_id);
				g.setPrinted(false);
				g.setStorage_id("DEFAUL");
				g.setUneffective_dat(com.cqqyd2014.util.DateUtil.ShortStringToJDate("1900-1-1"));
				g.setUneffective_userid("");
				g.setWh_id("SUPPLY");
				g.setCountry_id(contract_id);
				g.setCreate_dat(new java.util.Date());
				g.setMaker(user_id);
				g.setInto_wh_uuid("");
				g.setCom_id(com_id);
				
				save(session, g);
				
				
			}
			
			
	}
	
	
	//商品移动的各种处理
	
	private synchronized static com.cqqyd2014.hibernate.entities.Goods moveBeweenStorage(Session session,String goodsBarcode,String fromWhId,String fromStorage,String toWhId,String toStorage,String com_id,java.util.Date dat,String moveType,String user_id){
		//商品移动
		synchronized(GoodsLogic.class){
		String move_id=com.cqqyd2014.util.StringUtil.getUUID();
		com.cqqyd2014.hibernate.entities.GoodsMove smove=new com.cqqyd2014.hibernate.entities.GoodsMove();
		com.cqqyd2014.hibernate.entities.GoodsMoveId smid=new com.cqqyd2014.hibernate.entities.GoodsMoveId();
		smid.setMoveId(move_id);
		smid.setComId(com_id);
		smove.setGoodsBarcode(goodsBarcode);
		smove.setId(smid);
		smove.setFromWh(fromWhId);
		smove.setFromStorage(fromStorage);
		smove.setToWh(toWhId);
		smove.setToStorage(toStorage);
		smove.setDat(dat);
		smove.setUserId(user_id);
		/*
		 * moveType 001到货入库
		 * 
		 * moveType 002被锁定
		 * 
		 * moveType 003锁定销售
		 * 
		 */
		smove.setMoveType(moveType);
		session.save(smove);
		smid=null;
		smove=null;
		//更新商品状态
		com.cqqyd2014.hibernate.dao.GoodsDAO gdao=new com.cqqyd2014.hibernate.dao.GoodsDAO();
		com.cqqyd2014.hibernate.entities.Goods goods=gdao.getEntity(session, goodsBarcode, com_id);
		
		goods.setCurrentWh(toWhId);
		goods.setCurrentStorage(toStorage);
		session.saveOrUpdate(goods);
		gdao=null;
		return goods;
		}
	}
	

	
	
	//新品入库
	public static com.cqqyd2014.hibernate.entities.Goods NewItemToWh(Session session,String goodsBarcode,String toWhId,java.util.Date dat,String com_id,String user_id) throws com.cqqyd2014.util.exception.AjaxSuccessMessageException{
		//测试是否为供应商待入库状态
		com.cqqyd2014.hibernate.dao.VGoodsDAO gdao=new com.cqqyd2014.hibernate.dao.VGoodsDAO();
		com.cqqyd2014.hibernate.entities.VGoods g=gdao.getEntityViewByBarcode(session, com_id, goodsBarcode) ;
		String currentWh=g.getId().getCurrentWh();
		String currentStorage=g.getId().getCurrentStorage();
		if (!currentWh.equals("SUPPLY")){
			throw new com.cqqyd2014.util.exception.AjaxSuccessMessageException("商品"+goodsBarcode+"未处于供应商仓库，不得入库");
		}
		if (!currentStorage.equals("DEFAUL")){
			throw new com.cqqyd2014.util.exception.AjaxSuccessMessageException("商品"+goodsBarcode+"未处于普通状态，不得入库");
		}
		g=null;
		
		gdao=null;
		return moveBeweenStorage(session,goodsBarcode,"SUPPLY","DEFAUL",toWhId,"DEFAUL",com_id,dat,"001",user_id);
		
	}
	//002商品因为拣货而锁定
	public static com.cqqyd2014.hibernate.entities.Goods  LockItemsForPickup(Session session,String goodsBarcode,String com_id,String user_id) throws com.cqqyd2014.util.exception.AjaxSuccessMessageException{
		com.cqqyd2014.hibernate.dao.GoodsDAO gdao=new com.cqqyd2014.hibernate.dao.GoodsDAO();
		com.cqqyd2014.hibernate.entities.Goods g=gdao.getEntity(session, goodsBarcode, com_id);
		String currentWh=g.getCurrentWh();
		String currentStorage=g.getCurrentStorage();
		if (currentWh.equals("SUPPLY")){
			throw new com.cqqyd2014.util.exception.AjaxSuccessMessageException("商品"+goodsBarcode+"处于供应商仓库，不得拣货");
		}
		if (currentStorage.equals("CUSTOM")){
			throw new com.cqqyd2014.util.exception.AjaxSuccessMessageException("商品"+goodsBarcode+"已经发出，不得拣货");
		}
		if (currentStorage.equals("LOCKED")){
			throw new com.cqqyd2014.util.exception.AjaxSuccessMessageException("商品"+goodsBarcode+"已经是拣货状态，不得再次拣货");
		}
		
		gdao=null;
		//商品从普通变为锁定状态
		return moveBeweenStorage(session,goodsBarcode,currentWh,"DEFAUL",currentWh,"LOCKED",com_id,new java.util.Date(),"002",user_id);
	}
	//锁定商品销售003
		public static com.cqqyd2014.hibernate.entities.Goods LockItemToSaleItem(Session session,String goodsBarcode,String com_id,String user_id) throws com.cqqyd2014.util.exception.AjaxSuccessMessageException{
			
			//测试是否为锁定待发出商品
			com.cqqyd2014.hibernate.dao.GoodsDAO gdao=new com.cqqyd2014.hibernate.dao.GoodsDAO();
			com.cqqyd2014.hibernate.entities.Goods g=gdao.getEntity(session, goodsBarcode, com_id);
			String currentWh=g.getCurrentWh();
			String currentStorage=g.getCurrentStorage();
			if (currentWh.equals("CUSTOM")){
				throw new com.cqqyd2014.util.exception.AjaxSuccessMessageException("商品"+goodsBarcode+"已经处于客户仓库，不得再次销售");
			}
			if (!currentStorage.equals("LOCKED")){
				throw new com.cqqyd2014.util.exception.AjaxSuccessMessageException("商品"+goodsBarcode+"未处于锁定状态，不能从锁定发送至客户");
			}
			g=null;
			
			gdao=null;
			return moveBeweenStorage(session,goodsBarcode,currentWh,"LOCKED","CUSTOM","DEFAUL",com_id,new java.util.Date(),"003",user_id);
		}
	
	//商品被预包装004
	public static com.cqqyd2014.hibernate.entities.Goods  PrePacItemsInWh(Session session,String goodsBarcode,String com_id,String user_id) throws com.cqqyd2014.util.exception.AjaxSuccessMessageException{
		//测试商品是否为等待预包装
		com.cqqyd2014.hibernate.dao.GoodsDAO gdao=new com.cqqyd2014.hibernate.dao.GoodsDAO();
		com.cqqyd2014.hibernate.entities.Goods g=gdao.getEntity(session, goodsBarcode, com_id);
		String currentWh=g.getCurrentWh();
		String currentStorage=g.getCurrentStorage();
		if (currentWh.equals("CUSTOM")){
			throw new com.cqqyd2014.util.exception.AjaxSuccessMessageException("商品"+goodsBarcode+"已经处于客户仓库，不能包装");
		}
		if (currentWh.equals("SUPPLY")){
			throw new com.cqqyd2014.util.exception.AjaxSuccessMessageException("商品"+goodsBarcode+"已经处于供应商仓库，不能包装");
		}
		if (currentStorage.equals("PREPAC")){
			throw new com.cqqyd2014.util.exception.AjaxSuccessMessageException("商品"+goodsBarcode+"已经预包装不能再包装");
		}
		if (currentStorage.equals("LOCKED")){
			throw new com.cqqyd2014.util.exception.AjaxSuccessMessageException("商品"+goodsBarcode+"已经被锁定不能再包装");
		}
		g=null;
				
		gdao=null;
		return moveBeweenStorage(session,goodsBarcode,currentWh,"DEFAUL",currentWh,"PREPAC",com_id,new java.util.Date(),"004",user_id);
	}
	//预包装销售
	public static com.cqqyd2014.hibernate.entities.Goods  PrePacItemsToLockItem(Session session,String goodsBarcode,String com_id,String user_id) throws com.cqqyd2014.util.exception.AjaxSuccessMessageException{
		//测试是否为预包装待发出商品
		com.cqqyd2014.hibernate.dao.GoodsDAO gdao=new com.cqqyd2014.hibernate.dao.GoodsDAO();
		com.cqqyd2014.hibernate.entities.Goods g=gdao.getEntity(session, goodsBarcode, com_id);
		String currentWh=g.getCurrentWh();
		String currentStorage=g.getCurrentStorage();
		if (currentWh.equals("CUSTOM")){
			throw new com.cqqyd2014.util.exception.AjaxSuccessMessageException("商品"+goodsBarcode+"已经处于客户仓库，不得再次销售");
		}
		if (!currentStorage.equals("PREPAC")){
			throw new com.cqqyd2014.util.exception.AjaxSuccessMessageException("商品"+goodsBarcode+"未处于打包，不能从预包装发货");
		}
		g=null;
		
		gdao=null;
		return moveBeweenStorage(session,goodsBarcode,currentWh,"PREPAC",currentWh,"LOCKED",com_id,new java.util.Date(),"005",user_id);
	}
	//预包装解包
		public static com.cqqyd2014.hibernate.entities.Goods  PrePacItemsUnPack(Session session,String goodsBarcode,String com_id,String user_id) throws com.cqqyd2014.util.exception.AjaxSuccessMessageException{
			//测试是否为预包装待发出商品
			com.cqqyd2014.hibernate.dao.GoodsDAO gdao=new com.cqqyd2014.hibernate.dao.GoodsDAO();
			com.cqqyd2014.hibernate.entities.Goods g=gdao.getEntity(session, goodsBarcode, com_id);
			String currentWh=g.getCurrentWh();
			String currentStorage=g.getCurrentStorage();
			if (currentWh.equals("CUSTOM")){
				throw new com.cqqyd2014.util.exception.AjaxSuccessMessageException("商品"+goodsBarcode+"已经处于客户仓库，不得解包");
			}
			if (!currentStorage.equals("PREPAC")){
				throw new com.cqqyd2014.util.exception.AjaxSuccessMessageException("商品"+goodsBarcode+"未处于打包状态，不得解包");
			}
			g=null;
			
			gdao=null;
			return moveBeweenStorage(session,goodsBarcode,currentWh,"PREPAC",currentWh,"DEFAUL",com_id,new java.util.Date(),"010",user_id);
		}
	//删除订单锁定商品退正常库
	public static com.cqqyd2014.hibernate.entities.Goods ReturnLockItemToDefault(Session session,String goodsBarcode,String com_id,String user_id) throws com.cqqyd2014.util.exception.AjaxSuccessMessageException{
		
		//测试是否为锁定待发出商品
		com.cqqyd2014.hibernate.dao.GoodsDAO gdao=new com.cqqyd2014.hibernate.dao.GoodsDAO();
		com.cqqyd2014.hibernate.entities.Goods g=gdao.getEntity(session, goodsBarcode, com_id);
		String currentWh=g.getCurrentWh();
		String currentStorage=g.getCurrentStorage();
		if (currentWh.equals("CUSTOM")){
			throw new com.cqqyd2014.util.exception.AjaxSuccessMessageException("商品"+goodsBarcode+"已经处于客户仓库，不得再次销售");
		}
		if (!currentStorage.equals("LOCKED")){
			throw new com.cqqyd2014.util.exception.AjaxSuccessMessageException("商品"+goodsBarcode+"未处于锁定状态，不能从锁定发送至客户");
		}
		g=null;
		
		gdao=null;
		return moveBeweenStorage(session,goodsBarcode,currentWh,"LOCKED",currentWh,"DEFAUL",com_id,new java.util.Date(),"006",user_id);
	}
	//移库
		public static com.cqqyd2014.hibernate.entities.Goods MoveGoods(Session session,String goodsBarcode,String toWhId,String fromWhId,java.util.Date dat,String com_id,String user_id) throws com.cqqyd2014.util.exception.AjaxSuccessMessageException{
			//测试是否为供应商待入库状态
			com.cqqyd2014.hibernate.dao.GoodsDAO gdao=new com.cqqyd2014.hibernate.dao.GoodsDAO();
			com.cqqyd2014.hibernate.entities.Goods g=gdao.getEntity(session, goodsBarcode, com_id);
			String currentWh=g.getCurrentWh();
			String currentStorage=g.getCurrentStorage();
			if (!currentWh.equals(fromWhId)){
				throw new com.cqqyd2014.util.exception.AjaxSuccessMessageException("商品"+goodsBarcode+"未处于"+fromWhId+"仓库，不得移库");
			}
			if (!currentStorage.equals("DEFAUL")){
				throw new com.cqqyd2014.util.exception.AjaxSuccessMessageException("商品"+goodsBarcode+"未处于普通状态，不得移库");
			}
			g=null;
			
			gdao=null;
			return moveBeweenStorage(session,goodsBarcode,fromWhId,"DEFAUL",toWhId,"DEFAUL",com_id,dat,"007",user_id);
			
		}
		//退货入库
		public static com.cqqyd2014.hibernate.entities.Goods GoodsReturn(Session session,String goodsBarcode,String toWhId,java.util.Date dat,String com_id,String user_id) throws com.cqqyd2014.util.exception.AjaxSuccessMessageException{
			//测试是否为客户仓库
			com.cqqyd2014.hibernate.dao.GoodsDAO gdao=new com.cqqyd2014.hibernate.dao.GoodsDAO();
			com.cqqyd2014.hibernate.entities.Goods g=gdao.getEntity(session, goodsBarcode, com_id);
			String currentWh=g.getCurrentWh();
			String currentStorage=g.getCurrentStorage();
			if (!currentWh.equals("CUSTOM")){
				throw new com.cqqyd2014.util.exception.AjaxSuccessMessageException("商品"+goodsBarcode+"未处于客户仓库，不得退货");
			}
			if (!currentStorage.equals("DEFAUL")){
				throw new com.cqqyd2014.util.exception.AjaxSuccessMessageException("商品"+goodsBarcode+"未处于普通状态，不得退货");
			}
			if (toWhId.equals("CUSTOM")){
				throw new com.cqqyd2014.util.exception.AjaxSuccessMessageException("商品"+goodsBarcode+"不能退至客户仓库");
			}
			if (toWhId.equals("SUPPLY")){
				throw new com.cqqyd2014.util.exception.AjaxSuccessMessageException("商品"+goodsBarcode+"不能退至供应商仓库");
			}
			g=null;
			
			gdao=null;
			return moveBeweenStorage(session,goodsBarcode,currentWh,"DEFAUL",toWhId,"DEFAUL",com_id,dat,"008",user_id);
			
		}
	//商品损毁，已经入库的不需要减少入库单，但是需要移库到破损库位
		public static com.cqqyd2014.hibernate.entities.Goods GoodsBroken(Session session,String goodsBarcode,java.util.Date dat,String com_id,String user_id) throws com.cqqyd2014.util.exception.AjaxSuccessMessageException{
			//测试是否为客户仓库
			com.cqqyd2014.hibernate.dao.GoodsDAO gdao=new com.cqqyd2014.hibernate.dao.GoodsDAO();
			com.cqqyd2014.hibernate.entities.Goods g=gdao.getEntity(session, goodsBarcode, com_id);
			String currentWh=g.getCurrentWh();
			String currentStorage=g.getCurrentStorage();
			if (currentWh.equals("CUSTOM")){
				throw new com.cqqyd2014.util.exception.AjaxSuccessMessageException("商品"+goodsBarcode+"在客户仓库，不得损毁");
			}
			if (currentWh.equals("SUPPLY")){
				throw new com.cqqyd2014.util.exception.AjaxSuccessMessageException("商品"+goodsBarcode+"未入库不得损毁");
			}
			if (currentStorage.equals("LOCKED")||currentStorage.equals("PREPAC")){
				throw new com.cqqyd2014.util.exception.AjaxSuccessMessageException("商品"+goodsBarcode+"被锁定或者预包装，不得损毁");
			}
			g.setEffective(false);
			g.setUneffectiveDat(new java.util.Date());
			session.saveOrUpdate(g);
			session.flush();
			
			g=null;
			
			gdao=null;
			return moveBeweenStorage(session,goodsBarcode,currentWh,"DEFAUL",currentWh,"BROKEN",com_id,dat,"009",user_id);
			
		}
		//标签损毁，标签移动入LABLE_库位，入库单需要减少入库数量，订单也要减少入库数量
		public static com.cqqyd2014.hibernate.entities.Goods GoodsLableBroken(Session session,String goodsBarcode,java.util.Date dat,String com_id,String user_id) throws com.cqqyd2014.util.exception.AjaxSuccessMessageException{
			//测试是否为客户仓库
			com.cqqyd2014.hibernate.dao.GoodsDAO gdao=new com.cqqyd2014.hibernate.dao.GoodsDAO();
			com.cqqyd2014.hibernate.entities.Goods g=gdao.getEntity(session, goodsBarcode, com_id);
			String currentWh=g.getCurrentWh();
			String currentStorage=g.getCurrentStorage();
			if (currentWh.equals("CUSTOM")){
				throw new com.cqqyd2014.util.exception.AjaxSuccessMessageException("商品"+goodsBarcode+"在客户仓库，不得销毁");
			}
			if (currentWh.equals("SUPPLY")){
				throw new com.cqqyd2014.util.exception.AjaxSuccessMessageException("商品"+goodsBarcode+"未入库不得销毁");
			}
			if (currentStorage.equals("LOCKED")||currentStorage.equals("PREPAC")){
				throw new com.cqqyd2014.util.exception.AjaxSuccessMessageException("商品"+goodsBarcode+"被锁定或者预包装，不得销毁");
			}
			g.setEffective(false);
			g.setUneffectiveDat(new java.util.Date());
			session.saveOrUpdate(g);
			session.flush();
			
			g=null;
			
			gdao=null;
			return moveBeweenStorage(session,goodsBarcode,currentWh,"DEFAUL",currentWh,"LABEL_",com_id,dat,"011",user_id);
			
		}
	
	
	public static com.cqqyd2014.wh.model.Goods getModelGoodBarcode(Session session,String com_id,String barcode){
		com.cqqyd2014.hibernate.dao.GoodsDAO swdao=new com.cqqyd2014.hibernate.dao.GoodsDAO();
		com.cqqyd2014.hibernate.entities.Goods sw=swdao.getEntity(session, barcode, com_id);
		
		swdao=null;
		
		com.cqqyd2014.wh.model.Goods sn_item=new com.cqqyd2014.wh.model.Goods();
		sn_item.setGoods_id(sw.getGoodsId());
		sn_item.setWh_id(sw.getCurrentWh());
		sn_item.setBarcode(barcode);
		
		com.cqqyd2014.hibernate.dao.VGoodsInfoDAO gidao=new com.cqqyd2014.hibernate.dao.VGoodsInfoDAO();
		com.cqqyd2014.hibernate.entities.VGoodsInfo gi=gidao.getGoodsInfo(sw.getGoodsId(), session, com_id);
		sn_item.setGoods_name(gi.getId().getCName());
		sw=null;
		return sn_item;
	}
	

	
	

}