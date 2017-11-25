package com.cqqyd2014.system.poiout.action;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.struts2.interceptor.SessionAware;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.cqqyd2014.hibernate.HibernateSessionFactory;
import com.opensymphony.xwork2.ActionSupport;

public class SKUExport extends ActionSupport implements SessionAware {
	private Map<String, Object> session;

	@Override
	public void setSession(Map<String, Object> session) {
		// TODO Auto-generated method stub
		this.session = session;
	}

	/** 导出Excel测试 */

	String com_id;

	public String getCom_id() {
		return com_id;
	}

	public void setCom_id(String com_id) {
		this.com_id = com_id;
	}

	String sku;

	public String getSku() {
		return sku;
	}

	public void setSku(String sku) {
		this.sku = sku;
	}

	public String execute() {

		Integer I_type = ((Integer) this.session.get("TYPE"));
		int type = I_type.intValue();
		String user_name = (String) this.session.get("USER_NAME");
		String user_id = (String) this.session.get("USER_ID");
		com_id = (String) this.session.get("com_code");

		Session session = HibernateSessionFactory.getSession();

		Transaction tx = session.beginTransaction();
/*
		try {

			// 出库记录

			// 第一步，创建一个webbook，对应一个Excel文件
			HSSFWorkbook wb = new HSSFWorkbook();
			// 第二步，在webbook中添加一个sheet，对应Excel文件中的 sheet
			
			
			
			
			
			
			com.cqqyd2014.hibernate.dao.VHgBzcDetailDAO hbddao = new com.cqqyd2014.hibernate.dao.VHgBzcDetailDAO();
			com.cqqyd2014.hibernate.dao.ComInfoDAO cidao=new com.cqqyd2014.hibernate.dao.ComInfoDAO();
			com.cqqyd2014.hibernate.entities.ComInfo ci=cidao.getComInfo(session, com_id);
			//入库记录
			
			
			
			
			//出库记录
			java.util.ArrayList<com.cqqyd2014.hibernate.entities.VHgBzcDetail> vbds = hbddao.getWhs(session, com_id, sku);
			for (int j = 0; j < vbds.size(); j++) {
				com.cqqyd2014.hibernate.entities.VHgBzcDetail vbd=vbds.get(j);
				
				HSSFSheet sheet = wb.createSheet("出库_"+"_"+vbd.getId().getCcmcShort());
				
				// 第三步，在sheet中添加表头第0行，注意老版本poi对Excel的行数列数有限制
				HSSFRow row = sheet.createRow(0);
				// 第四步，创建单元格样式：居中
				HSSFCellStyle style = wb.createCellStyle();
				style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
				// 第五步，创建表头单元格，并设置样式
				HSSFCell cell;
				cell = row.createCell(0);
				cell.setCellValue("序号");
				cell.setCellStyle(style);
				
				
				cell = row.createCell(1);
				cell.setCellValue("跨境平台订单号");
				cell.setCellStyle(style);

				cell = row.createCell(2);
				cell.setCellValue("SKU");
				cell.setCellStyle(style);

				cell = row.createCell(3);
				cell.setCellValue("日期");
				cell.setCellStyle(style);

				cell = row.createCell(4);
				cell.setCellValue("数量");
				cell.setCellStyle(style);

				cell = row.createCell(5);
				cell.setCellValue("仓储企业");
				cell.setCellStyle(style);

				cell = row.createCell(6);
				cell.setCellValue("出入类型");
				cell.setCellStyle(style);
				
				cell=row.createCell(7);
				cell.setCellValue("推送至仓库时间");
				cell.setCellStyle(style);

				// 第六步，写入实体数据，实际应用中这些数据从数据库得到
				
				com.cqqyd2014.hibernate.dao.VIntoHwOutFullDAO viofdao=new com.cqqyd2014.hibernate.dao.VIntoHwOutFullDAO();
				java.util.ArrayList<com.cqqyd2014.hibernate.entities.VIntoHwOutFull> items=viofdao.getOutFull(session, vbd.getId().getCId(), com_id, vbd.getId().getHwType());
				for (int i=1;i<=items.size();i++){
					com.cqqyd2014.hibernate.entities.VIntoHwOutFull item=items.get(i-1);
					row = sheet.createRow(i);
					row.createCell(0).setCellValue(i);
					row.createCell(1).setCellValue(item.getId().getKjsNo());
					row.createCell(2).setCellValue(item.getId().getGoodsId());
					row.createCell(3).setCellValue(com.cqqyd2014.util.DateUtil.getLocalFullString(item.getId().getCTime()));
					row.createCell(4).setCellValue(item.getId().getOutNum().toString());
					row.createCell(5).setCellValue(item.getId().getCcmcShort());
					row.createCell(6).setCellValue(item.getId().getSValue());
					if (item.getId().getHwStartTime().compareTo(com.cqqyd2014.util.DateUtil.FullStringToJDate("1970-1-1 00:00:00"))==0){
						row.createCell(7).setCellValue("");
					}
					else{
						row.createCell(7).setCellValue(com.cqqyd2014.util.DateUtil.getLocalFullString(item.getId().getHwStartTime()));
					}
					
					
				}
				
				
			}
			// 第七步，将文件存到流中
			ByteArrayOutputStream os = new ByteArrayOutputStream();
			wb.write(os);
			byte[] fileContent = os.toByteArray();
			ByteArrayInputStream is = new ByteArrayInputStream(fileContent);

			excelStream = is; // 文件流
			excelFileName = com_id+"_"+sku+ ".xls"; // 设置下载的文件名
			tx.commit();
		}
		

		catch (Exception e) {
			if (null != tx) {
				tx.rollback();// 撤销事务

			}
			System.out.println(e.getMessage());
			e.printStackTrace();

		} finally {
			HibernateSessionFactory.closeSession();
		}
*/
		return "success";
	}

	// -------------------------------------------------------------
	private InputStream excelStream; // 输出流变量
	private String excelFileName; // 下载文件名

	public InputStream getExcelStream() {
		return excelStream;
	}

	public void setExcelStream(InputStream excelStream) {
		this.excelStream = excelStream;
	}

	public String getExcelFileName() {
		return excelFileName;
	}

	public void setExcelFileName(String excelFileName) {
		this.excelFileName = excelFileName;
	}
}
