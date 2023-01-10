drop table member;
create table member(
	no int not null auto_increment,
    email varchar(100) not null,
    password varchar(64) not null,
    name varchar(100) not null,
    department varchar(100),
    primary key(no)
);

desc member;

alter table member add column juminbunho char(13) not null;
desc member;

alter table member drop juminbunho ;
desc member;

alter table member add column juminbunho char(13) not null after email;
desc member;

alter table member change column department dept varchar(200) not null;
desc member;

alter table member add column self_intro text;
desc member;

-- insert
insert into member 
values (null,'rkdthfl22@naver.com',password('1234'),'강소리','개발팀',null);

insert into member(no,email,name,dept,password) 
values(null,'rkdthfl222@naver.com','강소리','개발팀',password('1234'));
select * from member;

-- update
update member 
   set email='rkdthfl223@naver.com',
       password=password('5678')
 where no = 2;
 select * from member;
 
-- delete
delete from member
 where no = 2;
 select * from member;
 
 -- transaction
 select @@autocommit;
 set autocommit=0;
 
 insert into member(no,email,name,dept,password) 
values(null,'rkdthfl225@naver.com','강소리5','개발팀5',password('1234'));
select * from member;

commit;
 
 
