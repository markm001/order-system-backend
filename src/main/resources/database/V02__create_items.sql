create table item_tags (
       item_id bigint not null,
       tags varchar(255)
);

create table items (
       id bigint not null,
       name varchar(255) not null,
       description varchar(255) not null,
       price bigint not null,
       primary key (id)
);

alter table item_tags
	add constraint FKjv1mnuei30kq5rly4y4e7wv5t
	foreign key (item_id)
	references items (id);