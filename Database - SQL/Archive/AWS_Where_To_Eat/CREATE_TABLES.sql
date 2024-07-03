
DROP TABLE Restaurants;

CREATE TABLE Restaurants  (
  Restaurant_ID INT AUTO_INCREMENT  NOT NULL,
  Restaurant_Name VARCHAR(75) NOT NULL,
	Days_Open VARCHAR(50) NOT NULL,
	Hours_Open VARCHAR(50) NOT NULL,
	Address_Line_One VARCHAR(50) NOT NULL,
	Address_Line_Two VARCHAR(50) NOT NULL,
	City VARCHAR(50) NOT NULL,
	State CHAR(2) NOT NULL,
	Zipcode CHAR(5) NOT NULL,
	Phone_Number CHAR(10) NOT NULL,
	Website VARCHAR(100) NOT NULL,
	Breakfast CHAR(1) NOT NULL,
	Lunch CHAR(1) NOT NULL,
	Dinner CHAR(1) NOT NULL,
  CONSTRAINT PK_Restaurants PRIMARY KEY (Restaurant_ID)
  );