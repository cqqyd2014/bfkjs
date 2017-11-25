alter table weixin_scan_qr_log add column effective boolean;
CREATE TABLE return_goods
(
  com_id character(4) NOT NULL,
  uuid character(36) NOT NULL,
  order_no character(20),
  goods_barcode character(22),
  op_dat timestamp with time zone,
  return_userid character(36),
  memo character varying(512),
  return_price numeric,
  order_create_userid character(36),
  goods_id character varying(128),
  seq character(4),
  CONSTRAINT pk_return_goods PRIMARY KEY (com_id, uuid)
);


create view v_weixin_scan_qr_log as
select * from weixin_scan_qr_log;



DROP VIEW v_order_detail;

CREATE OR REPLACE VIEW v_order_detail AS 
 SELECT t1.c_time,t1.c_tax2,t1.c_reg_tax2,t1.c_price2,t1.totall, t1.total2, t1.c_order_id, t1.c_goods_id, t1.c_count, 
    t1.c_price, t1.c_detail_id, t2.c_name, t2.unit, t1.com_id
   FROM c_order_detail t1, goods_info t2
  WHERE t1.c_goods_id::text = t2.c_id::text;


  alter table c_order_main add column actual_amount2 numeric;
  update c_order_main set actual_amount2=c_amount2-card_pay-discount+ship_fee;