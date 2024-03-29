CREATE TABLE PRODUCT (
SKU VARCHAR(10)  NOT NULL PRIMARY KEY,
TITLE VARCHAR(30) NOT NULL, 
DESCRIPTION VARCHAR(100) NOT NULL,
SALE_PRICE  DECIMAL(30,2) NOT NULL DEFAULT 0,
COLOR VARCHAR(32) NOT NULL,
CATEGORY VARCHAR(30) NOT NULL,
PRODUCT_SIZE INTEGER NOT NULL,
IN_STOCK INTEGER NOT NULL,
PURCHASE_PRICE DECIMAL(30,2) NOT NULL DEFAULT 0);

CREATE TABLE USERS (
EMAIL_ADDRESS VARCHAR(45) NOT NULL PRIMARY KEY,
FIRST_NAME VARCHAR(30) NOT NULL,
LAST_NAME VARCHAR(30) NOT NULL,
PASSCODE VARCHAR(30) NOT NULL,
PHONE_NUMBER VARCHAR(30) NOT NULL,
ACCESS_PRIVILEGES INTEGER NOT NULL);

CREATE TABLE PAYMENT (
PAYMENT_METHOD_NAME VARCHAR(30) NOT NULL PRIMARY KEY,
ADDRESS_LINE_ONE VARCHAR(30) NOT NULL,
ADDRESS_LINE_TWO VARCHAR(30) NOT NULL,
CITY VARCHAR(20) NOT NULL,
STATE_ VARCHAR(20) NOT NULL,
ZIP VARCHAR(20) NOT NULL,
EXPIRATION_MONTH INTEGER NOT NULL,
EXPIRATION_YEAR INTEGER NOT NULL,
SECURITY_CODE INTEGER NOT NULL,
EMAIL_ADDRESS VARCHAR(45) NOT NULL,
FOREIGN KEY(EMAIL_ADDRESS) REFERENCES USERS);

CREATE TABLE ORDERS (
ORDER_NUMBER VARCHAR(30) NOT NULL PRIMARY KEY,
TOTAL_REVENUE DECIMAL(30,2) NOT NULL DEFAULT 0,
TOTAL_DISCOUNT DECIMAL(30,2) NOT NULL DEFAULT 0,
TOTAL_COST DECIMAL(30,2) NOT NULL DEFAULT 0,
ORDER_DATE DATE NOT NULL,
EMAIL_ADDRESS VARCHAR(45) NOT NULL,
PAYMENT_METHOD_NAME VARCHAR(30) NOT NULL,
FOREIGN KEY (EMAIL_ADDRESS) REFERENCES USERS,
FOREIGN KEY (PAYMENT_METHOD_NAME) REFERENCES PAYMENT);

