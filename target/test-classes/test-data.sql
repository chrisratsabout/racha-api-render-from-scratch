BEGIN TRANSACTION;

DROP TABLE IF EXISTS order_item;
DROP TABLE IF EXISTS menu_item;

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

INSERT INTO menu_item (name, description, price) VALUES
    ('Hokkaido Milk Tea', 'Black milk tea w/toffee flavor + pearls', 5.75),
    ('Lavender Earl Grey Milk Tea', NULL, 5.75),
    ('Cookies n Cream', NULL, 6.00),
    ('Fresh Taro Milk Tea', NULL, 5.75),
    ('Pandan Mungbean Milk Tea', NULL, 5.50),
    ('Thai Tea', NULL, 4.50),
    ('Green Thai Tea', NULL, 4.50),
    ('Strawberry Matcha Latte', NULL, 6.25),
    ('Love Triangle', 'Strawberry, mango, and passion fruit green tea with mango jelly', 5.75),
    ('Just Peachy', 'White peach oolong tea w/lychee jelly', 5.75),
    ('Fruitea Peebles', 'Orange and pineapple black tea with mango jelly', 5.75),
    ('Fruitea Way', 'Strawberry tea w/seasonal cut up fruit', 6.25),
    ('Off To Neverland', 'Butterfly pea tea sparkling virgin mojito w/lime slices and mint', 5.25),
    ('Your Spark', 'Probiotic yogurt w/sparkling water + choice of flavor -peach, orange, mango, passion fruit, strawberry, or lychee', 5.50),
    ('Sweet N Salty', 'Sparkling Vietnamese salted plum lemonade', 5.00),
    ('Fifty/Fifty', 'Half black tea and half Vietnamese coffee', 5.00),
    ('Vietnamese Iced Coffee', 'Robusta coffee w/condensed milk', 4.50),
    ('Coconut Coffee', 'Robusta coffee w/condensed milk + coconut milk/cream', 5.25),
    ('Bánh Mì đặc biệt', 'Bbq pork, headcheese, pork bologna, and pate', 6.50),
    ('Bánh Mì Thịt Nướng', 'Choice one one: chicken, beef, pork', 6.00),
    ('Bánh Mì Bi', 'Shredded pork', 5.50),
    ('Bánh Mì Cha Lua Pate', 'Pork bologna, and pate', 6.00),
    ('Bánh Mì Xiu Mai', 'Pork meatballs w/cucumber + green onions', 6.00),
    ('Bánh Mì Chay', 'Bbq bean curd', 6.00)
   ;

INSERT INTO order_item (menu_item_id, quantity) VALUES
    (1, 1),
    (2, 2),
    (20, 1);

COMMIT;
