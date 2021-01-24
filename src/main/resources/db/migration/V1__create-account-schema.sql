create table account (
    id binary(16) primary key,
    document varchar(11) not null,
    fullname varchar(80) not null,
    amount double not null,
    created_at datetime
) engine=innodb default charset=utf8;