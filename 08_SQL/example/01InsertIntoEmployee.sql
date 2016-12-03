-- Insert kacsa into Employee table (id will be the genareted by definition of sequence)
INSERT INTO public."Employee"(
            name, age, jobtitle)
    VALUES ('Kacsa', 5, 'supervisor');

-- insert Pityu int Employee table (age will be default: 18)
INSERT INTO public."Employee"(
            id, name, jobtitle)
    VALUES (123, 'Pityu', 'cheerleader');

-- insert Arnold into Employee table (id will be the incremented one, age default: 18, job title: null)
INSERT INTO public."Employee"(
            name)
    VALUES ('Arnold');

-- query all data from Employee table
SELECT * FROM public."Employee"

-- filtered for jobtitle where the jobtitle is null
SELECT * FROM public."Employee" WHERE jobtitle IS NULL