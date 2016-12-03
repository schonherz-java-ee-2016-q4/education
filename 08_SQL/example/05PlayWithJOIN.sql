SELECT 
  "Employee".name, 
  "PhoneNumbers"."phoneNumber" AS "Telefonszam"
FROM 
  public."Employee", 
  public."PhoneNumbers"
WHERE 
  "PhoneNumbers"."employId" = "Employee".id;
-- Total query runtime: 11 msec

-- insert two new record with one command
INSERT INTO public."PhoneNumbers" ("employId", "phoneNumber") VALUES
(1, '+1111111'),
(123, '12345')

SELECT *
	FROM public."Employee"
	LEFT JOIN public."PhoneNumbers" ON "PhoneNumbers"."employId" = "Employee".id
-- Total query runtime: 11 msec

-- show all rows from Employee even if there is no phonenumber for him/her
SELECT *
	FROM public."Employee"
	LEFT JOIN public."PhoneNumbers" ON "PhoneNumbers"."employId" = "Employee".id

-- Cartesian square of the two table
SELECT *
	FROM public."Employee"
	CROSS JOIN public."PhoneNumbers"