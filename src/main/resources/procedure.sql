create or replace function cal_bonus(vempno in emp.empno%type)
  return number
is
  vsal number(7,2);
begin
  select sal into vsal
  from temporary
  where empno = vempno;
end;
/


SQL> ed proc04
SQL> @proc04

SQL> variable var_result number;
SQL> execute :var_result := cal_bonus(7788);
SQL> print var_result;

--[5] Cursor(커서)
-- :처리 결과가 여러개의 행으로 구해지는 select문을 처리

SQL> ed proc05

create or replace procedure cursor_sample01
is
    vdept dept%rowtype;
    cursor c1
    is
    select * from dept;
begin
    DBMS_OUTPUT.PUT_LINE('부서번호/부서명/지역명');
    DBMS_OUTPUT.PUT_LINE('----------------------------------')
    open c1;

    LOOP
      fetch c1 into vdept.deptno, vdept.dname, vdept.loc;

      exit when c1%notfound;

      DBMS_OUTPUT.PUT_LINE(vdept.deptno ||''|| vdept.dname ||''|| vdept.loc)

end;
/




