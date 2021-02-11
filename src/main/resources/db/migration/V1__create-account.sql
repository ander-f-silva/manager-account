create table account
(
    id         varchar(36) primary key,
    document   varchar(80) not null,
    full_name  varchar(80) not null,
    amount     decimal(9, 2) not null,
    created_at datetime
) engine = innodb
  default charset = utf8;

alter table account
    add constraint constraint_document unique key (document);