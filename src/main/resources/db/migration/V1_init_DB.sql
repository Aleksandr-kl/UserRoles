create sequence address_seq start with 1 increment by 1;
create sequence user_seq start with 1 increment by 1;
create table addresses
(
    number_apartment integer,
    number_home      integer,
    id               bigint not null,
    user_id          bigint unique,
    country          varchar(255),
    street           varchar(255),
    town             varchar(255),
    primary key (id)
);
create table users
(
    archive    boolean not null,
    address_id bigint unique,
    id         bigint  not null,
    email      varchar(255),
    name       varchar(255),
    password   varchar(255),
    role       varchar(255) check (role in ('USER', 'MANAGER', 'ADMIN')),
    primary key (id)
);
alter table if exists addresses add constraint FK_addresses_users foreign key (user_id) references users;
alter table if exists users add constraint FK_users_addresses foreign key (address_id) references addresses;