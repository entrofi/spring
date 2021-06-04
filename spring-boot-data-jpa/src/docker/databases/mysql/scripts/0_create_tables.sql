create table product
(
    id          bigint not null,
    name        varchar(255),
    description text,
    ean         varchar(255),
    primary key (id)
);