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

insert into user_table (id, gold, inventory_id) values ('1', 10, 1);
insert into user_table (id, gold, inventory_id) values ('2', 100, 2);
insert into user_table (id, gold, inventory_id) values ('3', 5, 3);
insert into user_table (id, gold, inventory_id) values ('4', 10, 4);
insert into user_table (id, gold, inventory_id) values ('5', 100, 5);

insert into stock (stock_id, end_date, start_date) values ('1', '2020-04-1', '2020-03-1');
insert into stock (stock_id, end_date, start_date) values ('2', '2020-06-1', '2020-05-1');

insert into action (action_id,action_name) values (1, 'make_gift');
insert into action (action_id,action_name) values (2, 'sleep');

insert into stocks_actions (stock_id, action_id) values (1,2);
insert into stocks_actions (stock_id, action_id) values (2,1);

