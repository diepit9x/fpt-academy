CREATE TABLE dbo.Customer(
	customer_id INT PRIMARY KEY,
	customer_name VARCHAR(200) NOT NULL,
);

CREATE TABLE dbo.Employee(
	employee_id INT PRIMARY KEY,
	employee_name VARCHAR(200) NOT NULL,
	salary DECIMAL(12,2) NOT NULL DEFAULT 0,
	supervisor_id INT
);

CREATE TABLE dbo.Product(
	product_id INT PRIMARY KEY,
	product_name VARCHAR(200) NOT NULL,
	list_price DECIMAL(12,2) NOT NULL DEFAULT 0
);

CREATE TABLE dbo.[Order](
	order_id INT PRIMARY KEY,
	order_date DATE NOT NULL,
	customer_id INT NOT NULL,
	employee_id INT NOT NULL,
	total DECIMAL(15,2) NOT NULL,
	FOREIGN KEY (customer_id) REFERENCES Customer(customer_id),
	FOREIGN KEY (employee_id) REFERENCES Employee(employee_id)
);

CREATE TABLE dbo.LineItem(
	order_id INT NOT NULL,
	product_id INT NOT NULL,
	quantity INT NOT NULL CHECK(quantity > 0),
	price DECIMAL(12,2) NOT NULL DEFAULT 0,
	PRIMARY KEY (order_id, product_id),
	FOREIGN KEY (order_id) REFERENCES Orders(order_id),
	FOREIGN KEY (product_id) REFERENCES Product(product_id)
);