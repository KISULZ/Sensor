create table dictionary_code
(
    id              serial,
    value_set_id    uuid         not null,
    value_set_name  varchar(255) not null,
    value_set_title varchar(255) not null,
    element_id      varchar(255) not null,
    display         varchar(255) not null,
    primary key (id)
);