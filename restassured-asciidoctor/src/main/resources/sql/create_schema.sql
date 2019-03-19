drop table note if exists;
create sequence hibernate_sequence start with 1 increment by 1;
create table note (id bigint auto_increment not null, title varchar(255), details varchar(255), primary key (id));
