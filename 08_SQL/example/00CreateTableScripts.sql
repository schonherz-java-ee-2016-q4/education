CREATE SEQUENCE public."Employee_id_seq"
  INCREMENT 2
  MINVALUE 1
  MAXVALUE 9223372036854775807
  START 13
  CACHE 1;

CREATE TABLE public."Employee"
(
  id integer NOT NULL DEFAULT nextval('"Employee_id_seq"'::regclass),
  name character varying(20) NOT NULL,
  age integer NOT NULL DEFAULT 18,
  jobtitle character varying(50),
  CONSTRAINT "Employee_pkey" PRIMARY KEY (id)
);

CREATE TABLE public."PhoneNumbers"
(
  "employeeId" integer NOT NULL,
  "phoneNumber" character varying(15),
  CONSTRAINT "PhoneNumbers_employId_fkey" FOREIGN KEY ("employeeId")
      REFERENCES public."Employee" (id) MATCH SIMPLE
      ON UPDATE CASCADE ON DELETE CASCADE
);

CREATE TABLE public."Emails"
(
  "employeeId" integer,
  email character varying(50),
  CONSTRAINT "Emails_employId_fkey" FOREIGN KEY ("employeeId")
      REFERENCES public."Employee" (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
);
