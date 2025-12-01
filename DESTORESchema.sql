CREATE DATABASE destore;

CREATE TABLE destore.Product(
	product_id int NOT NULL AUTO_INCREMENT PRIMARY KEY,
	product_name VARCHAR(100),
	product_price FLOAT
);

CREATE TABLE destore.Store(
	store_id int NOT NULL AUTO_INCREMENT PRIMARY KEY,
	store_name VARCHAR(100),
	store_address VARCHAR(100)
);

CREATE TABLE destore.Customer(
	customer_id int NOT NULL AUTO_INCREMENT PRIMARY KEY,
	forename VARCHAR(100),
	surname VARCHAR(100),
	address VARCHAR(100),
	total_spent FLOAT
);

CREATE TABLE destore.Stock(
	stock_id int NOT NULL AUTO_INCREMENT PRIMARY KEY,
	product_id int NOT NULL,
	store_id int NOT NULL,
	stock int,
	FOREIGN KEY (product_id) REFERENCES Product(product_id),
	FOREIGN KEY (store_id) REFERENCES Store(store_id)
);

CREATE TABLE destore.Price_Control(
	price_control_id int NOT NULL AUTO_INCREMENT PRIMARY KEY,
	product_id int NOT NULL,
	store_id int NOT NULL,
	min_quantity int,
	rate FLOAT,
	FOREIGN KEY (product_id) REFERENCES Product(product_id),
	FOREIGN KEY (store_id) REFERENCES Store(store_id)
);

CREATE TABLE destore.Advance_Payment(
	advance_payment_id int NOT NULL AUTO_INCREMENT PRIMARY KEY,
	product_id INT NOT NULL,
	customer_id INT NOT NULL,
	payment_rate INT,
	purchase_date DATETIME,
	payments_made INT,
	payment_cost FLOAT,
	complete BOOLEAN,
	FOREIGN KEY (product_id) REFERENCES Product(product_id),
	FOREIGN KEY (customer_id) REFERENCES Customer(customer_id)
);

CREATE TABLE destore.Sale (
  sale_id int NOT NULL AUTO_INCREMENT PRIMARY KEY,
  product_id int NOT NULL,
  customer_id int NOT NULL,
  store_id int NOT NULL,
  sale_date date NOT NULL,
)

