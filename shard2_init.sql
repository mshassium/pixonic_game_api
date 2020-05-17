create table if not exists action
(
    action_id bigserial not null
        constraint action_pkey
            primary key,
    action_name varchar(255)
);

alter table action owner to postgres;

create table if not exists inventory
(
    inventory_id bigserial not null
        constraint inventory_pkey
            primary key
);

alter table inventory owner to postgres;

create table if not exists item
(
    id bigserial not null
        constraint item_pkey
            primary key,
    name varchar(255),
    price bigint,
    type varchar(255)
);

alter table item owner to postgres;

create table if not exists inventory_items
(
    inventory_id bigint not null
        constraint fk5uqjfer6outi5qdotlwrmhq4r
            references inventory,
    item_id bigint not null
        constraint fkejk52ncf68hfkh720evu048da
            references item
);

alter table inventory_items owner to postgres;

create table if not exists stock
(
    stock_id varchar(255) not null
        constraint stock_pkey
            primary key,
    end_date timestamp,
    start_date timestamp
);

alter table stock owner to postgres;

create table if not exists stocks_actions
(
    stock_id varchar(255) not null
        constraint fkeoh5aybv01oaqeqp2t6r1cr9f
            references stock,
    action_id bigint not null
        constraint fk1vdetvx4gcfb0f4mvnfy3enxh
            references action,
    constraint stocks_actions_pkey
        primary key (stock_id, action_id)
);

alter table stocks_actions owner to postgres;

create table if not exists user_table
(
    id varchar(255) not null
        constraint user_table_pkey
            primary key,
    gold bigint,
    constraint user_id_check CHECK ( gold >1000 AND gold <=2000 ),
    inventory_id bigint
        constraint fk8ric5pasdln2mhrgpj65m7t2f
            references inventory
);

alter table user_table owner to postgres;
commit;

insert into item (id, name, price, type)values (1, 'valentine', 3, 'gift');
insert into item (id, name, price, type) values (2, 'knife', 5, 'weapon');
insert into item (id, name, price, type) values (3, 'helmet', 25, 'armor');
insert into item (id, name, price, type) values (4, 'horse', 40, 'vehicle');
insert into item (id, name, price, type) values (5, 'chamomile', 2, 'plant');

insert into inventory (inventory_id) values (1);
insert into inventory (inventory_id) values (2);
insert into inventory (inventory_id) values (3);
insert into inventory (inventory_id) values (4);
insert into inventory (inventory_id) values (5);

insert into inventory_items (inventory_id, item_id) values (1, 1);
insert into inventory_items (inventory_id, item_id) values (1, 2);
insert into inventory_items (inventory_id, item_id) values (1, 3);
insert into inventory_items (inventory_id, item_id) values (1, 4);
insert into inventory_items (inventory_id, item_id) values (1, 5);
insert into inventory_items (inventory_id, item_id) values (2, 1);
insert into inventory_items (inventory_id, item_id) values (2, 5);
insert into inventory_items (inventory_id, item_id) values (3, 3);
insert into inventory_items (inventory_id, item_id) values (4, 3);
insert into inventory_items (inventory_id, item_id) values (5, 2);

insert into stock (stock_id, end_date, start_date) values ('1', '2020-04-1', '2020-03-1');
insert into stock (stock_id, end_date, start_date) values ('2', '2020-06-1', '2020-05-1');

insert into action (action_id,action_name) values (1, 'make_gift');
insert into action (action_id,action_name) values (2, 'sleep');

insert into stocks_actions (stock_id, action_id) values (1,2);
insert into stocks_actions (stock_id, action_id) values (2,1);

