create table authors (id bigserial primary key, name varchar(255));
insert into authors (name)
values
('Joanne Rowling'),
('Uncle Bob');

create table books (id bigserial primary key, title varchar(255), price int, author_id bigint references authors (id));
insert into books (title, price, author_id)
values
('Harry Potter', 95, 1),
('Clean Code', 25, 2),
('Fantastic Beasts and Where to Find Them', 360, 1);