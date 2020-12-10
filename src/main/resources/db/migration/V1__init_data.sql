create table authors (id bigserial primary key, title varchar(255));
insert into authors (title)
values
('Food'),
('Electronic');

create table books (id bigserial primary key, title varchar(255), price int, author_id bigint references authors (id));
insert into books (title, price, author_id)
values
('Harry Potter', 95, 1),
('Bread', 25, 1),
('Cheese', 360, 1);