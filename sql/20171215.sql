

alter table logistics_fee add  effective boolean;

update logistics_fee set effective=true;

update logistics_fee set effective=false where logistics='5000000000' and vehicle in ('AIR_','SHIP');
update logistics_fee set effective=false where logistics='5003980031' and vehicle in ('AIR_','SHIP');
update logistics_fee set effective=false where logistics='500528000A' and vehicle in ('SHIP');

create view v_logistics_vehicle_fee as
select t1.logistics_name,t2.*,t3.vehicle_name,t3.vehicle_order
from 
logistics_company t1,logistics_fee t2,logistics_vehicle t3
where t1.logistics_id=t2.logistics and t2.vehicle=t3.vehicle_id ;

delete from logistics_fee where logistics='5000000000' and vehicle='AIR_';

delete from logistics_fee where logistics='5003980031' and vehicle='AIR_';



delete from logistics_fee where  vehicle='SHIP';



