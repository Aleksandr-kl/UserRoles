INSERT INTO users(id,archive, email,name,password, role,address_id)
VALUES (1,false,'admin@gmail.com','admin','qwerty','ADMIN',null);

ALTER SEQUENCE user_seq RESTART WITH 2;