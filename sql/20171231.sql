alter table warehouse add column working  boolean;


update warehouse set working=true;
update warehouse set working=false where wh_id in ('SUPPLY','CUSTOM','ORDER_','SUPPLY') ;

insert into sys_par values('app_name','应用程序明细','勤驿达MIS','勤驿达MIS');

alter table menu_m add column order_id char(4);
update menu_m set order_id=menu_id;


DROP VIEW v_user_menu_m;

CREATE OR REPLACE VIEW v_user_menu_m AS 
 SELECT t1.order_id, t1.menu_id, t1.menu_name, t1."desc", t2.user_id
   FROM menu_m t1, 
    ( SELECT t2.menu_id, t1.user_id
           FROM sys_user_role t1, sys_role_menu_d t2
          WHERE t1.role_id::text = t2.role_id::text
          GROUP BY t2.menu_id, t1.user_id) t2
  WHERE t1.menu_id = t2.menu_id;