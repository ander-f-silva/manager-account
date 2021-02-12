insert into account (id, document, full_name, amount, created_at)
values ('4c6e76dc-64e6-11eb-ae93-0242ac130002', 'Mzk2NzA4OTkwODc=', 'Patricia Santos', 0.00, now());
insert into transaction (id, account_id, operation, value, created_at)
values ('d17e21ba-64e6-11eb-ae93-0242ac130002', '4c6e76dc-64e6-11eb-ae93-0242ac130002', 'DEPOSIT', 2000.00, now());
insert into account (id, document, full_name, amount, created_at)
values ('07a8270e-6bef-11eb-9439-0242ac130002', 'Mzc2OTI1MDEwOTI=', 'Andre da Silva', 0.00, now());
insert into account (id, document, full_name, amount, created_at)
values ('16ad4512-6bfa-11eb-9439-0242ac130002', 'Mzk4MjgwODIwMDQ=', 'Pedro de Souza', 2000.00, now());

