-- SET ALLLL!!! rows' age to 20 where the age was less then 18
UPDATE public."Employee"
   SET age=20
 WHERE age < 18;


 SELECT * FROM public."Employee"  WHERE age < 18
