create sequence hibernate_sequence start 1 increment 1;
create table product
(
    id          int8 not null,
    name        varchar(255),
    description text,
    ean         varchar(255),
    primary key (id)
);