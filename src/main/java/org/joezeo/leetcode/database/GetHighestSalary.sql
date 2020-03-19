# 编写一个 SQL 查询，获取 Employee 表中第 n 高的薪水（Salary）。
CREATE FUNCTION getNthHighestSalary(N INT) RETURNS INT
BEGIN
  if N<0 then
    RETURN (select min(Salary) from Employee);
  else
    set N = N-1;
    RETURN (
      # Write your MySQL query statement below.
      select ifnull((select distinct Salary from Employee order by Salary desc limit N,1),null) as NthHighestSalay
    );
  end if;
END