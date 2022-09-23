create table order_items (
       id bigint not null,
       order_id bigint not null,
       item_id bigint not null,
       quantity integer not null
);

create table orders (
       id bigint not null,
       user_id bigint not null,
       order_time datetime(6) not null,
       order_status varchar(255) not null,
       primary key (id)
);

alter table order_items
   add constraint order_items_order_id_fk
   foreign key (order_id)
   references orders (id);

alter table order_items
   add constraint order_items_item_id_fk
   foreign key (item_id)
   references items (id);

alter table orders
   add constraint orders_user_id_fk
   foreign key (user_id)
   references users (id);