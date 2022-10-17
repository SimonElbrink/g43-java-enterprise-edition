# Students
INSERT INTO jpa_db.students (id, birth_date, email, first_name, last_name, register_date, status, address_id) VALUES (1001,'1990-01-01', 'Christopher@lexicon.se', 'Christopher', 'Ojaide', DEFAULT, DEFAULT, null);
INSERT INTO jpa_db.students (id, birth_date, email, first_name, last_name, register_date, status, address_id) VALUES (1002,'1990-01-01', 'Husayn@lexicon.se', 'Husayn', 'Ganiev', DEFAULT, DEFAULT, null);
INSERT INTO jpa_db.students (id, birth_date, email, first_name, last_name, register_date, status, address_id) VALUES (1003,'1990-01-01', 'Jonas@lexicon.se', 'Jonas', 'Eira', DEFAULT, DEFAULT, null);
INSERT INTO jpa_db.students (id, birth_date, email, first_name, last_name, register_date, status, address_id) VALUES (1004,'1990-01-01', 'Julius@lexicon.se', 'Julius', 'Büchner', DEFAULT, DEFAULT, null);
INSERT INTO jpa_db.students (id, birth_date, email, first_name, last_name, register_date, status, address_id) VALUES (1005,'1990-01-01', 'Roudabeh@lexicon.se', 'Roudabeh', 'Adnani', DEFAULT, DEFAULT, null);
INSERT INTO jpa_db.students (id, birth_date, email, first_name, last_name, register_date, status, address_id) VALUES (1006,'1990-01-01', 'Mohsen@lexicon.se', 'Mohsen', 'Forouzmand', DEFAULT, DEFAULT, null);
INSERT INTO jpa_db.students (id, birth_date, email, first_name, last_name, register_date, status, address_id) VALUES (1007,'1990-01-01', 'Ly@lexicon.se', 'Ly', 'Ta-Thi', DEFAULT, DEFAULT, null);
INSERT INTO jpa_db.students (id, birth_date, email, first_name, last_name, register_date, status, address_id) VALUES (1008,'1990-01-01', 'Anusha@lexicon.se', 'Anusha', 'Yenugu', DEFAULT, DEFAULT, null);
INSERT INTO jpa_db.students (id, birth_date, email, first_name, last_name, register_date, status, address_id) VALUES (1009,'1990-01-01', 'Roberto@lexicon.se', 'Roberto', 'Camacho', DEFAULT, DEFAULT, null);
INSERT INTO jpa_db.students (id, birth_date, email, first_name, last_name, register_date, status, address_id) VALUES (1010,'1990-01-01', 'Thanapat@lexicon.se', 'Thanapat', 'Sombat kamrai', DEFAULT, DEFAULT, null);
INSERT INTO jpa_db.students (id, birth_date, email, first_name, last_name, register_date, status, address_id) VALUES (1011,'1990-01-01', 'Zeq@lexicon.se', 'Zeq', 'Alidemaj', DEFAULT, DEFAULT, null);
INSERT INTO jpa_db.students (id, birth_date, email, first_name, last_name, register_date, status, address_id) VALUES (1012,'1990-01-01', 'Joe@lexicon.se', 'Joe', 'Yaghi', DEFAULT, DEFAULT, null);
INSERT INTO jpa_db.students (id, birth_date, email, first_name, last_name, register_date, status, address_id) VALUES (1013,'1990-01-01', 'Jack@lexicon.se', 'Jack', 'Ninway', DEFAULT, DEFAULT, null);
INSERT INTO jpa_db.students (id, birth_date, email, first_name, last_name, register_date, status, address_id) VALUES (1014,'1990-01-01', 'Jennie@lexicon.se', 'Jennie', 'Smith', DEFAULT, DEFAULT, null);

# Courses
INSERT INTO jpa_db.course (id,name) VALUES (4001,'JavaGroup37');
INSERT INTO jpa_db.course (id,name) VALUES (4002,'JavaGroup40');
INSERT INTO jpa_db.course (id,name) VALUES (4003,'JavaGroup43');

# Course Relationships
INSERT INTO jpa_db.courses_students (courses_id, students_id) VALUES (4003, 1001);
INSERT INTO jpa_db.courses_students (courses_id, students_id) VALUES (4003, 1002);
INSERT INTO jpa_db.courses_students (courses_id, students_id) VALUES (4003, 1003);
INSERT INTO jpa_db.courses_students (courses_id, students_id) VALUES (4003, 1004);
INSERT INTO jpa_db.courses_students (courses_id, students_id) VALUES (4003, 1005);
INSERT INTO jpa_db.courses_students (courses_id, students_id) VALUES (4003, 1006);
INSERT INTO jpa_db.courses_students (courses_id, students_id) VALUES (4003, 1007);
INSERT INTO jpa_db.courses_students (courses_id, students_id) VALUES (4003, 1008);
INSERT INTO jpa_db.courses_students (courses_id, students_id) VALUES (4003, 1009);
INSERT INTO jpa_db.courses_students (courses_id, students_id) VALUES (4003, 1010);
INSERT INTO jpa_db.courses_students (courses_id, students_id) VALUES (4003, 1011);
INSERT INTO jpa_db.courses_students (courses_id, students_id) VALUES (4003, 1012);
INSERT INTO jpa_db.courses_students (courses_id, students_id) VALUES (4003, 1013);
INSERT INTO jpa_db.courses_students (courses_id, students_id) VALUES (4003, 1014);



