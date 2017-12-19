alter table deliver_d drop column num;
alter table sys_par add column select_items varchar(1024);

update sys_par set select_items='';

delete from sys_par where code='interval_time';
insert into sys_par values('easyui_ver','easyui所使用的版本','1.5.3','1.3.2,1.5.3');
insert into sys_par values('interval_time','刷新后台保持在线的秒数','300','60,300,900,1800');
insert into sys_par values('jquery_ui_ver','jquery ui所使用的版本','1.12.1','1.12.1');
insert into sys_par values('jquery_ver','jquery所使用的版本','2.1.4','1.11.3,2.1.4,3.2.1');
insert into sys_par values('temp_save_time','录入界面的暂存时间，分钟','5','1,5,10,15');

