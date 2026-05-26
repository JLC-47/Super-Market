create database micro_market;
use micro_market;

create table categories (
    id int primary key auto_increment,
    name varchar(100) not null,
    description varchar(255)
);


create table products (
    id int primary key auto_increment,
    name varchar(150) not null,
    description varchar(255),
    barcode varchar(100) not null unique,
    price decimal(10,2) not null,
    stock int not null default 0,
    status boolean default true,
    id_category int not null,
    foreign key (id_category) references categories(id)
);

create table suppliers (
    id int primary key auto_increment,
    name varchar(150) not null,
    tax_id varchar(50) not null unique,
    phone varchar(30),
    address varchar(255),
    email varchar(150)
);


create table product_supplier (
    id_producto int not null,
    id_supplier int not null,
    primary key (id_producto, id_supplier),
    foreign key (id_producto) references products(id),
    foreign key (id_supplier) references suppliers(id)
);



create table employees (
    id int primary key auto_increment,
    national_id varchar(20) not null unique,
    name varchar(150) not null,
    role enum('administrador', 'cajero', 'auxiliar') not null,
    hire_date date not null,
    salary decimal(10,2) not null
);



create table sales (
    id int primary key auto_increment,
    sale_date datetime default current_timestamp,
    subtotal decimal(10,2) not null,
    vat decimal(10,2) not null,
    total decimal(10,2) not null,
    id_employee int not null,
    foreign key (id_employee) references employees(id)
);


create table sale_details (
    id int primary key auto_increment,
    id_sale int not null,
    id_producto int not null,
    quantity int not null,
    unit_price decimal(10,2) not null,
    subtotal decimal(10,2) not null,
    foreign key (id_sale) references sales(id),
    foreign key (id_producto) references products(id)
);

