create table if not exists Taco_Order (
  id identity,
  delivery_Name varchar(50) not null,
  delivery_Street varchar(50) not null,
  delivery_City varchar(50) not null,
  delivery_State varchar(2) not null,
  delivery_Zip varchar(10) not null,
  cc_number varchar(16) not null,
  cc_expiration varchar(5) not null,
  cc_cvv varchar(3) not null,
  placed_at timestamp not null
);

create table if not exists Taco (
  id identity,
  name varchar(50) not null,
  taco_order bigint not null,
  taco_order_key bigint not null,
  created_at timestamp not null
);

create table if not exists Users (
  id identity,
  username varchar(50) not null,
  password varchar(50) not null,
  fullname varchar(50) not null,
  street varchar(50) not null,
  city varchar(50) not null,
  state varchar(50) not null,
  zip varchar(50) not null,
  phone_Number varchar(50) not null
);

create table if not exists Ingredient_Ref (
  id identity,
  ingredient varchar(4) not null,
  taco bigint not null,
  taco_key bigint not null
);


create table if not exists Ingredient (
  id varchar(4) not null,
  name varchar(25) not null,
  type varchar(10) not null
);


ALTER TABLE Taco ADD FOREIGN KEY (taco_order) REFERENCES Taco_Order(id);
ALTER TABLE Ingredient ADD PRIMARY KEY(ID);
ALTER TABLE Ingredient_Ref ADD FOREIGN KEY (ingredient) REFERENCES Ingredient(id);
