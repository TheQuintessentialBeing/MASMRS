-- user - register login log
-- student using studnet and year - report
-- program - load excel data to database.
-- passive ( TODO)
select u.id as User_ID, u.first_name as "First Name" ,
      u.last_name as "Last Name", u.email as "Users Email", r.name as   "User_Role"
      from kdb.user u
      inner join users_roles on u.id = user_id
      inner join role r on r.id = role_id
      order by r.name,u.first_name , u.last_name asc;

select * from Student where student_id in (1,2);
delete from Student where student_id in (1,2);
use nowdb;

SELECT r.student_id, r.academic_year, r.grade, r.section, r.subject, r.q1, r.q2, r.q3, r.q4
FROM records r
JOIN students s ON r.student_id = s.student_id
WHERE r.academic_year = '2023' AND r.grade = 'A' AND r.section = 'A';

SELECT student_id, subject,
       AVG((q1 + q2) / 2) AS semester_1_avg,
       AVG((q3 + q4) / 2) AS semester_2_avg
FROM records
GROUP BY student_id, subject;

use nowdb;
-- deleting data from db
-- Note: always select data and see before you delete it
--       If tables are related first delete the child ( the table with foreign key
--       then delete the table with primary key
-- child table ( dependent table )
select * from nowdb.student_records;
delete  from nowdb.student_records;
select * from nowdb.student_records;
-- parent table
select * from nowdb.students;
delete   from nowdb.students;
select * from nowdb.students;


-- above rows are working and should be enterd before records as student_id fkey is needed
insert into nowdb.students (student_id, first_name, middle_name, last_name, date_of_birth, gender, registration_date, photo, kifle_ketema, kebele, house_number, phone, comment,is_active)
values
 (1001, 'Kidus', 'Kagnew', 'Eshetu', '1993-02-03', 'M', '2005-10-24', null, 'Bole', 1, 'kebele 12', '0911-354-520', '4/9/2020',true)
,(1002, 'Betel', 'Hailu', 'Tegegne', '1995-10-02', 'F', '2018-12-06', null, 'Bole', 2, 'kebele 12', '0912-282-1592', '5/5/2021',true)
,(1003, 'Abebe', 'Goje', 'Kelelegn', '1992-03-10', 'M', '2022-11-06', null, 'Piazza', 3, 'kebele 13', '0963-546-9306', '12/29/2021',true)

,(1004, 'Mattie', 'Debebe', 'Amicho', '1993-02-03', 'M', '2005-10-24', null, 'Shegole', 1, 'kebele 14', '0978-354-5277', '4/9/2020',true)
,(1005, 'Sara', 'Kebede', 'Naod', '1995-10-02', 'F', '2018-12-06', null, 'Kirkos', 2, 'kebele 10', '0911-282-1592', '5/5/2021',true)
,(1006, 'Teshome', 'Abiy', 'Woldu', '1992-03-10', 'M', '2022-11-06', null, 'Arat Killo', 3, 'kebele 11', '0936-546-9306', '12/29/2021',true)

,(1007, 'Mefkere', 'Seab', 'Adam', '1993-02-03', 'M', '2005-10-24', null, 'France', 1, 'kebele 10', '0949-354-5277', '4/9/2020',true)
,(1008, 'Woini', 'Hagos', 'Birhan', '1995-10-02', 'F', '2018-12-06', null, 'Yeka', 2, 'kebele 1', '0991-282-1592', '5/5/2021',true)
,(1009, 'Martha', 'Bulo', 'Ahmed', '1995-10-02', 'F', '2018-12-06', null, 'Yeka', 2, 'kebele 2', '0191-282-1592', '5/5/2021',true)

,(1010, 'Solomon', 'Fentaw', 'Muluneh', '1992-03-10', 'M', '2022-11-06', null, 'Mexico', 3, 'kebele 1', '0974-546-9306', '12/29/2021',true)
,(1011, 'Bank', 'Kagnew', 'Eshetu', '1993-02-03', 'M', '2005-10-24', null, 'Bole', 1, 'kebele 12', '0911-354-5277', '4/9/2020',true)
,(1012, 'Tab', 'Hailu', 'Tegegne', '1995-10-02', 'F', '2018-12-06', null, 'Bole', 2, 'kebele 12', '0912-282-1592', '5/5/2021',true)

,(1013, 'Yichalal', 'Goje', 'Kelelegn', '1992-03-10', 'M', '2022-11-06', null, 'Piazza', 3, 'kebele 13', '0963-546-9306', '12/29/2021',true)
,(1014, 'Fetene', 'Debebe', 'Amicho', '1993-02-03', 'M', '2005-10-24', null, 'Shegole', 1, 'kebele 14', '0978-354-5277', '4/9/2020',true)
,(1015, 'Simret', 'Kebede', 'Naod', '1995-10-02', 'F', '2018-12-06', null, 'Kirkos', 2, 'kebele 10', '0911-282-1592', '5/5/2021',true)

,(1016, 'Teshome', 'Abiy', 'Woldu', '1992-03-10', 'M', '2022-11-06', null, 'Arat Killo', 3, 'kebele 11', '0936-546-9306', '12/29/2021',true)
,(1017, 'Wondimu', 'Seab', 'Adam', '1993-02-03', 'M', '2005-10-24', null, 'France', 1, 'kebele 10', '0949-354-5277', '4/9/2020',true)
,(1018, 'Hakim', 'Hagos', 'Birhan', '1995-10-02', 'F', '2018-12-06', null, 'Yeka', 2, 'kebele 1', '0991-282-1592', '5/5/2021',true)

,(1019, 'Hana', 'Bulo', 'Ahmed', '1995-10-02', 'F', '2018-12-06', null, 'Yeka', 2, 'kebele 2', '0191-282-1592', '5/5/2021',true)
,(1020, 'Aklilu ', 'Fentaw', 'Muluneh', '1992-03-10', 'M', '2022-11-06', null, 'Mexico', 3, 'kebele 1', '0974-546-9306', '12/29/2021',true)
,(1021, 'Amen', 'Kagnew', 'Eshetu', '1993-02-03', 'M', '2005-10-24', null, 'Bole', 1, 'kebele 12', '0911-354-5277', '4/9/2020',true)

,(1022, 'Tru', 'Hailu', 'Tegegne', '1995-10-02', 'F', '2018-12-06', null, 'Bole', 2, 'kebele 12', '0912-282-1592', '5/5/2021',true)
,(1023, 'Bruk', 'Goje', 'Kelelegn', '1992-03-10', 'M', '2022-11-06', null, 'Piazza', 3, 'kebele 13', '0963-546-9306', '12/29/2021',true)
,(1024, 'Tekle', 'Debebe', 'Amicho', '1993-02-03', 'M', '2005-10-24', null, 'Shegole', 1, 'kebele 14', '0978-354-5277', '4/9/2020',true)

,(1025, 'Shemsit', 'Kebede', 'Naod', '1995-10-02', 'F', '2018-12-06', null, 'Kirkos', 2, 'kebele 10', '0911-282-1592', '5/5/2021',true)
,(1026, 'Lakew', 'Abiy', 'Woldu', '1992-03-10', 'M', '2022-11-06', null, 'Arat Killo', 3, 'kebele 11', '0936-546-9306', '12/29/2021',true)
,(1027, 'Senay', 'Seab', 'Adam', '1993-02-03', 'M', '2005-10-24', null, 'France', 1, 'kebele 10', '0949-354-5277', '4/9/2020',true)

,(1028, 'Nahu', 'Hagos', 'Birhan', '1995-10-02', 'F', '2018-12-06', null, 'Yeka', 2, 'kebele 1', '0991-282-1592', '5/5/2021',true)
,(1029, 'Omar', 'Bulo', 'Ahmed', '1995-10-02', 'F', '2018-12-06', null, 'Yeka', 2, 'kebele 2', '0191-282-1592', '5/5/2021',true)
,(1030, 'Mike', 'Fentaw', 'Muluneh', '1992-03-10', 'M', '2022-11-06', null, 'Mexico', 3, 'kebele 1', '0974-546-9306', '12/29/2021',true)

,(1031, 'Young', 'Kagnew', 'Eshetu', '1993-02-03', 'M', '2005-10-24', null, 'Bole', 1, 'kebele 12', '0911-354-5277', '4/9/2020',true)
,(1032, 'Sewyew', 'Hailu', 'Tegegne', '1995-10-02', 'F', '2018-12-06', null, 'Bole', 2, 'kebele 12', '0912-282-1592', '5/5/2021',true)
,(1033, 'Jemal', 'Goje', 'Kelelegn', '1992-03-10', 'M', '2022-11-06', null, 'Piazza', 3, 'kebele 13', '0963-546-9306', '12/29/2021',true)

,(1034, 'Tiktok', 'Debebe', 'Amicho', '1993-02-03', 'M', '2005-10-24', null, 'Shegole', 1, 'kebele 14', '0978-354-5277', '4/9/2020',true)
,(1035, 'Mengestu', 'Kebede', 'Naod', '1995-10-02', 'F', '2018-12-06', null, 'Kirkos', 2, 'kebele 10', '0911-282-1592', '5/5/2021',true)
,(1036, 'Chemrew', 'Abiy', 'Woldu', '1992-03-10', 'M', '2022-11-06', null, 'Arat Killo', 3, 'kebele 11', '0936-546-9306', '12/29/2021',true)

,(1037, 'Demere', 'Seab', 'Adam', '1993-02-03', 'M', '2005-10-24', null, 'France', 1, 'kebele 10', '0949-354-5277', '4/9/2020',true)
,(1038, 'Aynu', 'Hagos', 'Birhan', '1995-10-02', 'F', '2018-12-06', null, 'Yeka', 2, 'kebele 1', '0991-282-1592', '5/5/2021',true)
,(1039, 'Minit', 'Bulo', 'Ahmed', '1995-10-02', 'F', '2018-12-06', null, 'Yeka', 2, 'kebele 2', '0191-282-1592', '5/5/2021',true)

,(1040, 'Tadese', 'Fentaw', 'Muluneh', '1992-03-10', 'M', '2022-11-06', null, 'Mexico', 3, 'kebele 1', '0974-546-9306', '12/29/2021',true)
,(1041, 'Gezu', 'Kagnew', 'Eshetu', '1993-02-03', 'M', '2005-10-24', null, 'Bole', 1, 'kebele 12', '0911-354-5277', '4/9/2020',true)
,(1042, 'Temarit', 'Hailu', 'Tegegne', '1995-10-02', 'F', '2018-12-06', null, 'Bole', 2, 'kebele 12', '0912-282-1592', '5/5/2021',true)

,(1043, 'Kedame', 'Goje', 'Kelelegn', '1992-03-10', 'M', '2022-11-06', null, 'Piazza', 3, 'kebele 13', '0963-546-9306', '12/29/2021',true)
,(1044, 'Belay', 'Debebe', 'Amicho', '1993-02-03', 'M', '2005-10-24', null, 'Shegole', 1, 'kebele 14', '0978-354-5277', '4/9/2020',true)
,(1045, 'Miskir', 'Kebede', 'Naod', '1995-10-02', 'F', '2018-12-06', null, 'Kirkos', 2, 'kebele 10', '0911-282-1592', '5/5/2021',true)

,(1046, 'Masresha', 'Abiy', 'Woldu', '1992-03-10', 'M', '2022-11-06', null, 'Arat Killo', 3, 'kebele 11', '0936-546-9306', '12/29/2021',true)
,(1047, 'Sitotaw', 'Seab', 'Adam', '1993-02-03', 'M', '2005-10-24', null, 'France', 1, 'kebele 10', '0949-354-5277', '4/9/2020',true)
,(1048, 'Birtukan', 'Hagos', 'Birhan', '1995-10-02', 'F', '2018-12-06', null, 'Yeka', 2, 'kebele 1', '0991-282-1592', '5/5/2021',true)

,(1049, 'Tsedale', 'Bulo', 'Ahmed', '1995-10-02', 'F', '2018-12-06', null, 'Yeka', 2, 'kebele 2', '0191-282-1592', '5/5/2021',true)
,(1050, 'Zerihun', 'Fentaw', 'Muluneh', '1992-03-10', 'M', '2022-11-06', null, 'Mexico', 3, 'kebele 1', '0974-546-9306', '12/29/2021',true)
,(1051, 'Tizta', 'Fentaw', 'Muluneh', '1992-03-10', 'M', '2022-11-06', null, 'Mexico', 3, 'kebele 1', '0974-546-9306', '12/29/2021',true)

,(1052, 'Betel', 'Hailu', 'Tegegne', '1995-10-02', 'F', '2018-12-06', null, 'Bole', 2, 'kebele 12', '0912-282-1592', '5/5/2021',true)
,(1053,   'Abebe', 'Goje', 'Kelelegn', '1992-03-10', 'M', '2022-11-06', null, 'Piazza', 3, 'kebele 13', '0963-546-9306', '12/29/2021',true)
,(1054, 'Mattie', 'Debebe', 'Amicho', '1993-02-03', 'M', '2005-10-24', null, 'Shegole', 1, 'kebele 14', '0978-354-5277', '4/9/2020',true)

,(1055, 'Sara', 'Kebede', 'Naod', '1995-10-02', 'F', '2018-12-06', null, 'Kirkos', 2, 'kebele 10', '0911-282-1592', '5/5/2021',true)
,(1056, 'Teshome', 'Abiy', 'Woldu', '1992-03-10', 'M', '2022-11-06', null, 'Arat Killo', 3, 'kebele 11', '0936-546-9306', '12/29/2021',true)
,(1057, 'Mefkere', 'Seab', 'Adam', '1993-02-03', 'M', '2005-10-24', null, 'France', 1, 'kebele 10', '0949-354-5277', '4/9/2020',true)

,(1058, 'Woini', 'Hagos', 'Birhan', '1995-10-02', 'F', '2018-12-06', null, 'Yeka', 2, 'kebele 1', '0991-282-1592', '5/5/2021',true)
,(1059, 'Martha', 'Bulo', 'Ahmed', '1995-10-02', 'F', '2018-12-06', null, 'Yeka', 2, 'kebele 2', '0191-282-1592', '5/5/2021',true)
,(1060, 'Solomon', 'Fentaw', 'Muluneh', '1992-03-10', 'M', '2022-11-06', null, 'Mexico', 3, 'kebele 1', '0974-546-9306', '12/29/2021',true)
,(1061, 'Bank', 'Kagnew', 'Eshetu', '1993-02-03', 'M', '2005-10-24', null, 'Bole', 1, 'kebele 12', '0911-354-5277', '4/9/2020',true)

,(1062, 'Tab', 'Hailu', 'Tegegne', '1995-10-02', 'F', '2018-12-06', null, 'Bole', 2, 'kebele 12', '0912-282-1592', '5/5/2021',true)
,(1063, 'Yichalal', 'Goje', 'Kelelegn', '1992-03-10', 'M', '2022-11-06', null, 'Piazza', 3, 'kebele 13', '0963-546-9306', '12/29/2021',true)
,(1064, 'Fetene', 'Debebe', 'Amicho', '1993-02-03', 'M', '2005-10-24', null, 'Shegole', 1, 'kebele 14', '0978-354-5277', '4/9/2020',true)

,(1065, 'Simret', 'Kebede', 'Naod', '1995-10-02', 'F', '2018-12-06', null, 'Kirkos', 2, 'kebele 10', '0911-282-1592', '5/5/2021',true)
,(1066, 'Teshome', 'Abiy', 'Woldu', '1992-03-10', 'M', '2022-11-06', null, 'Arat Killo', 3, 'kebele 11', '0936-546-9306', '12/29/2021',true)
,(1067, 'Wondimu', 'Seab', 'Adam', '1993-02-03', 'M', '2005-10-24', null, 'France', 1, 'kebele 10', '0949-354-5277', '4/9/2020',true)

,(1068, 'Hakim', 'Hagos', 'Birhan', '1995-10-02', 'F', '2018-12-06', null, 'Yeka', 2, 'kebele 1', '0991-282-1592', '5/5/2021',true)
,(1069, 'Hana', 'Bulo', 'Ahmed', '1995-10-02', 'F', '2018-12-06', null, 'Yeka', 2, 'kebele 2', '0191-282-1592', '5/5/2021',true)
,(1070, 'Aklilu ', 'Fentaw', 'Muluneh', '1992-03-10', 'M', '2022-11-06', null, 'Mexico', 3, 'kebele 1', '0974-546-9306', '12/29/2021',true)

,(1071, 'Amen', 'Kagnew', 'Eshetu', '1993-02-03', 'M', '2005-10-24', null, 'Bole', 1, 'kebele 12', '0911-354-5277', '4/9/2020',true)
,(1072, 'Tru', 'Hailu', 'Tegegne', '1995-10-02', 'F', '2018-12-06', null, 'Bole', 2, 'kebele 12', '0912-282-1592', '5/5/2021',true)
,(1073, 'Bruk', 'Goje', 'Kelelegn', '1992-03-10', 'M', '2022-11-06', null, 'Piazza', 3, 'kebele 13', '0963-546-9306', '12/29/2021',true)

,(1074, 'Tekle', 'Debebe', 'Amicho', '1993-02-03', 'M', '2005-10-24', null, 'Shegole', 1, 'kebele 14', '0978-354-5277', '4/9/2020',true)
,(1075, 'Shemsit', 'Kebede', 'Naod', '1995-10-02', 'F', '2018-12-06', null, 'Kirkos', 2, 'kebele 10', '0911-282-1592', '5/5/2021',true)
,(1076, 'Lakew', 'Abiy', 'Woldu', '1992-03-10', 'M', '2022-11-06', null, 'Arat Killo', 3, 'kebele 11', '0936-546-9306', '12/29/2021',true)

,(1077, 'Senay', 'Seab', 'Adam', '1993-02-03', 'M', '2005-10-24', null, 'France', 1, 'kebele 10', '0949-354-5277', '4/9/2020',true)
,(1078, 'Nahu', 'Hagos', 'Birhan', '1995-10-02', 'F', '2018-12-06', null, 'Yeka', 2, 'kebele 1', '0991-282-1592', '5/5/2021',true)
,(1079, 'Omar', 'Bulo', 'Ahmed', '1995-10-02', 'F', '2018-12-06', null, 'Yeka', 2, 'kebele 2', '0191-282-1592', '5/5/2021',true)

,(1080, 'Mike', 'Fentaw', 'Muluneh', '1992-03-10', 'M', '2022-11-06', null, 'Mexico', 3, 'kebele 1', '0974-546-9306', '12/29/2021',true)
,(1081, 'Young', 'Kagnew', 'Eshetu', '1993-02-03', 'M', '2005-10-24', null, 'Bole', 1, 'kebele 12', '0911-354-5277', '4/9/2020',true)
,(1082, 'Sewyew', 'Hailu', 'Tegegne', '1995-10-02', 'F', '2018-12-06', null, 'Bole', 2, 'kebele 12', '0912-282-1592', '5/5/2021',true)

,(1083, 'Jemal', 'Goje', 'Kelelegn', '1992-03-10', 'M', '2022-11-06', null, 'Piazza', 3, 'kebele 13', '0963-546-9306', '12/29/2021',true)
,(1084, 'Tiktok', 'Debebe', 'Amicho', '1993-02-03', 'M', '2005-10-24', null, 'Shegole', 1, 'kebele 14', '0978-354-5277', '4/9/2020',true)
,(1085, 'Mengestu', 'Kebede', 'Naod', '1995-10-02', 'F', '2018-12-06', null, 'Kirkos', 2, 'kebele 10', '0911-282-1592', '5/5/2021',true)

,(1086, 'Chemrew', 'Abiy', 'Woldu', '1992-03-10', 'M', '2022-11-06', null, 'Arat Killo', 3, 'kebele 11', '0936-546-9306', '12/29/2021',true)
,(1087, 'Demere', 'Seab', 'Adam', '1993-02-03', 'M', '2005-10-24', null, 'France', 1, 'kebele 10', '0949-354-5277', '4/9/2020',true)
,(1088, 'Aynu', 'Hagos', 'Birhan', '1995-10-02', 'F', '2018-12-06', null, 'Yeka', 2, 'kebele 1', '0991-282-1592', '5/5/2021',true)

,(1089, 'Minit', 'Bulo', 'Ahmed', '1995-10-02', 'F', '2018-12-06', null, 'Yeka', 2, 'kebele 2', '0191-282-1592', '5/5/2021',true)
,(1090, 'Tadese', 'Fentaw', 'Muluneh', '1992-03-10', 'M', '2022-11-06', null, 'Mexico', 3, 'kebele 1', '0974-546-9306', '12/29/2021',true)
,(1091, 'Gezu', 'Kagnew', 'Eshetu', '1993-02-03', 'M', '2005-10-24', null, 'Bole', 1, 'kebele 12', '0911-354-5277', '4/9/2020',true)

,(1092, 'Temarit', 'Hailu', 'Tegegne', '1995-10-02', 'F', '2018-12-06', null, 'Bole', 2, 'kebele 12', '0912-282-1592', '5/5/2021',true)
,(1093, 'Kedame', 'Goje', 'Kelelegn', '1992-03-10', 'M', '2022-11-06', null, 'Piazza', 3, 'kebele 13', '0963-546-9306', '12/29/2021',true)
,(1094, 'Belay', 'Debebe', 'Amicho', '1993-02-03', 'M', '2005-10-24', null, 'Shegole', 1, 'kebele 14', '0978-354-5277', '4/9/2020',true)

,(1095, 'Miskir', 'Kebede', 'Naod', '1995-10-02', 'F', '2018-12-06', null, 'Kirkos', 2, 'kebele 10', '0911-282-1592', '5/5/2021',true)
,(1096, 'Masresha', 'Abiy', 'Woldu', '1992-03-10', 'M', '2022-11-06', null, 'Arat Killo', 3, 'kebele 11', '0936-546-9306', '12/29/2021',true)
,(1097, 'Sitotaw', 'Seab', 'Adam', '1993-02-03', 'M', '2005-10-24', null, 'France', 1, 'kebele 10', '0949-354-5277', '4/9/2020',true)

,(1098, 'Birtukan', 'Hagos', 'Birhan', '1995-10-02', 'F', '2018-12-06', null, 'Yeka', 2, 'kebele 1', '0991-282-1592', '5/5/2021',true)
,(1099, 'Tsedale', 'Bulo', 'Ahmed', '1995-10-02', 'F', '2018-12-06', null, 'Yeka', 2, 'kebele 2', '0191-282-1592', '5/5/2021',true)
,(1100, 'Zerihun', 'Fentaw', 'Muluneh', '1992-03-10', 'M', '2022-11-06', null, 'Mexico', 3, 'kebele 1', '0974-546-9306', '12/29/2021',true)
,(1101, 'Tizta', 'Fentaw', 'Muluneh', '1992-03-10', 'M', '2022-11-06', null, 'Mexico', 3, 'kebele 1', '0974-546-9306', '12/29/2021',true);


-- StudentRecord data for from 2014 at least 3 students in each section ( section varies by year ) and for 3 subjects
--- includes some new  or drop out students in each year each semester
--- included the frist 14 students as a sampel - you can add all the students and also add subjects and also drop or add in each year for possible scinious


-- 2014 - 9 - Section A
use nowdb;
go
CREATE PROCEDURE loadRecordsData
BEGIN
DECLARE row_count INT;
IF EXISTS ( SELECT 1 FROM records LIMIT 1 )
   THEN DELETE FROM records
   SELECT 'records table was not empty and data deleted.' AS Message;
ELSE
   SELECT 'records table was already empty.' AS Message;

END IF
-- Inserting data into records table
INSERT INTO student_records
(records_id,student_id, academic_year, grade,subject  ,  section, q1,  q2, q3, q4, updated_by, comment) values
 (1       , 1001      , 2014        ,9     ,'English',    'A'  , 1, 2, 77, 80, 1, 'Admitted in 2014')
,(2       , 1001      , 2014        ,9     ,'Math',       'A'  , 3, 4, 80, 80, 1, 'Admitted in 2014')
,(3       , 1001	  , 2014        ,9     ,'Physics',    'A'  , 5, 60, 80, 80, 1,  'Admitted in 2014')

,(4       , 1002      , 2014        ,9     ,'English',    'A'  , 7, 90, 77, 80, 1, 'Admitted in 2014')
,(5       , 1002      , 2014        ,9     ,'Math',       'A'  , 9, 90, 80, 80, 1, 'Admitted in 2014')
,(6       , 1002      , 2014        ,9     ,'Physics',    'A'  , 11, 90, 80, 80, 1, 'Admitted in 2014')

,(7       , 1003      , 2014        ,9     ,'English',    'A'  , 13, 90, 77, 80, 1, 'Admitted in 2014')
,(8       , 1003      , 2014        ,9     ,'Math',       'A'  , 15, 90, 80, 80, 1, 'Admitted in 2014')
,(9       , 1003      , 2014        ,9     ,'Physics',    'A'  , 17, 90, 80, 80, 1, 'Admitted in 2014')
			--  B
,(10      , 1004      , 2014        ,9     ,'English',    'B'  , 19, 90, 77, 80, 1, 'Admitted in 2014')
,(11      , 1004      , 2014        ,9     ,'Math',       'B'  , 21, 90, 80, 80, 1, 'Admitted in 2014')
,(12      , 1004      , 2014        ,9     ,'Physics',    'B'  , 23, 90, 80, 80, 1, 'Admitted in 2014')

,(13      , 1005      , 2014        ,9     ,'English',    'B'  , 25, 90, 77, 80, 1, 'Admitted in 2014')
,(14      , 1005      , 2014        ,9     ,'Math',       'B'  , 27, 90, 80, 80, 1, 'Admitted in 2014')
,(15      , 1005      , 2014        ,9     ,'Physics',    'B'  , 29, 90, 80, 80, 1,  'Admitted in 2014')

,(16      , 1006      , 2014        ,9     ,'English',    'B'  , 99, 90, 77, 80, 1, 'Admitted in 2014')
,(17      , 1006      , 2014        ,9     ,'Math',       'B'  , 97, 90, 80, 80, 1, 'Admitted in 2014')
,(18      , 1006      , 2014        ,9     ,'Physics',    'B'  , 95, 90, 80, 80, 1,  'Admitted in 2014')
--

-- 2015 - 10 - Section A & B
-- insert into StudentRecord
-- (records_id,student_id, academic_year, grade,subject  ,  section, q1,  q2, q3, q4, updated_by, comment) values
,(19      , 1001      , 2015        ,10    ,'English',    'A'  , 93, 90, 77, 80, 1, 'Admitted in 2014')
,(20      , 1001      , 2015        ,10    ,'Math',       'A'  , 91, 90, 80, 80, 1, 'Admitted in 2014')
,(21      , 1001	  , 2015        ,10    ,'Physics',    'A'  , 89, 90, 80, 80, 1,  'Admitted in 2014')

,(22      , 1002      , 2015        ,10    ,'English',    'B'  , 87, 90, 77, 80, 1, 'Admitted in 2014')
,(23      , 1002      , 2015        ,10    ,'Math',       'B'  , 85, 90, 80, 80, 1, 'Admitted in 2014')
,(24      , 1002      , 2015        ,10    ,'Physics',    'B'  , 83, 90, 80, 80, 1, 'Admitted in 2014')

,(25      , 1003      , 2015        ,10    ,'English',    'A'  , 81, 90, 77, 80, 1, 'Admitted in 2014')
,(26      , 1003      , 2015        ,10    ,'Math',       'A'  , 79, 90, 80, 80, 1, 'Admitted in 2014')
,(27      , 1003      , 2015        ,10    ,'Physics',    'A'  , 77, 90, 80, 80, 1, 'Admitted in 2014')
              --  B
,(28      , 1004      , 2015        ,10    ,'English',    'B'  , 75, 90, 77, 80, 1, 'Admitted in 2014')
,(29      , 1004      , 2015        ,10    ,'Math',       'B'  , 73, 90, 80, 80, 1, 'Admitted in 2014')
,(30      , 1004      , 2015        ,10    ,'Physics',    'B'  , 71, 90, 80, 80, 1, 'Admitted in 2014')

,(31      , 1005      , 2015        ,10    ,'English',    'A'  , 69, 90, 77, 80, 1, 'Admitted in 2014')
,(32      , 1005      , 2015        ,10    ,'Math',       'A'  , 67, 90, 80, 80, 1, 'Admitted in 2014')
,(33      , 1005      , 2015        ,10    ,'Physics',    'A'  , 65, 90, 80, 80, 1,  'Admitted in 2014')

,(34      , 1006      , 2015        ,10    ,'English',    'B'  , 63, 90, 77, 80, 1, 'Admitted in 2014')
,(35      , 1006      , 2015        ,10    ,'Math',       'B'  , 61, 90, 80, 80, 1, 'Admitted in 2014')
,(36      , 1006      , 2015        ,10    ,'Physics',    'B'  , 59, 90, 80, 80, 1,  'Admitted in 2014')
-- Admitted in  students in 2015
,(37      , 1007     , 2015         ,10    ,'English',    'A'  , 57, 90, 77, 80, 1, 'Admitted in 2015')
,(38      , 1007     , 2015         ,10    ,'Math',       'A'  , 55, 90, 80, 80, 1, 'Admitted in 2015')
,(39      , 1007     , 2015         ,10    ,'Physics',    'A'  , 53, 90, 80, 80, 1,  'Admitted in 2015')

-- 2016 - 11 - Section A & B
-- insert into StudentRecord
-- (records_id,student_id, academic_year, grade,subject  ,  section, q1,  q2, q3, q4, updated_by, comment) values

-- drop out
-- (        ,1001      , 2016       ,11    ,'English',    'B'  , 100, 90, 77, 80, 1, 'Admitted in 2014')
-- ,(       ,1001      , 2016       ,11    ,'Math',       'B'  , 100, 90, 80, 80, 1, 'Admitted in 2014') -- drop out
-- ,(       ,1001		, 2016      ,11    ,'Physics',    'A'  , 10, 90, 80, 80, 1,  'Admitted in 2014')

 ,(40      , 1002      , 2016        ,11    ,'English',    'A'  , 51, 90, 77, 80, 1, 'Admitted in 2014')
,(41      , 1002      , 2016        ,11    ,'Math',       'A'  , 49, 90, 80, 80, 1, 'Admitted in 2014')
,(42      , 1002      , 2016        ,11    ,'Physics',    'A'  , 47, 90, 80, 80, 1, 'Admitted in 2014')

,(43      , 1003      , 2016        ,11    ,'English',    'A'  , 45, 90, 77, 80, 1, 'Admitted in 2014')
,(44      , 1003      , 2016        ,11    ,'Math',       'A'  , 43, 90, 80, 80, 1, 'Admitted in 2014')
,(45      , 1003      , 2016        ,11    ,'Physics',    'A'  , 41, 90, 80, 80, 1, 'Admitted in 2014')

,(46      , 1004      , 2016        ,11    ,'English',    'B'  , 39, 90, 77, 80, 1, 'Admitted in 2014')
,(47      , 1004      , 2016        ,11    ,'Math',       'B'  , 37, 90, 80, 80, 1, 'Admitted in 2014')
,(48      , 1004      , 2016        ,11    ,'Physics',    'B'  , 35, 90, 80, 80, 1, 'Admitted in 2014')

,(49      , 1005      , 2016        ,11    ,'English',    'C'  , 33, 90, 77, 80, 1, 'Admitted in 2014')
,(50      , 1005      , 2016        ,11    ,'Math',       'C'  , 31, 90, 80, 80, 1, 'Admitted in 2014')
,(51      , 1005      , 2016        ,11    ,'Physics',    'C'  , 29, 90, 80, 80, 1,  'Admitted in 2014')

,(52      , 1006      , 2016        ,11    ,'English',    'B'  , 27, 90, 77, 80, 1, 'Admitted in 2014')
,(53      , 1006      , 2016        ,11    ,'Math',       'B'  , 25, 90, 80, 80, 1, 'Admitted in 2014')
,(54      , 1006      , 2016        ,11    ,'Physics',    'B'  , 23, 90, 80, 80, 1,  'Admitted in 2014')

,(55      , 1007      , 2016        ,11    ,'English',    'B'  , 19, 90, 77, 80, 1, 'Admitted in 2015')
,(56      , 1007      , 2016        ,11    ,'Math',       'B'  , 17, 90, 80, 80, 1, 'Admitted in 2015')
,(57      , 1007      , 2016        ,11    ,'Physics',    'B'  , 17, 90, 80, 80, 1,  'Admitted in 2015')

-- Admitted in  2016 1st semester

,(58      , 1008      , 2016        ,11    ,'English',    'C'  , 19, 90, 77, 80, 1, 'Admitted in 2015')
,(59      , 1008      , 2016        ,11    ,'Math',       'C'  , 17, 90, 80, 80, 1, 'Admitted in 2015')
,(60      , 1008      , 2016        ,11    ,'Physics',    'C'  , 17, 90, 80, 80, 1,  'Admitted in 2015')
-- Admitted in  2016 2nd semester q1 and q2 are 0
,(61      , 1009      , 2016        ,11    ,'English',    'B'  , 0, 0, 77, 80, 1, 'Admitted in 2015')
,(62      , 1009      , 2016        ,11    ,'Math',       'B'  , 0, 0, 80, 80, 1, 'Admitted in 2015')
,(63      , 1009      , 2016        ,11    ,'Physics',    'B'  , 0, 0, 80, 80, 1,  'Admitted in 2015')

-- select * from records where records_id > 60
-- 2017 - 12 - Section A & B & C
-- insert into StudentRecord
-- (records_id,student_id, academic_year, grade,subject  ,  section, q1,  q2, q3, q4, updated_by, comment) values

-- drop out
--  (        ,1001      , 2016       ,11    ,'English',    'B'  , 100, 90, 0, 80, 1, 'Admitted in 2014')
-- ,(       ,1001      , 2016       ,11    ,'Math',       'B'  , 100, 90, 80, 80, 1, 'Admitted in 2014')
-- ,(       ,1001		, 2016      ,11    ,'Physics',    'B'  , 10, 90, 80, 80, 1,  'Admitted in 2014')

,(64      , 1002      , 2017        ,12    ,'English',    'B'  , 13, 90, 0, 80, 1, 'Admitted in 2017')
,(65      , 1002      , 2017        ,12    ,'Math',       'B'  , 11, 90, 80, 80, 1, 'Admitted in 2017')
,(66      , 1002      , 2017        ,12    ,'Physics',    'B'  , 12, 90, 0.0, 0.0, 1, 'Admitted in 2017')

,(67      , 1003      , 2017        ,12    ,'English',    'B'  , 14, 90, 0, 80, 1, 'Admitted in 2017')
,(68        , 1003      , 2017        ,12    ,'Physics',    'B'  , 16, 90, 80, 80, 1, 'Admitted in 2017')

,(69      , 1004      , 2017        ,12    ,'English',    'B'  , 18, 90, 0, 80, 1, 'Admitted in 2017')
,(70      , 1004      , 2017        ,12    ,'Math',       'B'  , 20, 90, 80, 80, 1, 'Admitted in 2017')
,(71      , 1004      , 2017        ,12    ,'Physics',    'B'  , 22, 90, 80, 80, 1, 'Admitted in 2017')

,(72      , 1005      , 2017        ,12    ,'English',    'C'  , 24, 90, 0, 80, 1, 'Admitted in 2017')
,(73      , 1005      , 2017        ,12    ,'Math',       'C'  , 26, 90, 80, 80, 1, 'Admitted in 2017')
,(74      , 1005      , 2017        ,12    ,'Physics',    'C'  , 28, 90, 80, 80, 1,  'Admitted in 2017')

,(75      , 1006      , 2017        ,12    ,'English',    'B'  , 24, 90, 0, 80, 1, 'Admitted in 2017')
,(76      , 1006      , 2017        ,12    ,'Math',       'B'  , 22, 90, 80, 80, 1, 'Admitted in 2017')
,(77      , 1006      , 2017        ,12    ,'Physics',    'B'  , 10, 90, 80, 80, 1,  'Admitted in 2017')

,(78      , 1007      , 2017        ,12    ,'English',    'B'  , 28, 90, 0, 80, 1, 'Admitted in 2017')
,(79      , 1007      , 2017        ,12    ,'Math',       'B'  , 30, 90, 80, 80, 1, 'Admitted in 2017')

-- drop out - sem II
-- ,(       , 1008    , 2016        ,11    ,'English',    'C'  , 100, 90, 0, 0, 1, 'DO SEM II 2017')
-- ,(       , 1008     , 2016       ,11    ,'Math',       'C'  , 100, 90, 0, 0, 1, 'DO SEM II 2017')
-- ,(       , 1008     , 2016       ,11    ,'Physics',    'C'  , 10, 90, 0, 0, 1,  'DO SEM II 2017')

,(80      , 1008     , 2017         ,12    ,'English',    'B'  , 32, 90, 0, 0, 1, 'Admitted in 2017')
,(81      , 1008     , 2017         ,12    ,'Math',       'B'  , 34, 90, 0, 0, 1, 'Admitted in 2017')
,(82      , 1008     , 2017         ,12    ,'Physics',    'B'  , 36, 90, 0, 0, 1,  'Admitted in 2017')



-- 2017 added q1
,(83      , 1009     , 2017         ,12    ,'English',    'B'  , 38, 90, 0, 0, 1, 'Admitted in 2017')
,(84      , 1009     , 2017         ,12    ,'Math',       'B'  , 40, 90, 0, 0, 1, 'Admitted in 2017')
,(85      , 1009     , 2017         ,12    ,'Physics',    'B'  , 42, 90, 0, 0, 1,  'Admitted in 2017')

,(86      , 1010     , 2017         ,12    ,'English',    'C'  , 44, 90, 0, 0, 1, 'Admitted in 2017')
,(87      , 1010     , 2017         ,12    ,'Math',       'C'  , 46, 90, 0, 0, 1, 'Admitted in 2017')
,(88      , 1010     , 2017         ,12    ,'Physics',    'C'  , 48, 90, 0, 0, 1,  'Admitted in 2017')

,(89      , 1011     , 2017         ,12    ,'English',    'B'  , 50, 90, 0, 0, 1, 'Admitted in 2017')
,(90      , 1011     , 2017         ,12    ,'Math',       'B'  , 52, 90, 0, 0, 1, 'Admitted in 2017')
,(91      , 1011     , 2017         ,12    ,'Physics',    'B'  , 54, 90, 0, 0, 1,  'Admitted in 2017')

,(92      , 1012     , 2017         ,12    ,'English',    'B'  , 56, 90, 0, 0, 1, 'Admitted in 2017')
,(93      , 1012     , 2017         ,12    ,'Math',       'B'  , 100, 90, 0, 0, 1, 'Admitted in 2017')
,(94      , 1012     , 2017         ,12    ,'Physics',    'B'  , 10, 90, 0, 0, 1,  'Admitted in 2017')

,(95      , 1013     , 2017         ,12    ,'English',    'B'  , 58, 90, 0, 0, 1, 'Admitted in 2017')
,(96      , 1013     , 2017         ,12    ,'Math',       'B'  , 60, 90, 0, 0, 1, 'Admitted in 2017')
,(97      , 1013     , 2017         ,12    ,'Physics',    'B'  , 62, 90, 0, 0, 1,  'Admitted in 2017')

,(98      , 1014     , 2017         ,12    ,'English',    'B'  , 64, 90, 0, 0, 1, 'Admitted in 2017')
,(99      , 1014     , 2017         ,12    ,'Math',       'B'  , 66, 90, 0, 0, 1, 'Admitted in 2017')
,(100     , 1014     , 2017         ,12    ,'Physics',    'B'  , 68, 90, 0, 0, 1,  'Admitted in 2017');

-- Get number of records inserted

SET row_count = ROW_COUNT();

-- Show Message count of records inserted

SELECT concat('Inseted ', row_count,' row(s) into records table.' ) AS Result;
END; -- this line closes the BEGIN statement above

-- executing the following statment will run the above procedure line by line
-- optionally we can also delete student table in the above procedure -- rem records should be deleted first
-- we can call the foll statment to execute but the above create stoded procedure should be run and created in the database
-- b/c it should exist like a table once and called any times even inside another stored procedures
-- stored prcedures can also take parameters / filtering condition and also return results like a programming
-- there are also functions for more specific purposes
