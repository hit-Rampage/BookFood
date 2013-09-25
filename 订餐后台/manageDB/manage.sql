create  database manage;
use manage;
create table Admin(
	AdminId int auto_increment primary key,
	LoginName varchar(50),
	LoginPwd varchar(50)
);
create table User(
	UserId int auto_increment primary key,
	UserName varchar(50),
	Password varchar(50),
	UserState varchar(50)
);
create table Deliver(
	DeliverId int auto_increment primary key,
	UserName varchar(50),
	Address varchar(255),
	Telephone varchar(20),
	OrderTime varchar(50),
	OrderState varchar(20),
	OrderPrice double
);
create table OrderItem(
	ItemId int auto_increment primary key,
	DeliverId int,
	DishName varchar(50),
	DishCount int
);
create table Dish(
	DishId int auto_increment primary key,
	DishName varchar(50),
	DishSeries varchar(50),
	DishImg varchar(100),
	DishPrice double
);
create table Feedback(
	MsgId int auto_increment primary key,
	UserName varchar(50),
	SendNote varchar(1024),
	SendTime varchar(100)
);

