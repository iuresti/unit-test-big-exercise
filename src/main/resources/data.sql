INSERT INTO users (curp, name, validity_date) VALUES ('UEAI830711HSPRDV04', 'Ivan Uresti', '20191215');
INSERT INTO users (curp, name, validity_date) VALUES ('SAAO830711HSPRDV04', 'Omar Sanchez', '20191116');
INSERT INTO users (curp, name, validity_date) VALUES ('PEMJ830711HSPRDV04', 'Jorge Perez', '20191215');


INSERT INTO books (isbn) VALUES ('0-2580-4729-1');
INSERT INTO books (isbn) VALUES ('0-5569-4822-X');
INSERT INTO books (isbn) VALUES ('0-5871-3144-6');
INSERT INTO books (isbn) VALUES ('0-2098-9535-7');
INSERT INTO books (isbn) VALUES ('0-4402-0899-8');
INSERT INTO books (isbn) VALUES ('0-7961-6110-0');
INSERT INTO books (isbn) VALUES ('0-8091-0658-2');
INSERT INTO books (isbn) VALUES ('0-4606-5337-7');


INSERT INTO books_by_user (isbn, curp, borrow_date) VALUES ('0-2580-4729-1', 'UEAI830711HSPRDV04', now());
INSERT INTO books_by_user (isbn, curp, borrow_date) VALUES ('0-5569-4822-X', 'UEAI830711HSPRDV04', now());
INSERT INTO books_by_user (isbn, curp, borrow_date) VALUES ('0-5871-3144-6', 'SAAO830711HSPRDV04', now());
INSERT INTO books_by_user (isbn, curp, borrow_date) VALUES ('0-2098-9535-7', 'UEAI830711HSPRDV04', now());
