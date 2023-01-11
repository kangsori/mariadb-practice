-- 문제1.
-- 현재 평균 연봉보다 많은 월급을 받는 직원은 몇 명이나 있습니까?
select count(*) as '직원수'
from salaries
where to_date='9999-01-01'
  and salary > (select avg(salary) 
                  from salaries 
				where to_date='9999-01-01');

-- 문제2. (X)
-- 현재, 각 부서별로 최고의 급여를 받는 사원의 사번, 이름, 급여를 조회하세요.
-- 단 조회결과는 연봉의 내림차순으로 정렬되어 나타나야 합니다. 

-- 문제3.
-- 현재, 자신의 부서 평균 급여보다 급여가 많은 사원의 사번, 이름과 급여을 조회하세요
select a.emp_no as '사번', a.first_name as '이름', c.salary as '급여' -- , d.avg_salary
from employees a
inner join dept_emp b on a.emp_no=b.emp_no and b.to_date='9999-01-01'
inner join salaries c on a.emp_no=c.emp_no and c.to_date='9999-01-01'
inner join ( select a.dept_no,avg(salary) as avg_salary
		       from dept_emp a
		 inner join salaries b on a.emp_no=b.emp_no
		      where a.to_date='9999-01-01'
		        and b.to_date='9999-01-01'
	       group by a.dept_no) d on b.dept_no = d.dept_no
where c.salary > d.avg_salary
order by c.salary desc;

-- 문제4.
-- 현재, 사원들의 사번, 이름, 매니저 이름, 부서 이름으로 출력해 보세요.
select a.emp_no as '사번', a.first_name as '이름', e.first_name as '매니저', c.dept_name as '부서'
from employees a
inner join dept_emp b on a.emp_no=b.emp_no and b.to_date='9999-01-01'
inner join departments c on b.dept_no=c.dept_no
inner join dept_manager d on b.dept_no=d.dept_no and d.to_date='9999-01-01'
inner join employees e on d.emp_no=e.emp_no;

-- 문제5.
-- 현재, 평균급여가 가장 높은 부서의 사원들의 사번, 이름, 직책, 급여를 조회하고 연봉 순으로 출력하세요.
select a.emp_no as '사번', a.first_name as '이름', b.title as '직책', c.salary as '급여'
from employees a
inner join titles b on a.emp_no=b.emp_no and b.to_date='9999-01-01'
inner join salaries c on a.emp_no=c.emp_no and c.to_date='9999-01-01'
inner join dept_emp d on a.emp_no=d.emp_no and d.to_date='9999-01-01'
where d.dept_no in ( select * from (select a.dept_no 
					   from dept_emp a
				 inner join salaries b on a.emp_no=b.emp_no and b.to_date='9999-01-01'
                      where a.to_date='9999-01-01'
                   group by a.dept_no
                   order by avg(b.salary) desc
                      limit 1 ) as dept_no ) ;

-- 문제6.
-- 평균 급여가 가장 높은 부서는? 
-- 부서이름, 평균 급여
select a.dept_no as '부서' , avg(b.salary) as '평균급여'
from dept_emp a
inner join salaries b on a.emp_no=b.emp_no and b.to_date='9999-01-01'
where a.to_date='9999-01-01'
group by a.dept_no
order by avg(b.salary) desc
limit 0,1;

-- 문제7.
-- 평균 급여가 가장 높은 직책?
-- 직책, 평균 급여
select a.title as '직책', avg(b.salary) as '평균급여'
from titles a
inner join salaries b on a.emp_no=b.emp_no and b.to_date='9999-01-01'
where a.to_date='9999-01-01'
group by a.title
order by avg(b.salary) desc
limit 0,1;

-- 문제8.
-- 현재 자신의 매니저보다 높은 연봉을 받고 있는 직원은?
-- 부서이름, 사원이름, 연봉, 매니저 이름, 메니저 연봉 순으로 출력합니다.
-- 1)
select d.dept_name as '부서',a.first_name as '이름', b.salary as '급여', g.first_name as '매니저', f.salary as '매니저급여'
from employees a
inner join salaries b on a.emp_no=b.emp_no and b.to_date='9999-01-01'
inner join dept_emp c on a.emp_no=c.emp_no and c.to_date='9999-01-01'
inner join departments d on c.dept_no=d.dept_no
inner join dept_manager e on c.dept_no=e.dept_no and e.to_date='9999-01-01'
inner join salaries f on e.emp_no=f.emp_no and f.to_date='9999-01-01'
inner join employees g on g.emp_no=f.emp_no 
where  b.salary > f.salary ;

-- 2)
select d.dept_name as '부서',a.first_name as '이름', b.salary as '급여', f.first_name as '매니저', e.salary as '매니저급여'
from employees a
inner join salaries b on a.emp_no=b.emp_no and b.to_date='9999-01-01'
inner join dept_emp c on a.emp_no=c.emp_no and c.to_date='9999-01-01'
inner join departments d on c.dept_no=d.dept_no
inner join ( select a.dept_no, a.emp_no, b.salary
               from dept_manager a
         inner join salaries b on a.emp_no=b.emp_no and b.to_date='9999-01-01'
              where a.to_date='9999-01-01') e on c.dept_no=e.dept_no
inner join employees f on e.emp_no=f.emp_no 
where  b.salary > e.salary ;

