-- Delete ALLLL!!! row from Employee table where the jobtitle was null
DELETE FROM public."Employee"
 WHERE jobtitle IS NULL;

SELECT * FROM public."Employee" WHERE jobtitle IS NULL