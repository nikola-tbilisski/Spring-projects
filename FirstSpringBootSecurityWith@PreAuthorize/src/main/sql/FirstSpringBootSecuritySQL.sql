create table Person(
                       id int primary key generated by default as identity ,
                       username varchar(100) not null ,
                       year_of_birth int not null ,
                       password varchar not null
);

insert into Person(username, year_of_birth, password) VALUES ('Zhora', 1960, 'test_password');
insert into Person(username, year_of_birth, password) VALUES ('Nikola', 1976, 'test_password');