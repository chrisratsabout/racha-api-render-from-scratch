-- database racha
BEGIN TRANSACTION;

-- *************************************************************************************************
-- Drop all db objects in the proper order
-- *************************************************************************************************
DROP TABLE IF EXISTS order_item;
DROP TABLE IF EXISTS menu_item;


-- *************************************************************************************************
-- Create the tables and constraints
-- *************************************************************************************************
-- menu_item
CREATE TABLE menu_item (
	menu_item_id SERIAL,
	name varchar(50) NOT NULL,
    description varchar,
	price decimal(8,2) NOT NULL,
	CONSTRAINT PK_menu_item PRIMARY KEY (menu_item_id)
);

-- order item

CREATE TABLE order_item (
	order_item_id SERIAL,
	menu_item_id int NOT NULL,
	quantity int NOT NULL DEFAULT(1),
	CONSTRAINT PK_order_item PRIMARY KEY (order_item_id),
	CONSTRAINT FK_order_item_menu_item FOREIGN KEY (menu_item_id) REFERENCES menu_item(menu_item_id)
);
CREATE UNIQUE INDEX IX_order_item_menu_item ON order_item(menu_item_id);
