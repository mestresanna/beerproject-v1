DROP table if exists Beer_Order;
drop table if exists Beer;
drop table if exists Orders;
drop table if exists Customer;

CREATE TABLE Beer
(
    idBeer      INTEGER AUTO_INCREMENT PRIMARY KEY,
    name        VARCHAR(255) NOT NULL,
    abv         DOUBLE NOT NULL,
    plato       INTEGER,
    style       VARCHAR(255) NOT NULL,
    quantity    VARCHAR(255) NOT NULL,
    stock       INTEGER NOT NULL,
    container   VARCHAR(255) NOT NULL,
    brewery     VARCHAR(255) NOT NULL,
    price       DECIMAL(10, 2) NOT NULL,
    --imageUrl    VARCHAR(2083),
    CONSTRAINT Beer_pk PRIMARY KEY (idBeer),
    CONSTRAINT check_quantity
        CHECK (quantity IN ('NIP', 'STUBBY', 'BELGIAN', 'BRITISH', 'BOMBER',
                            'LARGE_FORMAT', 'CAGUAMA', 'HOWLER', 'TALLBOY', 'STOVEPIPE')),
    CONSTRAINT check_container CHECK (container IN ('BOTTLE', 'CAN', 'KEG'))
);


CREATE TABLE Customer(
    idCustomer   INTEGER AUTO_INCREMENT PRIMARY KEY,
    contact      VARCHAR(255) NOT NULL,
    companyName  VARCHAR(255) NOT NULL,
    address      VARCHAR(255) NOT NULL,
    email        VARCHAR(255) UNIQUE NOT NULL,
    phone        VARCHAR(50) NOT NULL
);

CREATE TABLE Orders
(
    idOrder    INTEGER AUTO_INCREMENT PRIMARY KEY,
    date       DATE NOT NULL,
    comments   TEXT,
    customerId INTEGER NOT NULL,
    total      DECIMAL(10, 2) NOT NULL,
    --imageUrl   VARCHAR(2083),
    FOREIGN KEY (customerId) REFERENCES Customer(idCustomer)
);

CREATE TABLE Beer_Order
(
    beerId  INTEGER NOT NULL,
    orderId INTEGER NOT NULL,
    quantity INTEGER NOT NULL,
    PRIMARY KEY (beerId, orderId),
    FOREIGN KEY (beerId) REFERENCES Beer(idBeer),
    FOREIGN KEY (orderId) REFERENCES Orders(idOrder)
);