--
-- Data for Name: department; Type: TABLE DATA; Schema: public; Owner: postgres
--
INSERT INTO public.department (department_id,created_at, department_name) VALUES (5,'2019-04-12','dept11');
INSERT INTO public.department (department_id,created_at, department_name, parent_id) VALUES (7,'2019-04-12','dept2',5);
INSERT INTO public.department (department_id,created_at, department_name, parent_id) VALUES (9,'2019-04-12','dept3',5);
INSERT INTO public.department (department_id,created_at, department_name, parent_id) VALUES (8,'2019-04-12','dept4',7);



--
-- Data for Name: employee_position; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.employee_position (position_id,position_name) VALUES (3,'jedi master');
INSERT INTO public.employee_position (position_id,position_name) VALUES (1,'padawan');
INSERT INTO public.employee_position (position_id,position_name) VALUES (2,'jedi');

--
-- Data for Name: employee; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.employee (employee_id,birthday, chief, email, name, patronymic, phone, recruitment_date, salary, sex, surname, id, department_id, "position") 
VALUES (1,'1989-12-12','t','123@ya.ru','Жак', 'Ив','89083828555','2019-04-12',100000,'m','Кусто',6,5,3);
INSERT INTO public.employee (employee_id,birthday, chief, email, name, patronymic, phone, recruitment_date, salary, sex, surname, id, department_id, "position") 
VALUES (2,'1989-12-12','t','123@ya.ru','Жан','Жак','89083828555','2019-04-12',80000,'m','Кусто',10,8,3);
INSERT INTO public.employee (employee_id,birthday, chief, email, name, patronymic, phone, recruitment_date, salary, sex, surname, id, department_id, "position") 
VALUES (3,'1989-12-12','f','123@ya.ru','Жанлин','Жак','89083828555','2019-04-12',80000,'f','Кусто',21,5,3);
INSERT INTO public.employee (employee_id,birthday, chief, email, name, phone, recruitment_date, salary, sex, surname, id, department_id, "position") 
VALUES (4,'1989-12-12','t','123@ya.ru','Жаки','89083828555','2019-04-12',80000,'f','Куст',22,9,3);
INSERT INTO public.employee (employee_id,birthday, chief, email, name, phone, recruitment_date, salary, sex, surname, id, department_id, "position") 
VALUES (5,'1989-12-12','f','123@ya.ru','Жак','89083828555','2019-04-12',10000,'f','Кустошвили',24,7,1);
INSERT INTO public.employee (employee_id,birthday, chief, email, name, patronymic, phone, recruitment_date, salary, sex, surname, id, department_id, "position") 
VALUES (6,'1989-12-12','f','123@ya.ru','Жак','Ибрагимович','89083828555','2019-04-12',10000,'f','Кустович',23,7,1);


