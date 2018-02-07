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






  alter table sys_user add column last_modify_dat timestamp with time zone;
  alter table sys_user add column last_modify_uuid char(36);


CREATE OR REPLACE FUNCTION sys_user_20180119()
  RETURNS void AS
$BODY$
declare

sys_user_refcursor refcursor;  
i integer;
com_id varchar(1000);
user_id varchar(1000);

begin 
	i=0;
	 open sys_user_refcursor for execute 'select com_id,id from sys_user';  
    loop  
        fetch sys_user_refcursor into com_id,user_id;  
          
        if found then  
            raise notice '%',user_id;
            --1、跟新uuid的库位
		i=i+1;
            execute 'update sys_user set last_modify_uuid=$1,last_modify_dat=now() where com_id=$2 and id=$3 ' using uuid(i),com_id,user_id;

            
        else  
            exit;  
        end if;  
    end loop;  
    close sys_user_refcursor;  
   
	
end 
$BODY$
  LANGUAGE plpgsql VOLATILE
  COST 100;

  select sys_user_20180119();


   ALTER TABLE sys_user DROP CONSTRAINT pk_sys_user;

ALTER TABLE sys_user
  ADD CONSTRAINT pk_sys_user PRIMARY KEY(com_id,id);


  
  create view v_sys_user as
  select * from sys_user;


  alter table sys_user rename id to user_id;

  DROP VIEW v_sys_user;

CREATE OR REPLACE VIEW v_sys_user AS 
 SELECT sys_user.user_id, sys_user.name, sys_user.create_time, 
    sys_user.pwd, sys_user.user_login, sys_user.tel, sys_user.com_id, 
    sys_user.effective, sys_user.online, sys_user.last_online_time, 
    sys_user.pickup_weighting, sys_user.send_weighting, sys_user.quota_current, 
    sys_user.quota_add, sys_user.quota_subtract, sys_user.quota_freez, 
    sys_user.email, sys_user.last_modify_dat, sys_user.last_modify_uuid
   FROM sys_user;
  
create view v_user_par as
select * from user_par;






  DROP VIEW v_user_menu_m;

CREATE OR REPLACE VIEW v_user_menu_m AS 
 SELECT t1.com_id,t1.order_id, t1.menu_id, t1.menu_name, t1."desc", t2.user_id
   FROM menu_m t1, 
    ( SELECT t2.menu_id, t1.user_id
           FROM sys_user_role t1, sys_role_menu_d t2
          WHERE t1.role_id::text = t2.role_id::text
          GROUP BY t2.menu_id, t1.user_id) t2
  WHERE t1.menu_id = t2.menu_id;