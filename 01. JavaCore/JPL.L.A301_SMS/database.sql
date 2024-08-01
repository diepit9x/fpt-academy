CREATE TABLE dbo.Customer(
	customer_id INT PRIMARY KEY IDENTITY(1,1),
	customer_name VARCHAR(200) NOT NULL
);

CREATE TABLE dbo.Employee(
	employee_id INT PRIMARY KEY IDENTITY(1,1),
	employee_name VARCHAR(200) NOT NULL,
	salary DECIMAL(12,2) NOT NULL DEFAULT 0,
	supervisor_id INT
);

CREATE TABLE dbo.Product(
	product_id INT PRIMARY KEY IDENTITY(1,1),
	product_name VARCHAR(200) NOT NULL,
	list_price DECIMAL(12,2) NOT NULL DEFAULT 0
);

CREATE TABLE dbo.[Order](
	order_id INT PRIMARY KEY IDENTITY(1,1),
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
	FOREIGN KEY (order_id) REFERENCES [Order](order_id),
	FOREIGN KEY (product_id) REFERENCES Product(product_id)
);

--PROCEDURE INSERT CUSTOMER
CREATE PROCEDURE dbo.Insert_Customer
	@customer_name VARCHAR(200),
	@new_customerId INT OUTPUT
AS
BEGIN
	INSERT INTO dbo.Customer(customer_name) VALUES (@customer_name);
	SELECT @new_customerId = SCOPE_IDENTITY();
END

--PROCEDURE DELETE CUSTOMER
CREATE PROCEDURE dbo.Delete_Customer
	@customer_id INT,
	@status BIT OUTPUT
AS
BEGIN
	BEGIN TRY
		BEGIN TRAN DeleteCustomer
			DELETE FROM LineItem WHERE order_id IN (
				SELECT order_id FROM dbo.[Order] WHERE customer_id = @customer_id);
			DELETE FROM dbo.[Order] WHERE customer_id = @customer_id;
			DELETE FROM dbo.Customer WHERE customer_id = @customer_id;
			SELECT @status = 1;
		COMMIT TRAN DeleteCustomer
	END TRY
	BEGIN CATCH
		ROLLBACK TRAN DeleteCustomer
		SELECT @status = 0;
	END CATCH
END


--PROCEDURE UPDATE CUSTOMER
CREATE PROCEDURE dbo.Update_Customer
	@customer_id INT,
	@customer_name VARCHAR(200),
	@status BIT OUTPUT
AS
BEGIN
	BEGIN TRY
		BEGIN TRAN UpdateCustomer
			UPDATE dbo.Customer SET customer_name = @customer_name
				WHERE customer_id = @customer_id;
			IF @@ROWCOUNT > 0
				BEGIN
					SELECT @status = 1;
				END
			ELSE
				BEGIN
					SELECT @status = 0;
				END

		COMMIT TRAN UpdateCustomer
	END TRY
	BEGIN CATCH
		ROLLBACK TRAN UpdateCustomer
		SELECT @status = 0;
	END CATCH
END

--PROCEDURE CACULATE ORDER PRICE TOTAL
CREATE PROCEDURE Order_Total
	@order_id INT,
	@total DECIMAL(15,2) OUTPUT
AS
BEGIN
	SELECT @total = SUM(price) FROM dbo.LineItem 
	WHERE order_id = @order_id 
	GROUP BY order_id;
END

-- FUNCTION CACULATE ORDER PRICE TOTAL
CREATE FUNCTION dbo.Order_Total (@order_id INT)
RETURNS TABLE
AS
	RETURN (SELECT SUM(price) as total_price FROM dbo.LineItem
		WHERE order_id = @order_id
		GROUP BY order_id);

SELECT * FROM dbo.Order_Total(5)
