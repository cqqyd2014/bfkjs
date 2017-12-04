alter table menu_d add column web_attention boolean;
alter table menu_d add column web_attention_tips varchar(1024);
alter table menu_d add column web_attentsion_get_num_url varchar(1024);
update menu_d set web_attention=false,web_attentsion_get_num_url='#',web_attention_tips='';


update menu_d set web_attention=true,web_attentsion_get_num_url='#',web_attention_tips='已经付款还未分配打包人的订单' where menu_id='0001' and menu_d_id='0007';


  alter table menu_d drop web_attentsion_get_num_url ;
alter table menu_d add column get_num_class varchar(1024);
alter table menu_d add column get_num_method varchar(1024);
update menu_d set get_num_class='#',get_num_method='#';


update menu_d set get_num_class='com.cqqyd2014.hibernate.dao.VOrderMainDAO',get_num_method='getWaitAssginPackageCount'
 where menu_id='0001' and menu_d_id='0007';

update menu_d set web_attention=true,web_attention_tips='等待从库房发出的运单',get_num_class='com.cqqyd2014.hibernate.dao.VDeliverMDAO',get_num_method='getWaitDeliverCount'
 where menu_id='0001' and menu_d_id='0004';
 
update menu_d set web_attention=true,web_attention_tips='账户余额不足，等待付款订单',get_num_class='com.cqqyd2014.hibernate.dao.VOrderMainDAO',get_num_method='getUnPaidCount'
 where menu_id='0001' and menu_d_id='0006';

DROP VIEW v_user_menu_d;

CREATE OR REPLACE VIEW v_user_menu_d AS 
 SELECT t1.web_attention_tips,t1.web_attention,t1.get_num_class,t1.get_num_method,t1.order_id, t1.menu_id, t1.menu_d_id, t1.menu_d_name, 
    t1.menu_d_js_method, t1.menu_d_js_url, t1.com_id, t2.user_id
   FROM menu_d t1, 
    ( SELECT t2.menu_id, t2.menu_d_id, t1.user_id, t1.com_id
           FROM sys_user_role t1, sys_role_menu_d t2
          WHERE t1.role_id::text = t2.role_id::text AND t1.com_id::bpchar = t2.com_id
          GROUP BY t2.menu_id, t2.menu_d_id, t1.user_id, t1.com_id) t2
  WHERE t1.menu_id = t2.menu_id AND t1.menu_d_id = t2.menu_d_id AND t1.com_id = t2.com_id::bpchar;




  
drop view v_order_main_goods_barcode ;
DROP VIEW v_deliver_yue;


DROP VIEW v_deliver_d;

CREATE OR REPLACE VIEW v_deliver_d AS 
SELECT case when t9.name is null then '' else t9.name end AS send_user_name, t8.receiver_mobile, t8.addr_district, 
    t8.addr_city, t8.addr_province, t8.c_tell, t8.tell2, t8.c_user_addr, 
    t8.c_user_name, t8.order_dat, t8.sys_user_name AS create_user_name, 
    t8.original_id, t3.returned, t3.returned_memo, t3.returned_dat, 
    t3.returned_userid, t2.effective, t2.sended, t2.deliver_bill_status, 
    t0.wh_name, t2.wh_id, t2.com_id, 
    t5.logistics_name AS deliver_express_com_name, 
    t2.express_com AS deliver_express_com, t2.express_no AS deliver_express_no, 
    t2.logistics_vehicle AS deliver_express_vehicle, t3.goods_barcode, 
    t2.order_no, t2.seq, t3.goods_id, t6.c_name, t3.net_weight, t3.gross_weight, 
    t3.uuid AS deliver_d_uuid, t3.package_weight, t2.deliver_no, 
    t7.s_value AS unit
   FROM warehouse t0, deliver_d t3, logistics_company t5, goods_info t6, 
    sys_code t7, v_order_main t8, 
    deliver_m t2
   LEFT JOIN sys_user t9 ON t2.send_userid = t9.id AND t2.com_id = t9.com_id::bpchar
  WHERE t2.com_id = t3.com_id AND t2.order_no = t3.order_no AND t2.seq = t3.seq AND t6.unit::text = t7.s_code::text AND t5.logistics_id::text = t2.express_com::text AND t6.c_id::text = t3.goods_id::text AND t6.com_id::bpchar = t3.com_id AND t2.wh_id = t0.wh_id AND 
  t7.s_code::text = t6.unit::text AND t7.s_id::text = 'unit'::text AND t2.order_no = t8.order_no AND t2.com_id = t8.com_id;
