-- add phone number to Géza (id:5)
INSERT INTO public."PhoneNumbers"(
            "employId", "phoneNumber")
    VALUES (5, '+36123456');

-- add an other phone number to Géza
INSERT INTO public."PhoneNumbers"(
            "employId", "phoneNumber")
            -- Query the id of that employee whos name 'like' Géza
    VALUES ((SELECT id FROM public."Employee" WHERE name LIKE 'Géza'), '+36654321');


SELECT * FROM public."PhoneNumbers" 