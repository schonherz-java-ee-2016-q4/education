-- Roll back by user
-- Start a new transaction
BEGIN;
-- 3 rows
SELECT * FROM public."Employee";

INSERT INTO public."Employee" (name) VALUES ('Árpád');
-- 4 rows
SELECT * FROM public."Employee";
-- The database state will be the same what was before the transaction has benn started
ROLLBACK; 
-- 3 rows (Árpád is not in the table)
SELECT * FROM public."Employee";



-- Roll back by error
-- 4 rows
SELECT * FROM public."Employee";
BEGIN;
INSERT INTO public."Employee" (name) VALUES ('Mari');
-- 5 rows
SELECT * FROM public."Employee";
INSERT INTO public."Employee" (name, nonexistscolum) VALUES ('Gizi', 'asdasd');
-- transaction is interrupted by an error 
SELECT * FROM public."Employee";
ROLLBACK;
-- 4 rows
SELECT * FROM public."Employee";



-- Commit the transaction
-- 4 rows
SELECT * FROM public."Employee";
BEGIN;
INSERT INTO public."Employee" (name) VALUES ('Mari');
-- 5 rows
COMMIT;
-- 5 rows
SELECT * FROM public."Employee";