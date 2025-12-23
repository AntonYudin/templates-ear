

drop database if exists "default";

drop user if exists "default" ;
create user "default" with encrypted password 'default' createdb;

\c template1 default

create database "default" with encoding = 'utf-8';


