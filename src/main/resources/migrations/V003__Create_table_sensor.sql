create table sensor
(
    id              serial,
    name    varchar(255)         not null,
    model   varchar(255)         not null,
    range_id   bigint            not null,
    type_id    bigint            not null,
    unit_id    bigint            not null,
    Location   varchar(40)       not null,
    Description    varchar(40)   not null,
    primary key (id),
    foreign key (range_id) references dictionary_code (id),
    foreign key (type_id) references dictionary_code (id),
    foreign key (unit_id) references dictionary_code (id)
);