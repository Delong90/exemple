-- ------------------------------------------------------------------------------------------------------------------------------------------------------------------
-- СОЗДАНИЕ БАЗЫ
    
create database projectMatushkin;
use projectMatushkin;
-- ------------------------------------------------------------------------------------------------------------------------------------------------------------------
-- СОЗДАНИЕ ТАБЛИЦЫ ЮЗЕРОВ
create table users (
	`id_users` int primary key auto_increment,
	`user` varchar(50) unique,
    `password` varchar(64)
    );
    
    insert into users value(null,'Jenia','34174f01cd44f97fceceafe61d50623188a06ddb1336f5477eec6efad0785a8d');
    insert into users value(null,'Liza','ce59116024f7f4a62099327d516e999fee3c985b2cc714d808e22ca928c42124');

    
    select * from users;
-- ------------------------------------------------------------------------------------------------------------------------------------------------------------------
-- СОЗДАНИЕ ТАБЛИЦЫ ПРОДУКТОВ
    create table products (
	`id_products` int primary key auto_increment,
	`title` varchar(50),
    `cost` double precision
    );
    
    select * from products;
    
    
    insert into products value (null,'Молоко',1.25);
    insert into products value (null,'Батон',1);
    insert into products value (null,'Хлеб',1.1);
    insert into products value (null,'Макароны',0.86);
    insert into products value (null,'Гречка',1.1);
    insert into products value (null,'Кефир',1.5);
    insert into products value (null,'Мороженное',1.5);


-- ------------------------------------------------------------------------------------------------------------------------------------------------------------------
-- СОЗДАНИЕ ТАБЛИЦЫ ЗАКАЗОВ
create table orders (
	`id_orders` int primary key auto_increment,
	`id_user` int,
    foreign key (id_user) references users(id_users)
    );
    
     select * from orders;
    
     insert into orders value (null,1);
     insert into orders value (null,2);
     insert into orders value (null,2);
     insert into orders value (null,2);
    

-- ------------------------------------------------------------------------------------------------------------------------------------------------------------------
-- ЗАПОЛНЕНИЕ ТАБЛИЦЫ ЗАКАЗО И ПРОДУКТОВ    
create table order_items (
	`id_order` int,
    `id_product` int,
	`quantity` int,
    foreign key (id_product) references products(id_products),
    foreign key (id_order) references orders(id_orders)
	);
    
         insert into order_items value (1,2,1);    
         insert into order_items value (1,5,1);    
         insert into order_items value (2,1,2);    
         insert into order_items value (2,2,2);    
         insert into order_items value (3,3,2);    
         insert into order_items value (3,4,2);    
         insert into order_items value (4,5,2);    
         insert into order_items value (4,6,2);    
         insert into order_items value (4,7,2);    
    
     select * from order_items;    
    
-- ------------------------------------------------------------------------------------------------------------------------------------------------------------------
-- ВЫВОД РЕЗУЛЬТАТОВ

select o.id_orders from orders o join users u on u.id_users = 2 and o.id_user = 2;     -- все заказы юзера с id=2 видим заказы 2,3,4
select p.title,p.cost, oi.quantity from order_items oi join orders o join products p on oi.id_order = 4 and o.id_orders=4 and oi.id_product=p.id_products;     -- выводим продукты их цену и их количество по заказу номер 4
select oi.id_order, p.title,p.cost, oi.quantity from order_items oi join orders o join products p on oi.id_order=o.id_orders and oi.id_product=p.id_products;     -- выводим все заказы




-- ------------------------------------------------------------------------------------------------------------------------------------------------------------------        
-- drop table order_items;
-- drop table orders;
-- drop table products;
-- drop table users;

--     
--      select * from users;
--      select * from orders;
--      select * from order_items;
--      select * from products;
--      
--       select * from users,orders,order_items,products;
    