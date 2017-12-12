package com.cqqyd2014.system.schedler;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.cqqyd2014.hibernate.HibernateSessionFactory;
import com.cqqyd2014.hibernate.entities.VUserPicking;
import com.cqqyd2014.hibernate.entities.VUserSending;
import com.cqqyd2014.quota.logic.QuotaTransLogic;

@Component("taskJobOrderAssign")
public class TaskJobOrderAssign  {
	 @Scheduled
	public void order_assign() {
		
		
		
		//System.out.println("分配订单任务进行中。。。"+new java.util.Date());
		java.util.Date now = new java.util.Date();

		Session session = HibernateSessionFactory.getSession();

		Transaction tx = session.beginTransaction();
		try {
			// 针对所有单位进行处理，先得到单位列表
			
			com.cqqyd2014.hibernate.dao.SysUserDAO sudao = new com.cqqyd2014.hibernate.dao.SysUserDAO();
			com.cqqyd2014.hibernate.dao.OrderMainDAO omdao = new com.cqqyd2014.hibernate.dao.OrderMainDAO();
			com.cqqyd2014.hibernate.dao.DeliverMDAO dmdao = new com.cqqyd2014.hibernate.dao.DeliverMDAO();
			com.cqqyd2014.hibernate.dao.VUserPickingDAO updao = new com.cqqyd2014.hibernate.dao.VUserPickingDAO();
			com.cqqyd2014.hibernate.dao.VUserSendingDAO usdao = new com.cqqyd2014.hibernate.dao.VUserSendingDAO();
			java.util.ArrayList<com.cqqyd2014.system.model.ComInfo> cis = com.cqqyd2014.system.logic.ComInfoLogic.getArrayListModelFromArrayListView(
					com.cqqyd2014.hibernate.dao.VComInfoDAO.getArrayListEntity(session));
			//1、清理未付款订单，看看能否足够付款
			//com.cqqyd2014.hibernate.dao.OrderMainDAO omdao=new com.cqqyd2014.hibernate.dao.OrderMainDAO();
			java.util.Date paid_time=new java.util.Date();
			java.util.ArrayList<com.cqqyd2014.hibernate.entities.COrderMain> oms_unpaid=omdao.getEntityUnPaid(session);
			for (int i=0;i<oms_unpaid.size();i++){
				com.cqqyd2014.hibernate.entities.COrderMain om=oms_unpaid.get(i);
				com.cqqyd2014.hibernate.entities.SysUser su=sudao.getUserFromUserid(session, om.getUserId(), om.getId().getComId());
				if (su.getQuotaCurrent().compareTo(om.getActualAmount())>=0){
					//可以扣费
					QuotaTransLogic.changeQuota(session, om.getId().getComId(), om.getUserId(), "f2eb38fa-4a4a-4e17-b1c1-3aa0460f3af9", "0002", om.getActualAmount(), "系统批量处理", om.getId().getOrderNo(), "");
					om.setPaid(true);
					om.setPaidMoney(om.getActualAmount());
					om.setPaidTime(paid_time);
					om.setCStatus("订单已付");
					session.saveOrUpdate(om);
				}
			}
			session.flush();
			
			
			
			// System.out.println("单位个数"+cis.size());
			for (int i = 0; i < cis.size(); i++) {
				com.cqqyd2014.system.model.ComInfo ci = cis.get(i);
				// 1、清理不在线的用户
				sudao.setTimeOutForceOffline(session, ci.getCom_code());
				// 分配“已经生成订单，未拣货的”
				// 得到未拣货的订单
				java.util.ArrayList<com.cqqyd2014.hibernate.entities.COrderMain> oms_pickup = omdao
						.getListUnAssignedPackager(session, ci.getCom_code());

				// System.out.println("需要拣货"+oms_pickup.size());
				// 得到有拣货资质的用户清单
				java.util.ArrayList<com.cqqyd2014.hibernate.entities.VUserPicking> ups = updao.getListByComId(session,
						ci.getCom_code());
				if (ups.size() != 0 && oms_pickup.size() != 0) {

					// 这些用户正在处理的订单数量总计
					long sum = 0;
					for (int j = 0; j < ups.size(); j++) {
						sum = sum + ups.get(j).getId().getPickingCount();
					}
					// 平均分配之后，每个用户的订单数量(整除去掉小数)
					long avg_count = (oms_pickup.size() + sum) / ups.size();
					// 是否有零头

					long remainder = (oms_pickup.size() + sum) - avg_count * ups.size();
					// System.out.println("余数是"+remainder);

					// 轮训的方式派发订单，轮训不同的用户
					int flag = 0;
					for (int j = 0; j < ups.size(); j++) {
						// 得到当前用户
						VUserPicking up = ups.get(j);
						// System.out.println(up.getId().getId());
						// 当前用户正在打包订单数量
						long picking_working_count = up.getId().getPickingCount();
						// 最终需要的数量，含需要分配的和正在处理的，理论上是之前的平均数加上1
						long picking_all = avg_count + (remainder >= (j + 1) ? 1 : 0);
						// 需要分配的数量

						long picking_need = picking_all - picking_working_count;
						// 如果已经在处理中的数量大于分配数量，则不分配
						if (picking_need > 0) {

							for (int k = 0; k < picking_need; k++) {
								if ((flag + 1) > oms_pickup.size())
									continue;
								com.cqqyd2014.hibernate.entities.COrderMain om = oms_pickup.get(flag);
								om.setPackageUser(up.getId().getId());
								om.setCStatus("订单处理");
								om.setGtStatus("等待拣货");
								om.setPackageUserAssignTime(now);
								session.saveOrUpdate(om);
								flag++;
							}
						}

					}

				}
				// 分配“已经生成订单，未发货的”
				// 得到未发货的订单
				java.util.ArrayList<com.cqqyd2014.hibernate.entities.VDeliverNeedAssign> oms_send = omdao
						.getListUnAssignedSender(session, ci.getCom_code());

				// System.out.println("需要发货 "+oms_send.size());
				// 得到有拣货资质的用户清单
				java.util.ArrayList<com.cqqyd2014.hibernate.entities.VUserSending> uss = usdao.getListByComId(session,
						ci.getCom_code());
				if (uss.size() != 0 && oms_send.size() != 0) {
					// 这些用户正在处理的订单数量总计
					long sum2 = 0;
					for (int j = 0; j < uss.size(); j++) {
						sum2 = sum2 + uss.get(j).getId().getSendingCount();
					}
					// 平均分配之后，每个用户的订单数量(整除去掉小数)
					long avg_count2 = (oms_send.size() + sum2) / uss.size();
					// 是否有零头

					long remainder2 = (oms_send.size() + sum2) - avg_count2 * uss.size();
					// System.out.println("余数是"+remainder);

					// 轮训的方式派发订单，轮训不同的用户
					int flag2 = 0;
					for (int j = 0; j < uss.size(); j++) {
						// 得到当前用户
						VUserSending up = uss.get(j);
						// System.out.println(up.getId().getId());
						// 当前用户正在打包订单数量
						long sending_working_count = up.getId().getSendingCount();
						// 最终需要的数量，含需要分配的和正在处理的，理论上是之前的平均数加上1
						long sending_all = avg_count2 + (remainder2 >= (j + 1) ? 1 : 0);
						// 需要分配的数量

						long sending_need2 = sending_all - sending_working_count;
						// 如果已经在处理中的数量大于分配数量，则不分配
						if (sending_need2 > 0) {

							for (int k = 0; k < sending_need2; k++) {
								if ((flag2 + 1) > oms_send.size())
									continue;
								com.cqqyd2014.hibernate.entities.VDeliverNeedAssign dna = oms_send.get(flag2);
								com.cqqyd2014.hibernate.entities.DeliverM dm = dmdao.getDeliverM(session,
										dna.getId().getOrderNo(), dna.getId().getSeq(), ci.getCom_code());
								dm.setSendUserid(up.getId().getId());
								dm.setSendUserAssignTime(new java.util.Date());
								dm.setDeliverBillStatus("分派发货");
								session.saveOrUpdate(dm);

								flag2++;
							}

						}

					}

				}

			}

			tx.commit();

		}

		catch (Exception e) {

			if (null != tx) {
				tx.rollback();// 撤销事务

			}
			System.out.println(e.toString());

		} finally {
			HibernateSessionFactory.closeSession();
		}
		//System.out.println("结束");

	}
}