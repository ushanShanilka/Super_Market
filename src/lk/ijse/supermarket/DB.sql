drop database supermarket;
create database supermarket;

use supermarket;

create table User(
	id int (50) primary key,
	name varchar (20) not null,
	password varchar (20) not null,
    active_state BOOLEAN,
	userType  varchar (45),
	constraint id unique(id)
);

create table Customer(
	id Varchar (50) primary key,
	customerType VARCHAR (45),
	name varchar (20) not null,
	address VARCHAR (45),
	city VARCHAR (45),
	province VARCHAR (45),
	contact int (11)
);

create table Product(
	id Varchar (50) primary key,
	name varchar (20) not null,
	description VARCHAR (45),
	specification VARCHAR (45),
	displayName VARCHAR (45),
	available BOOLEAN,
	activeState BOOLEAN,
	availableBrands VARCHAR (45)
);

CREATE TABLE Batch(
    propertyId VARCHAR (45) primary key,
    batch VARCHAR (45) not null,
    price DECIMAL (10,2) not null,
    discountState BOOLEAN,
    discount DECIMAL (10,2),
    activeState BOOLEAN,
    quantity int (100),
    systemDate VARCHAR (45),
    productId VARCHAR (50) not null,
    CONSTRAINT FOREIGN KEY(productId) REFERENCES Product(id) on Delete Cascade on Update Cascade
);

CREATE TABLE Orders(
	id VARCHAR(60) NOT NULL,
	date VARCHAR(200),
	totalCost DECIMAL (10,2),
	customerId VARCHAR(60) ,
	userId int(60) NOT NULL,
	CONSTRAINT PRIMARY KEY (id),
	CONSTRAINT FOREIGN KEY(customerId) REFERENCES Customer(id) on Delete Cascade on Update Cascade,
	CONSTRAINT FOREIGN KEY(userId) REFERENCES User(id) on Delete Cascade on Update Cascade
);

Create TABLE orderDetail(
    qty int not null,
    unitPrice DECIMAL (10,2) not null,
    orderId VARCHAR (50) not null,
    propId VARCHAR (50) not null,
    CONSTRAINT FOREIGN KEY(orderId) REFERENCES Orders(id) on Delete Cascade on Update Cascade,
    CONSTRAINT FOREIGN KEY(propId) REFERENCES Batch(propertyId) on Delete Cascade on Update Cascade
);