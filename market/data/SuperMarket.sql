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
    id_product int not null,
    id_supplier int not null,
    primary key (id_product, id_supplier),
    foreign key (id_product) references products(id),
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
    id_product int not null,
    quantity int not null,
    unit_price decimal(10,2) not null,
    subtotal decimal(10,2) not null,
    foreign key (id_sale) references sales(id),
    foreign key (id_product) references products(id)
);

INSERT INTO categories (name, description, status) VALUES ('Lácteos y Huevos', 'Productos derivados de la leche y huevos', true);
INSERT INTO categories (name, description, status) VALUES ('Carnes', 'Carnes rojas, blancas y embutidos', true);
INSERT INTO categories (name, description, status) VALUES ('Aseo Personal', 'Productos para el cuidado personal y limpieza', true);

INSERT INTO suppliers (name, nit, address, phone, status) VALUES ('Distribuidora Central', '900123456-1', 'Calle 10 # 14-25', '3101234567', true);
INSERT INTO suppliers (name, nit, address, phone, status) VALUES ('Suministros del Valle', '800987654-2', 'Carrera 15 # 20-10', '3209876543', true);

INSERT INTO products (name, price, stock, category_id, supplier_id, status) VALUES ('Leche Entera 1L', 3500.00, 50, 1, 1, true);
INSERT INTO products (name, price, stock, category_id, supplier_id, status) VALUES ('Queso Campesino 250g', 5000.00, 30, 1, 1, true);
INSERT INTO products (name, price, stock, category_id, supplier_id, status) VALUES ('Carne de Res 500g', 12000.00, 20, 2, 2, true);
INSERT INTO products (name, price, stock, category_id, supplier_id, status) VALUES ('Jabón de Baño 3 Unidades', 6000.00, 40, 3, 1, true);

INSERT INTO employees (name, document, role, status) VALUES ('Carlos Ramirez', '1094123456', 'CAJERO', true);
INSERT INTO employees (name, document, role, status) VALUES ('Laura Gomez', '1094654321', 'ADMINISTRADOR', true);