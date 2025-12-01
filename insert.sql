INSERT INTO destore.Customer(forename, surname, address, total_spent) VALUES
('Nancy', 'Clark', '400, Horseferry Road, London SW1P 2AF', '235.33'), 
('Freya', 'Thompson', '15 Glenmire Terrace, Stanstead Abbotts SG12 8AD', '674.47'), 
('Keeley', 'Richardson', '12, Stanton St Quintin, Chippenham SN14 6DQ', '586.52'),
('Selina', 'Richardson', '14 Hawthorn Ave, Stone, ST15 0AX', '654.13'),
('Emma', 'Smith', '167 Lawsons Rd, Thornton-Cleveleys, FY5 4PJ', '583.59');

INSERT INTO destore.Store(store_name, store_address) VALUES('Barley','13 Telford Street'),
('Wheatacre','64 Trinity Crescent'),
('St Lawrence','59 Cloch Rd'),
('East End','16 Guildford Rd'),
('Barley','13 Telford Street');

INSERT INTO destore.Product(product_name, product_price) VALUES
('Screwdriver','23'),
('Vacuum Cleaner','109.99'),
('Lawnmower','249.99'),
('Armchair','89.99'),
('Radiator','163.16'),;

INSERT INTO destore.Sale(product_id, customer_id, store_id, sale_date) VALUES('4','2','1','2025-07-23'),
('3','2','2','2025-08-21'),
('4','1','3','2025-09-22'),
('1','1','4','2025-10-05'),
('3','3','5','2025-06-11'),
('2','2','1','2025-07-28'),
('2','2','2','2025-10-18'),
('1','3','3','2025-09-09'),
('5','2','4','2025-08-01'),
('3','5','5','2025-07-05'),
('2','4','1','2025-08-29'),
('4','5','2','2025-08-23'),
('3','1','3','2025-03-22'),
('2','2','4','2024-09-13'),
('4','3','5','2024-10-21'),
('2','4','1','2024-12-26'),
('1','4','2','2025-07-23');

INSERT INTO destore.Stock(product_id, store_id, stock) VALUES
('1','1','80'),
('1','2','3'),
('1','3','65'),
('1','4','12'),
('1','5','21'),
('1','1','54'),
('2','2','33'),
('2','3','65'),
('2','4','11'),
('2','5','15'),
('3','1','26'),
('3','2','12'),
('3','3','03'),
('3','4','17'),
('3','5','33'),
('4','1','67'),
('4','2','11'),
('4','3','45'),
('4','4','04'),
('4','5','23'),
('5','1','11'),
('5','2','56'),
('5','3','34'),
('5','4','64'),
('5','5','23');

INSERT INTO destore.Price_Control(product_id, store_id, min_quantity, rate) VALUES('1','2','2','0'),
('5','2','3','0.5')
('3','3','1','0.9')
('4','2','3','0.66')
('1','2','5','0.7');

INSERT INTO destore.Advance_Payment(product_id, customer_id, payment_rate, purchase_date, payments_made, payment_cost, complete) VALUES
('2','4', '12', '2023-03-03', '8', '23.34', '1'),
('1','2', '5', '2023-03-03', '3', '53.34', '1'),
('5','3', '20', '2025-11-25', '0', '43.34', '0');
