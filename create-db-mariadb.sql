
drop database `default`;
create database `default`;
drop user 'default'@'%';
create user 'default'@'%' identified by 'default';
grant all privileges on `default`.* to 'default'@'%';

