-- subquery
-- 1) select절
-- 2) from절 
select now() as n , sysdate() as s, 3+1 as r from dual;
select a.n, a.r
from  (select now() as n , sysdate() as s, 3+1 as r from dual) a;


-- 3) where절
--

-- 예제) 현재, Fai Bale이 근무하는 부서에서 근무하는 다른 직원의 사번, 이름을 출력 하세요 .

select b.dept_no
from employees a
inner join dept_emp b on a.emp_no=b.emp_no
where b.to_date='9999-01-01'
  and concat(a.first_name,' ',a.last_name) ='Fai Bale';
  
-- d004
select a.emp_no,concat(a.first_name,' ',a.last_name) as name
from employees a
inner join dept_emp b on a.emp_no=b.emp_no
where b.to_date='9999-01-01'
  and b.dept_no in ( select b.dept_no
					   from employees a
				 inner join dept_emp b on a.emp_no=b.emp_no
                      where b.to_date='9999-01-01'
                        and concat(a.first_name,' ',a.last_name) ='Fai Bale' );
                        
-- 3-1) 단일행 연산자 : =, <, >, <=, >=, !=, <>
-- 실습문제1:
-- 현재, 전체 사원의 평균 연봉보다 적은 급여를 받는 사원의 이름과 급여를 출력하세요
select a.first_name, b.salary
from employees a
inner join salaries b on a.emp_no=b.emp_no
where b.to_date='9999-01-01'
  and b.salary <  (select avg(salary)
				     from salaries
					where to_date='9999-01-01')
order by b.salary desc;

-- 실습문제2:
-- 현재, 가장 적은 평균 급여의 직책과 그 평균급여를 출력하세요

select a.title, avg(b.salary) as avg_salary
from titles a
left join salaries b on a.emp_no=b.emp_no
where a.to_date='9999-01-01'
  and b.to_date='9999-01-01'
group by a.title ;

select min(avg_salary)
from (select a.title, avg(b.salary) as avg_salary
from titles a
left join salaries b on a.emp_no=b.emp_no
where a.to_date='9999-01-01'
  and b.to_date='9999-01-01'
group by a.title) a ;

select a.title, avg(b.salary) as avg_salary
from titles a
left join salaries b on a.emp_no=b.emp_no
where a.to_date='9999-01-01'
  and b.to_date='9999-01-01'
group by a.title
having avg_salary = (select min(avg_salary) as min
					   from (select a.title, avg(b.salary) as avg_salary
							   from titles a
						  left join salaries b on a.emp_no=b.emp_no
							  where a.to_date='9999-01-01'
							    and b.to_date='9999-01-01'
					       group by a.title) b
					);
                    
select a.title, avg(b.salary) as avg_salary
from titles a
left join salaries b on a.emp_no=b.emp_no
where a.to_date='9999-01-01'
  and b.to_date='9999-01-01'
group by a.title 
order by avg_salary asc
limit 1;

-- 3-2) 복수행 연산자 
-- 1. = any : in 
-- 2. > any, >= any :최소값
-- 3. < any, <= any : 최대값
-- 4. <> any, != any : not insert

-- all 사용법
-- 1. =all (X)
-- 2. >all, >=all : 최대값
-- 3. <all, <=all : 최소값
-- 4. <>all , !=all :  

-- 실습문제3
-- 현재 급여가 5000 이상인 직원의 이름과 급여를 출력하세요.
select a.first_name, b.salary
from employees a
inner join salaries b on a.emp_no=b.emp_no
where b.to_date='9999-01-01'
  and b.salary > 50000 
order by b.salary ;

select a.first_name, b.salary
from employees a
inner join salaries b on a.emp_no=b.emp_no
where b.to_date='9999-01-01'
  and (a.emp_no, b.salary) in (select emp_no, salary
							         from salaries 
									 where to_date='9999-01-01'
									 and salary > 50000 )
order by b.salary ;

-- 실습문제4: 현재, 각 부서별로 최고 급여를 받고 있는 직원의 이름과 월급을 출력 하세요.
select a.dept_name, c.first_name, d.salary  
from departments a
inner join dept_emp b on a.dept_no=b.dept_no
inner join employees c on c.emp_no=b.emp_no
inner join salaries d on d.emp_no=c.emp_no
where b.to_date='9999-01-01'
  and d.to_date='9999-01-01'
  and (a.dept_no, d.salary) in (select a.dept_no,max(b.salary)
								from dept_emp a
								inner join salaries b on a.emp_no=b.emp_no
								where a.to_date='9999-01-01'
								and b.to_date='9999-01-01'
								group by dept_no );