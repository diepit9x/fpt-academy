USE FINAL_DB;

CREATE TABLE Monitor(
	monitorId VARCHAR(7) PRIMARY KEY,
	[type] INT NOT NULL CHECK(type BETWEEN 1 AND 3),
	name VARCHAR(200) NOT NULL,
	brand VARCHAR(200) NOT NULL,
	size VARCHAR(200) NOT NULL,
	resolution VARCHAR(200) NOT NULL,
	panel VARCHAR(200) NOT NULL,
	importDate DATE NOT NULL,
	warrantYear INT NOT NULL CHECK(warrantYear > 0),
	price INT NOT NULL,
	touchScreen VARCHAR(200),
	sRGB VARCHAR(200),
	adobeRGB VARCHAR(200),
	refreshRate VARCHAR(200),
	responseTime VARCHAR(200)
);