--and t2.order_no='QYDTB201712040000110'



CREATE OR REPLACE VIEW v_order_main_goods_barcode AS 
 SELECT t1.sys_user_name, t1.vehicle_id AS order_vehicle_id, 
    t1.vehicle_name AS order_vehicle_vechicle_name, 
    t1.logistics_id AS order_express_com, 
    t1.logistics_name AS order_express_com_name, t1.user_id, 
        CASE
            WHEN t4.wh_id IS NULL THEN ''::bpchar
            ELSE t4.wh_id
        END AS deliver_wh_id, 
        CASE
            WHEN t4.wh_name IS NULL THEN ''::character varying
            ELSE t4.wh_name
        END AS deliver_wh_name, 
    t1.detail_memo, t1.com_id, t1.order_no, t1.c_user_name, t1.addr_province, 
    t1.addr_city, t1.addr_district, t1.order_dat, t1.c_status, t1.gt_status, 
    t1.ems_status, t1.order_type_name, t1.order_type_full_name, t1.package_user, 
    t1.original_id, 
        CASE
            WHEN t4.deliver_no IS NULL THEN ''::character varying
            ELSE t4.deliver_no
        END AS deliver_no, 
        CASE
            WHEN t4.deliver_bill_status IS NULL THEN ''::bpchar
            ELSE t4.deliver_bill_status
        END AS deliver_bill_status, 
        CASE
            WHEN t4.goods_barcode IS NULL THEN ''::bpchar
            ELSE t4.goods_barcode
        END AS goods_barcode, 
    t1.c_tell, 
        CASE
            WHEN t4.goods_id IS NULL THEN ''::character varying
            ELSE t4.goods_id
        END AS goods_id, 
        CASE
            WHEN t4.deliver_express_no IS NULL THEN ''::character varying
            ELSE t4.deliver_express_no
        END AS deliver_express_no, 
        CASE
            WHEN t4.deliver_express_com_name IS NULL THEN ''::character varying
            ELSE t4.deliver_express_com_name
        END AS deliver_express_com_name, 
        CASE
            WHEN t4.deliver_express_com IS NULL THEN ''::character varying
            ELSE t4.deliver_express_com
        END AS deliver_express_com, 
        CASE
            WHEN t4.deliver_express_vehicle IS NULL THEN ''::character varying::bpchar
            ELSE t4.deliver_express_vehicle
        END AS deliver_express_vehicle, 
        CASE
            WHEN t4.c_name IS NULL THEN ''::character varying
            ELSE t4.c_name
        END AS c_name
   FROM v_order_main t1
   LEFT JOIN v_deliver_d t4 ON t1.com_id = t4.com_id AND t1.order_no = t4.order_no
  ORDER BY t1.order_dat DESC;


  
CREATE OR REPLACE VIEW v_deliver_yue AS 
 SELECT t1.c_count AS order_num, t6.c_name, t1.com_id, 
    t1.c_order_id AS order_no, t1.c_goods_id, 
    sum(
        CASE
            WHEN t2.wh_id IS NULL THEN 0
            ELSE 1
        END) AS sended_num
   FROM goods_info t6, 
    c_order_detail t1
   LEFT JOIN ( SELECT v_deliver_d.effective, v_deliver_d.sended, 
            v_deliver_d.deliver_bill_status, v_deliver_d.wh_name, 
            v_deliver_d.wh_id, v_deliver_d.com_id, 
            v_deliver_d.deliver_express_com_name, 
            v_deliver_d.deliver_express_com, v_deliver_d.deliver_express_no, 
            v_deliver_d.deliver_express_vehicle, v_deliver_d.goods_barcode, 
            v_deliver_d.order_no, v_deliver_d.seq, v_deliver_d.goods_id, 
            v_deliver_d.c_name, v_deliver_d.net_weight, 
            v_deliver_d.gross_weight, v_deliver_d.deliver_d_uuid, 
            v_deliver_d.package_weight, v_deliver_d.deliver_no, 
            v_deliver_d.unit
           FROM v_deliver_d
          WHERE v_deliver_d.effective = true AND v_deliver_d.sended = true) t2 ON t1.c_order_id::bpchar = t2.order_no AND t1.com_id = t2.com_id AND t1.c_goods_id::text = t2.goods_id::text
  WHERE t6.c_id::text = t1.c_goods_id::text
  GROUP BY t1.c_order_id, t1.c_goods_id, t1.com_id, t6.c_name, t1.c_count;



