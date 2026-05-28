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



INSERT INTO categories (name, description) VALUES
('Electrónica', 'Dispositivos electrónicos, gadgets y tecnología'),
('Lácteos', 'Productos derivados de la leche y refrigerados'),
('Abarrotes', 'Productos alimenticios secos y de primera necesidad'),
('Limpieza', 'Artículos para el aseo del hogar y desinfección'),
('Bebidas', 'Jugos, refrescos, aguas y licores');


INSERT INTO products (name, description, barcode, price, stock, status, id_category) VALUES
('Teclado Mecánico RGB', 'Teclado para computadora con luces y switches azules', '7501234567890', 45.00, 15, true, 1),
('Leche Entera 1L', 'Leche pasteurizada de vaca', '7501234567891', 1.50, 50, true, 2),
('Arroz Blanco 1kg', 'Arroz grano largo de alta calidad', '7501234567892', 2.10, 100, true, 3),
('Detergente Líquido 3L', 'Jabón líquido para ropa blanca y de color', '7501234567893', 8.50, 20, true, 4),
('Refresco de Cola 2L', 'Bebida carbonatada refrescante', '7501234567894', 1.80, 80, true, 5),
('Mouse Inalámbrico', 'Mouse óptico ergonómico 2.4Ghz', '7501234567895', 15.00, 30, true, 1);


INSERT INTO suppliers (name, tax_id, phone, address, email) VALUES
('TechDistribuidora S.A.', 'TAX-112233-A', '+57 300 123 4567', 'Calle 45 #12-34, Bogotá', 'ventas@techdist.com'),
('Lácteos del Campo', 'TAX-445566-B', '+57 311 987 6543', 'Km 5 Vía al Mar, Medellín', 'pedidos@lacteoscampo.com'),
('Distribuidora Global Alimentos', 'TAX-778899-C', '+57 322 555 1122', 'Av. Central #50-10, Cali', 'contacto@globalalimentos.com'),
('Químicos y Limpieza Express', 'TAX-001122-D', '+57 315 444 8899', 'Zona Industrial Lote 4, Barranquilla', 'info@limpiezaexpress.com');


INSERT INTO product_supplier (id_product, id_supplier) VALUES
(1, 1), 
(6, 1), 
(2, 2), 
(3, 3), 
(5, 3), 
(4, 4); 


INSERT INTO employees (national_id, name, role, hire_date, salary) VALUES
('10012345', 'Carlos Mendoza', 'administrador', '2023-01-15', 1200.00),
('10054321', 'Ana Gómez', 'cajero', '2024-02-10', 600.00),
('10098765', 'Luis Martínez', 'cajero', '2024-05-20', 600.00),
('10045678', 'Sofía Rojas', 'auxiliar', '2023-11-01', 500.00);


INSERT INTO sales (sale_date, subtotal, vat, total, id_employee) VALUES
('2026-05-25 10:30:00', 46.50, 8.84, 55.34, 2), 
('2026-05-26 15:45:12', 5.10, 0.97, 6.07, 2),  
('2026-05-27 18:20:00', 15.00, 2.85, 17.85, 3); 


INSERT INTO sale_details (id_sale, id_product, quantity, unit_price, subtotal) VALUES
(1, 1, 1, 45.00, 45.00),
(1, 2, 1, 1.50, 1.50);


INSERT INTO sale_details (id_sale, id_product, quantity, unit_price, subtotal) VALUES
(2, 3, 1, 2.10, 2.10),
(2, 5, 2, 1.50, 3.00);


INSERT INTO sale_details (id_sale, id_product, quantity, unit_price, subtotal) VALUES
(3, 6, 1, 15.00, 15.00);
