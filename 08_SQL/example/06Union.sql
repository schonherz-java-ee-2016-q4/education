-- Make the union of those two table
SELECT "employeeId", "phoneNumber"  FROM public."PhoneNumbers"
UNION
SELECT "employeeId", email FROM public."Emails";

-- ERROR:  each UNION query must have the same number of columns
SELECT "employeeId", "phoneNumber"  FROM public."PhoneNumbers"
UNION
SELECT email FROM public."Emails";

-- The "missing" column will be null -> the union can run
SELECT "employeeId", "phoneNumber"  FROM public."PhoneNumbers"
UNION
SELECT null, email FROM public."Emails";