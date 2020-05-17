create table if not exists action
(
    action_id   bigserial not null
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
    id    bigserial not null
        constraint item_pkey
            primary key,
    name  varchar(255),
    price bigint,
    type  varchar(255)
);

alter table item owner to postgres;

create table if not exists inventory_items
(
    inventory_id bigint not null
        constraint fk5uqjfer6outi5qdotlwrmhq4r
            references inventory,
    item_id      bigint not null
        constraint fkejk52ncf68hfkh720evu048da
            references item
);

alter table inventory_items owner to postgres;

create table if not exists stock
(
    stock_id   varchar(255) not null
        constraint stock_pkey
            primary key,
    end_date   timestamp,
    start_date timestamp
);

alter table stock owner to postgres;

create table if not exists stocks_actions
(
    stock_id  varchar(255) not null
        constraint fkeoh5aybv01oaqeqp2t6r1cr9f
            references stock,
    action_id bigint       not null
        constraint fk1vdetvx4gcfb0f4mvnfy3enxh
            references action,
    constraint stocks_actions_pkey
        primary key (stock_id, action_id)
);

alter table stocks_actions owner to postgres;

create
extension postgres_fdw;

create
server shard1_server
    foreign data wrapper postgres_fdw
    options (host'shard1_db', port'5432',dbname'postgres');

create user mapping for postgres
    server shard1_server
    options (user 'postgres', password 'shard1_db');

create
server shard2_server
    foreign data wrapper postgres_fdw
    options (host'shard2_db', port'5432',dbname'postgres');

create user mapping for postgres
    server shard2_server
    options (user 'postgres', password 'shard2_db');

create
foreign table if not exists user_table_1
    (
        id varchar(255) not null,
        gold bigint,
        inventory_id bigint
        )
    server shard1_server
    options (schema_name'public', table_name'user_table');

create
foreign table if not exists user_table_2
    (
        id varchar(255) not null,
        gold bigint,
        inventory_id bigint
        )
    server shard2_server
    options (schema_name'public', table_name'user_table');

create view user_table as
select *
from user_table_1
union all
select *
from user_table_2;
create
rule new_insert_to_shard_1 as on
insert to user_table
where (gold >= 0
  and gold <= 1000)
    do instead
insert into user_table_1
values (new.*);
create
rule new_insert_to_shard_2 as on
insert to user_table
where (gold
    > 1000)
    do instead
insert into user_table_2
values (new.*);
create
rule user_insert as on
insert to user_table
    do instead nothing;
create
rule user_update as on
update to user_table
    do instead nothing;
create
rule user_delete as on
delete to user_table
    do instead nothing;
commit;
insert into item (id, name, price, type)
values (1, 'valentine', 3, 'gift');
insert into item (id, name, price, type)
values (2, 'knife', 5, 'weapon');
insert into item (id, name, price, type)
values (3, 'helmet', 25, 'armor');
insert into item (id, name, price, type)
values (4, 'horse', 40, 'vehicle');
insert into item (id, name, price, type)
values (5, 'chamomile', 2, 'plant');

insert into inventory (inventory_id)
values (1);
insert into inventory (inventory_id)
values (2);
insert into inventory (inventory_id)
values (3);
insert into inventory (inventory_id)
values (4);
insert into inventory (inventory_id)
values (5);

insert into inventory_items (inventory_id, item_id)
values (1, 1);
insert into inventory_items (inventory_id, item_id)
values (1, 2);
insert into inventory_items (inventory_id, item_id)
values (1, 3);
insert into inventory_items (inventory_id, item_id)
values (1, 4);
insert into inventory_items (inventory_id, item_id)
values (1, 5);
insert into inventory_items (inventory_id, item_id)
values (2, 1);
insert into inventory_items (inventory_id, item_id)
values (2, 5);
insert into inventory_items (inventory_id, item_id)
values (3, 3);
insert into inventory_items (inventory_id, item_id)
values (4, 3);
insert into inventory_items (inventory_id, item_id)
values (5, 2);

insert into stock (stock_id, end_date, start_date)
values ('1', '2020-04-1', '2020-03-1');
insert into stock (stock_id, end_date, start_date)
values ('2', '2020-06-1', '2020-05-1');

insert into action (action_id, action_name)
values (1, 'make_gift');
insert into action (action_id, action_name)
values (2, 'sleep');

insert into stocks_actions (stock_id, action_id)
values (1, 2);
insert into stocks_actions (stock_id, action_id)
values (2, 1);

insert into user_table (id, gold, inventory_id)
values ('1', 100, 1);
insert into user_table (id, gold, inventory_id)
values ('2', 1001, 2);
insert into user_table (id, gold, inventory_id)
values ('3', 1002, 3);
commit;
