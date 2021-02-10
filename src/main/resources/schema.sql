drop table member if exists;

create table member (
    `id` varchar(255) not null,
    `enabled` boolean not null,
    `name` varchar(255),
    `password` varchar(255),
    `role` varchar(255),
    primary key (id)
);