create table transaction (
    id binary(16) primary key,
    account_id binary(16) not null,
    operation enum('TRANSFER','DEPOSIT') not null,
    value double not null,
    created_at datetime
) engine=innodb default charset=utf8;

alter table transaction
    add constraint fk_transaction_account
        foreign key (account_id) references account(id);
