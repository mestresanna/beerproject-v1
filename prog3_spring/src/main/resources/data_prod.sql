
INSERT INTO demo_orm.BEER (NAME, ABV, PLATO, STYLE, QUANTITY, STOCK, containers, BREWERY, PRICE, image_url) VALUES ('Puck v6', 5.5, 12, 'Fruit Sour', 'BELGIAN', 120, 'CAN', 'Kaapse', 2.50, '/images/beer-can.png');
INSERT INTO demo_orm.BEER (NAME, ABV, PLATO, STYLE, QUANTITY, STOCK, containers, BREWERY, PRICE, image_url) VALUES ('Swiped Out', 8.8, 19, 'Double IPA', 'BOMBER', 96, 'BOTTLE', 'Frau Gruber', 3.80, '/images/beer-bottle.png');
INSERT INTO demo_orm.BEER (NAME, ABV, PLATO, STYLE, QUANTITY, STOCK, containers, BREWERY, PRICE, image_url) VALUES ( 'Phuture 2', 0.3, 15, 'IPA', 'STUBBY', 108, 'CAN', 'Cloudwater', 3.55, '/images/beer-can.png');
INSERT INTO demo_orm.BEER (NAME, ABV, PLATO, STYLE, QUANTITY, STOCK, containers, BREWERY, PRICE, image_url) VALUES ('The Dreamer', 6.6, 16, 'West Coast IPA', 'TALLBOY', 4, 'KEG', 'Oddity', 110.45, '/images/beer-keg.png');

INSERT INTO demo_orm.CUSTOMER (CONTACT, company_name, ADDRESS, EMAIL, PHONE, image_url) VALUES ('Brandy', 'HoppingBillie''s', 'kerkstraat 35, Antwerp', 'brandy@hb.com', '+32456789808', '/images/person.png');
INSERT INTO demo_orm.CUSTOMER (CONTACT, company_name, ADDRESS, EMAIL, PHONE, image_url) VALUES ('Zinke', 'Binoche', 'Wetstraat 67, Antwerp', 'zinke@binoche.com', '+32564789832','/images/person.png' );
INSERT INTO demo_orm.CUSTOMER ( CONTACT, company_name, ADDRESS, EMAIL, PHONE, image_url) VALUES ( 'Ben', 'BeerLovers', 'Rotterdamstraat 105, Antwerp', 'Ben@Beerloversbar.be', '+32(0)497.47.26.20','/images/person.png');

--INSERT INTO demo_orm.orders (date, id_customer, total, comments, image_url) VALUES ('2025-01-01', 1,  10, 'Do not deliver on Tuesday', '/images/shopping-cart.png');

--INSERT INTO demo_orm.order_beer (beer_id, order_id, quantity) VALUES (1, 1, 4);