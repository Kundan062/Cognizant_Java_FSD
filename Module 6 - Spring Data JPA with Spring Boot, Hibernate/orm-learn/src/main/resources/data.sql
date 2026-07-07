insert into country (co_code, co_name) values ('IN', 'India');
insert into country (co_code, co_name) values ('US', 'United States of America');
insert into country (co_code, co_name) values ('DE', 'Germany');
insert into country (co_code, co_name) values ('FR', 'France');
insert into country (co_code, co_name) values ('JP', 'Japan');
insert into country (co_code, co_name) values ('AU', 'Australia');

insert into stock (st_code, st_date, st_open, st_close, st_volume) values ('FB', '2019-09-03', 184.00, 182.39, 9779400);
insert into stock (st_code, st_date, st_open, st_close, st_volume) values ('FB', '2019-09-10', 187.20, 188.49, 10799600);
insert into stock (st_code, st_date, st_open, st_close, st_volume) values ('GOOGL', '2019-09-05', 1200.00, 1211.38, 1408100);
insert into stock (st_code, st_date, st_open, st_close, st_volume) values ('GOOGL', '2019-09-20', 1233.12, 1246.15, 2270000);
insert into stock (st_code, st_date, st_open, st_close, st_volume) values ('GOOGL', '2019-10-01', 1265.00, 1271.00, 2424000);

insert into department (dp_id, dp_name) values (1, 'Technology');
insert into department (dp_id, dp_name) values (2, 'Human Resources');
insert into department (dp_id, dp_name) values (3, 'Finance');

insert into skill (sk_id, sk_name) values (1, 'Java');
insert into skill (sk_id, sk_name) values (2, 'Spring Boot');
insert into skill (sk_id, sk_name) values (3, 'Hibernate');
insert into skill (sk_id, sk_name) values (4, 'SQL');

insert into employee (em_id, em_name, em_salary, em_permanent, em_date_of_birth, em_dp_id) values (1, 'John Doe', 55000.00, true, '1990-05-10', 1);
insert into employee (em_id, em_name, em_salary, em_permanent, em_date_of_birth, em_dp_id) values (2, 'Jane Smith', 65000.00, true, '1988-08-22', 1);
insert into employee (em_id, em_name, em_salary, em_permanent, em_date_of_birth, em_dp_id) values (3, 'Mark Taylor', 45000.00, false, '1995-01-15', 2);

insert into employee_skill (es_em_id, es_sk_id) values (1, 1);
insert into employee_skill (es_em_id, es_sk_id) values (1, 2);
insert into employee_skill (es_em_id, es_sk_id) values (2, 2);
insert into employee_skill (es_em_id, es_sk_id) values (2, 3);
insert into employee_skill (es_em_id, es_sk_id) values (3, 4);

insert into quiz_user (us_id, us_name) values (1, 'admin');
insert into question (qt_id, qt_text, qt_score) values (1, 'What is the extension of the hyper text markup language file?', 1.0);
insert into question (qt_id, qt_text, qt_score) values (2, 'What is the maximum level of heading tag in HTML?', 1.0);
insert into option_item (op_id, op_text, op_correct, op_qt_id) values (1, '.xhtm', false, 1);
insert into option_item (op_id, op_text, op_correct, op_qt_id) values (2, '.html', true, 1);
insert into option_item (op_id, op_text, op_correct, op_qt_id) values (3, '5', false, 2);
insert into option_item (op_id, op_text, op_correct, op_qt_id) values (4, '6', true, 2);
insert into attempt (at_id, at_us_id, at_date) values (1, 1, '2026-06-27');
insert into attempt_question (aq_id, aq_at_id, aq_qt_id) values (1, 1, 1);
insert into attempt_question (aq_id, aq_at_id, aq_qt_id) values (2, 1, 2);
insert into attempt_option (ao_id, ao_aq_id, ao_op_id, ao_selected) values (1, 1, 1, false);
insert into attempt_option (ao_id, ao_aq_id, ao_op_id, ao_selected) values (2, 1, 2, true);
insert into attempt_option (ao_id, ao_aq_id, ao_op_id, ao_selected) values (3, 2, 3, false);
insert into attempt_option (ao_id, ao_aq_id, ao_op_id, ao_selected) values (4, 2, 4, true);
