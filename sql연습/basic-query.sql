select version() ,current_date, now()
from dual;

select 1+2
from dual;


create table pet (
	name varchar(100),
    owner varchar(20),
    species varchar(20),
    gender char(1),
    birth date,
    death date
);

drop table pet;

show tables;

insert into pet 
values ('강방디','강소리','dog','f','2012-03-24',null);

select * from pet;




