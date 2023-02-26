drop database if exists andesitemall;
drop user if exists 'andesitemall'@'%';


create database andesitemall default character set utf8mb4 collate utf8mb4_unicode_ci;
use andesitemall;
create user 'andesitemall'@'%' identified by 'andesitemallDefault';
grant all privileges on andesitemall.* to 'andesitemall'@'%';
flush privileges;