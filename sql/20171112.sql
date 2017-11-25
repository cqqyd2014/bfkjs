alter table deliver_d add column returned boolean;
update deliver_d set returned=false;
alter table deliver_d add column returned_userid char(36);
update deliver_d set returned_userid='';
alter table deliver_d add column returned_dat timestamp with time zone;
update deliver_d set returned_dat='1900-1-1';
alter table deliver_d add column returned_memo varchar(512);
update deliver_d set returned_memo='';




DROP VIEW public.v_deliver_yue;


DROP VIEW public.v_order_main_goods_barcode;


DROP VIEW public.v_deliver_d;





CREATE OR REPLACE VIEW public.v_deliver_d AS 
 SELECT t3.returned,t3.returned_memo,t3.returned_dat,t3.returned_userid,t2.effective,
    t2.sended,
    t2.deliver_bill_status,
    t0.wh_name,
    t2.wh_id,
    t2.com_id,
    t5.logistics_name AS deliver_express_com_name,
    t2.express_com AS deliver_express_com,
    t2.express_no AS deliver_express_no,
    t2.logistics_vehicle AS deliver_express_vehicle,
    t3.goods_barcode,
    t2.order_no,
    t2.seq,
    t3.goods_id,
    t6.c_name,
    t3.net_weight,
    t3.gross_weight,
    t3.uuid AS deliver_d_uuid,
    t3.package_weight,
    t2.deliver_no,
    t7.s_value AS unit
   FROM warehouse t0,
    deliver_m t2,
    deliver_d t3,
    logistics_company t5,
    goods_info t6,
    sys_code t7
  WHERE t2.com_id = t3.com_id AND t2.order_no = t3.order_no AND t2.seq = t3.seq AND t6.unit::text = t7.s_code::text AND t5.logistics_id::text = t2.express_com::text AND t6.c_id::text = t3.goods_id::text AND t6.com_id::bpchar = t3.com_id AND t2.wh_id = t0.wh_id AND t7.s_code::text = t6.unit::text AND t7.s_id::text = 'unit'::text;


CREATE OR REPLACE VIEW public.v_deliver_yue AS 
 SELECT t1.c_count AS order_num,
    t6.c_name,
    t1.com_id,
    t1.c_order_id AS order_no,
    t1.c_goods_id,
    sum(
        CASE
            WHEN t2.wh_id IS NULL THEN 0
            ELSE 1
        END) AS sended_num
   FROM goods_info t6,
    c_order_detail t1
     LEFT JOIN ( SELECT v_deliver_d.effective,
            v_deliver_d.sended,
            v_deliver_d.deliver_bill_status,
            v_deliver_d.wh_name,
            v_deliver_d.wh_id,
            v_deliver_d.com_id,
            v_deliver_d.deliver_express_com_name,
            v_deliver_d.deliver_express_com,
            v_deliver_d.deliver_express_no,
            v_deliver_d.deliver_express_vehicle,
            v_deliver_d.goods_barcode,
            v_deliver_d.order_no,
            v_deliver_d.seq,
            v_deliver_d.goods_id,
            v_deliver_d.c_name,
            v_deliver_d.net_weight,
            v_deliver_d.gross_weight,
            v_deliver_d.deliver_d_uuid,
            v_deliver_d.package_weight,
            v_deliver_d.deliver_no,
            v_deliver_d.unit
           FROM v_deliver_d
          WHERE v_deliver_d.effective = true AND v_deliver_d.sended = true) t2 ON t1.c_order_id::bpchar = t2.order_no AND t1.com_id = t2.com_id AND t1.c_goods_id::text = t2.goods_id::text
  WHERE t6.c_id::text = t1.c_goods_id::text
  GROUP BY t1.c_order_id, t1.c_goods_id, t1.com_id, t6.c_name, t1.c_count;

CREATE OR REPLACE VIEW public.v_order_main_goods_barcode AS 
 SELECT t1.sys_user_name,
    t1.vehicle_id AS order_vehicle_id,
    t1.vehicle_name AS order_vehicle_vechicle_name,
    t1.logistics_id AS order_express_com,
    t1.logistics_name AS order_express_com_name,
    t1.user_id,
        CASE
            WHEN t4.wh_id IS NULL THEN ''::bpchar
            ELSE t4.wh_id
        END AS deliver_wh_id,
        CASE
            WHEN t4.wh_name IS NULL THEN ''::character varying
            ELSE t4.wh_name
        END AS deliver_wh_name,
    t1.detail_memo,
    t1.com_id,
    t1.order_no,
    t1.c_user_name,
    t1.addr_province,
    t1.addr_city,
    t1.addr_district,
    t1.order_dat,
    t1.c_status,
    t1.gt_status,
    t1.ems_status,
    t1.order_type_name,
    t1.order_type_full_name,
    t1.package_user,
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