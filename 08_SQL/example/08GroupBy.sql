SELECT "employeeId", "phoneNumber"
  FROM public."PhoneNumbers"

SELECT "employeeId", COUNT("phoneNumber") AS num
  FROM public."PhoneNumbers"
  -- WHERE will filter before GROUP BY
  WHERE "phoneNumber" NOT LIKE '+111%'
  GROUP BY "employeeId"
  -- HAVING "is a WHERE" ont the result of GROUP BY
  HAVING "employeeId" < 50;
