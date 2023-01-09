-- select  기본
select count(*) 
from departments;

select dept_no, dept_name 
from departments;

select concat(first_name,' ',last_name) as '이름', gender as '성', hire_date as '입사일' 
from employees
limit 50;

select * from titles
limit 50;

select first_name, gender,hire_date
from employees
where hire_date < '1989-01-01'
	and gender ='f'
order by hire_date desc;

select emp_no, dept_no
from dept_emp
where dept_no in ('d005','d009');

select first_name, hire_date
from employees
where hire_date like '%1989%';

select concat(first_name,' ',last_name), gender, hire_date
from employees
order by hire_date ;

select *
from salaries
where from_date like '2001%' 
	and to_date like '2001%'
order by salary desc;


select first_name as '이룸', gender as '성별', hire_date as '입사일'
from employees
where gender ='m'
order by hire_date asc;


