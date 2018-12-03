sqlplus / as sysdba;
show user;
conn system/admin1234;
show user;
grant create view to scott;

conn scott/tiger;
show user;

create view emp_view30
as
select empno, ename, deptno from emp_copy
where deptno = 30;

select * from emp_view30;

desc emp_view30


