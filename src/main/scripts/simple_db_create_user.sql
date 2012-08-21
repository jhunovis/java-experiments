create database if not exists simple;
create user 'hibernate'@'localhost';
grant all privileges on simple.* to 'hibernate'@'localhost';
