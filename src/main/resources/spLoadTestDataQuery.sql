use studentdb;
CREATE PROCEDURE `studentdb`.`spLoadTestData` ()
-- check if Student table has no records already - display appropraite messages
-- BEGIN
-- DECLARE students_row_count INT;
-- IF EXISTS ( SELECT 1 FROM students LIMIT 1 )THEN DELETE FROM students;
-- SELECT 'students table was not empty and data deleted.' AS Message;
-- ELSE
-- SELECT 'students table was already empty.' AS Message;

-- END IF;
-- Inserting data into students table
-- SET SQL_SAFE_UPDATE =0; -- it tries to avoid accidental delete when where clause is not mentioned ; so 0 is to turn off this feature 1 is to turn ON
-- Delete  from student_records where student_id >0;
-- SET SQL_SAFE_UPDATE =0;
-- Delete  from students where record_id >0;

insert into studentdb.students (student_id, first_name, middle_name, last_name, date_of_birth, gender, registration_date, photo, kifle_ketema, kebele, house_number, phone, comment,is_active)
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
,(1101, 'Tizta', 'Fentaw', 'Muluneh', '1992-03-10', 'M', '2022-11-06', null, 'Mexico', 3, 'kebele 1', '0974-546-9306', '12/29/2021',true)

-- verify if student data is loaded successfully
-- if possible show failure msg if student id does not exist before we add record details below
--  at the end show how many rows are in the table , how many of them has details , and dont have details
-- if there are rejections of data entry b/c student is not already registered

-- Get number of students inserted

-- SET students_row_count = ROW_COUNT();

-- Show Message count of records inserted

-- SELECT concat('Inseted ', students_row_count,' row(s) into students table.' ) AS Result;




-- BELOW is to lead to student_records table
-- BEGIN
-- DECLARE row_count INT;
-- IF EXISTS ( SELECT 1 FROM student_records LIMIT 1 )THEN DELETE FROM records;
 --   SELECT 'records table was not empty and data deleted.' AS Message;
-- ELSE
   -- SELECT 'records table was already empty.' AS Message;

-- END IF;
-- Inserting data into records table


INSERT INTO student_records
(record_id, student_id, academic_year, grade,subject  ,  section, q1,  q2, q3, q4, updated_by, comment) values
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
,(33      , 1005      , 2015        ,10    ,'Physics',    'A'  , 65, 90, 80, 80, 1, 'Admitted in 2014')

,(34      , 1006      , 2015        ,10    ,'English',    'B'  , 63, 90, 77, 80, 1, 'Admitted in 2014')
,(35      , 1006      , 2015        ,10    ,'Math',       'B'  , 61, 90, 80, 80, 1, 'Admitted in 2014')
,(36      , 1006      , 2015        ,10    ,'Physics',    'B'  , 59, 90, 80, 80, 1, 'Admitted in 2014')
-- Admitted in  students in 2015
,(37      , 1007     , 2015         ,10    ,'English',    'A'  , 57, 90, 77, 80, 1, 'Admitted in 2015')
,(38      , 1007     , 2015         ,10    ,'Math',       'A'  , 55, 90, 80, 80, 1, 'Admitted in 2015')
,(39      , 1007     , 2015         ,10    ,'Physics',    'A'  , 53, 90, 80, 80, 1, 'Admitted in 2015')

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
,(51      , 1005      , 2016        ,11    ,'Physics',    'C'  , 29, 90, 80, 80, 1, 'Admitted in 2014')

,(52      , 1006      , 2016        ,11    ,'English',    'B'  , 27, 90, 77, 80, 1, 'Admitted in 2014')
,(53      , 1006      , 2016        ,11    ,'Math',       'B'  , 25, 90, 80, 80, 1, 'Admitted in 2014')
,(54      , 1006      , 2016        ,11    ,'Physics',    'B'  , 23, 90, 80, 80, 1, 'Admitted in 2014')

,(55      , 1007      , 2016        ,11    ,'English',    'B'  , 19, 90, 77, 80, 1, 'Admitted in 2015')
,(56      , 1007      , 2016        ,11    ,'Math',       'B'  , 17, 90, 80, 80, 1, 'Admitted in 2015')
,(57      , 1007      , 2016        ,11    ,'Physics',    'B'  , 17, 90, 80, 80, 1, 'Admitted in 2015')

-- Admitted in  2016 1st semester

,(58      , 1008      , 2016        ,11    ,'English',    'C'  , 19, 90, 77, 80, 1, 'Admitted in 2015')
,(59      , 1008      , 2016        ,11    ,'Math',       'C'  , 17, 90, 80, 80, 1, 'Admitted in 2015')
,(60      , 1008      , 2016        ,11    ,'Physics',    'C'  , 17, 90, 80, 80, 1, 'Admitted in 2015')
-- Admitted in  2016 2nd semester q1 and q2 are 0
,(61      , 1009      , 2016        ,11    ,'English',    'B'  , 0, 0, 77, 80, 1, 'Admitted in 2015')
,(62      , 1009      , 2016        ,11    ,'Math',       'B'  , 0, 0, 80, 80, 1, 'Admitted in 2015')
,(63      , 1009      , 2016        ,11    ,'Physics',    'B'  , 0, 0, 80, 80, 1, 'Admitted in 2015')

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
,(68      , 1003      , 2017        ,12    ,'Physics',    'B'  , 16, 90, 80, 80, 1, 'Admitted in 2017')

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
,(85      , 1009     , 2017         ,12    ,'Physics',    'B'  , 42, 90, 0, 0, 1, 'Admitted in 2017')

,(86      , 1010     , 2017         ,12    ,'English',    'C'  , 44, 90, 0, 0, 1, 'Admitted in 2017')
,(87      , 1010     , 2017         ,12    ,'Math',       'C'  , 46, 90, 0, 0, 1, 'Admitted in 2017')
,(88      , 1010     , 2017         ,12    ,'Physics',    'C'  , 48, 90, 0, 0, 1, 'Admitted in 2017')

,(89      , 1011     , 2017         ,12    ,'English',    'B'  , 50, 90, 0, 0, 1,  'Admitted in 2017')
,(90      , 1011     , 2017         ,12    ,'Math',       'B'  , 52, 90, 0, 0, 1,  'Admitted in 2017')
,(91      , 1011     , 2017         ,12    ,'Physics',    'B'  , 54, 90, 0, 0, 1,  'Admitted in 2017')

,(92      , 1012     , 2017         ,12    ,'English',    'B'  , 56, 90, 0, 0, 1,  'Admitted in 2017')
,(93      , 1012     , 2017         ,12    ,'Math',       'B'  , 100, 90, 0, 0, 1, 'Admitted in 2017')
,(94      , 1012     , 2017         ,12    ,'Physics',    'B'  , 10, 90, 0, 0, 1,  'Admitted in 2017')

,(95      , 1013     , 2017         ,12    ,'English',    'B'  , 58, 90, 0, 0, 1,  'Admitted in 2017')
,(96      , 1013     , 2017         ,12    ,'Math',       'B'  , 60, 90, 0, 0, 1,  'Admitted in 2017')
,(97      , 1013     , 2017         ,12    ,'Physics',    'B'  , 62, 90, 0, 0, 1,  'Admitted in 2017')

,(98      , 1014     , 2017         ,12    ,'English',    'B'  , 64, 90, 0, 0, 1, 'Admitted in 2017')
,(99      , 1014     , 2017         ,12    ,'Math',       'B'  , 66, 90, 0, 0, 1, 'Admitted in 2017')
,(100     , 1014     , 2017         ,12    ,'Physics',    'B'  , 68, 90, 0, 0, 1, 'Admitted in 2017')


-- additional testing


-- additional
 (101       , 1001      , 2014        ,9     ,'IT',          'A'  , 1, 2, 77, 81, 48, 'Admitted in 2014')
,(102       , 1001      , 2014        ,9     ,'Biology',     'A'  , 3, 4, 80, 80, 56, 'Admitted in 2014')
,(103       , 1001	    , 2014        ,9     ,'Chemistry',   'A'  , 5, 60, 80, 70, 88,  'Admitted in 2014')

,(104       , 1002      , 2014        ,9     ,'IT',          'A'  , 7, 90, 77, 70, 18, 'Admitted in 2014')
,(105       , 1002      , 2014        ,9     ,'Biology',     'A'  , 9, 90, 80, 60, 19, 'Admitted in 2014')
,(106       , 1002      , 2014        ,9     ,'Chemistry',   'A'  , 11, 90, 74, 45, 61, 'Admitted in 2014')

,(107       , 1003      , 2014        ,9     ,'IT',          'A'  , 13, 90, 77, 80, 21, 'Admitted in 2014')
,(108       , 1003      , 2014        ,9     ,'Biology',     'A'  , 15, 90, 10, 80, 51, 'Admitted in 2014')
,(109       , 1003      , 2014        ,9     ,'Chemistry',   'A'  , 17, 90, 30, 39, 61, 'Admitted in 2014')
			--  B
,(110      , 1004      , 2014        ,9     ,'IT',           'B'  , 19, 90, 77, 80, 71, 'Admitted in 2014')
,(111      , 1004      , 2014        ,9     ,'Biology',      'B'  , 21, 90, 18, 80, 91, 'Admitted in 2014')
,(112      , 1004      , 2014        ,9     ,'Chemistry',    'B'  , 23, 90, 50, 53, 88, 'Admitted in 2014')

,(113      , 1005      , 2014        ,9     ,'IT',           'B'  , 25, 90, 77, 50, 12, 'Admitted in 2014')
,(114      , 1005      , 2014        ,9     ,'Biology',      'B'  , 27, 90, 80, 80, 65, 'Admitted in 2014')
,(115      , 1005      , 2014        ,9     ,'Chemistry',    'B'  , 29, 90, 80, 80, 45,  'Admitted in 2014')

,(116      , 1006      , 2014        ,9     ,'IT',           'B'  , 99, 90, 77, 80, 89, 'Admitted in 2014')
,(117      , 1006      , 2014        ,9     ,'Biology',      'B'  , 97, 90, 80, 80, 88, 'Admitted in 2014')
,(118      , 1006      , 2014        ,9     ,'Chemistry',    'B'  , 95, 90, 80, 80, 89,  'Admitted in 2014')

 (119       , 1014      , 2014        ,9     ,'English',    'A'  , 71, 2, 77, 80, 12, 'Admitted in 2014')
,(120       , 1014      , 2014        ,9     ,'Math',       'A'  , 30, 40, 80, 80, 13, 'Admitted in 2014')
,(121       , 1014	    , 2014         ,9     ,'Physics',    'A'  , 55, 60, 80, 80, 21,  'Admitted in 2014')
,(122       , 1014      , 2014        ,9     ,'IT',          'A'  , 41, 32, 77, 80, 11, 'Admitted in 2014')
,(123       , 1014      , 2014        ,9     ,'Biology',     'A'  , 83, 4, 80, 80, 56, 'Admitted in 2014')
,(124       , 1014	    , 2014        ,9     ,'Chemistry',   'A'  , 15, 60, 80, 80, 55,  'Admitted in 2014')

,(125       , 1016      , 2014        ,9     ,'English',    'A'  , 27, 90, 77, 80, 32, 'Admitted in 2014')
,(126       , 1016      , 2014        ,9     ,'Math',       'A'  , 90, 90, 80, 80, 69, 'Admitted in 2014')
,(127       , 1016      , 2014        ,9     ,'Physics',    'A'  , 11, 90, 80, 80, 53, 'Admitted in 2014')
,(128       , 1016      , 2014        ,9     ,'IT',          'A'  , 37, 90, 77, 80, 48, 'Admitted in 2014')
,(129       , 1016      , 2014        ,9     ,'Biology',     'A'  , 93, 90, 80, 80, 89, 'Admitted in 2014')
,(130       , 1016      , 2014        ,9     ,'Chemistry',   'A'  , 11, 90, 80, 80, 51, 'Admitted in 2014')

,(131       , 1017      , 2014        ,9     ,'English',    'A'  , 13, 90, 77, 80, 31, 'Admitted in 2014')
,(132      , 1017      , 2014        ,9     ,'Math',       'A'  ,  55, 90, 80, 80, 11, 'Admitted in 2014')
,(133       , 1017      , 2014        ,9     ,'Physics',    'A'  , 27, 90, 80, 80, 21, 'Admitted in 2014')
,(134       , 1017      , 2014        ,9     ,'IT',          'A'  , 18, 90, 77, 80, 81, 'Admitted in 2014')
,(135       , 1017      , 2014        ,9     ,'Biology',     'A'  , 75, 90, 80, 80, 71, 'Admitted in 2014')
,(136       , 1017      , 2014        ,9     ,'Chemistry',   'A'  , 78, 90, 80, 80, 51, 'Admitted in 2014')


,(137      , 1018      , 2014        ,9     ,'English',    'B'  , 19, 80, 77, 80, 41, 'Admitted in 2014')
,(138      , 1018      , 2014        ,9     ,'Math',       'B'  , 21, 60, 80, 80, 18, 'Admitted in 2014')
,(139      , 1018      , 2014        ,9     ,'Physics',    'B'  , 23, 50, 80, 80, 12, 'Admitted in 2014')
,(140      , 1018      , 2014        ,9     ,'IT',           'B'  , 19, 32, 77, 80, 1, 'Admitted in 2014')
,(141      , 1018      , 2014        ,9     ,'Biology',      'B'  , 21, 45, 80, 80, 10, 'Admitted in 2014')
,(142      , 1018      , 2014        ,9     ,'Chemistry',    'B'  , 23, 80, 80, 80, 12, 'Admitted in 2014')

,(143      , 1019      , 2014        ,9     ,'English',    'B'  , 25, 50, 77, 80, 18, 'Admitted in 2014')
,(144      , 1019      , 2014        ,9     ,'Math',       'B'  , 27, 40, 80, 80, 19, 'Admitted in 2014')
,(145      , 1019      , 2014        ,9     ,'Physics',    'B'  , 29, 30, 80, 80, 18,  'Admitted in 2014')
,(146      , 1019      , 2014        ,9     ,'IT',           'B'  , 25, 70, 77, 80, 21, 'Admitted in 2014')
,(147      , 1019      , 2014        ,9     ,'Biology',      'B'  , 27, 90, 80, 80, 31, 'Admitted in 2014')
,(148      , 1019      , 2014        ,9     ,'Chemistry',    'B'  , 29, 90, 80, 80, 21,  'Admitted in 2014')

,(149      , 1020      , 2014        ,9     ,'English',    'B'  , 99, 30, 77, 80, 19, 'Admitted in 2014')
,(150      , 1020      , 2014        ,9     ,'Math',       'B'  , 97, 22, 80, 80, 31, 'Admitted in 2014')
,(151      , 1020      , 2014        ,9     ,'Physics',    'B'  , 95, 17, 80, 80, 11,  'Admitted in 2014')

,(152      , 1020      , 2014        ,9     ,'IT',           'B'  , 99, 75, 77, 80, 81, 'Admitted in 2014')
,(153      , 1020      , 2014        ,9     ,'Biology',      'B'  , 97, 12, 80, 80, 61, 'Admitted in 2014')
,(154      , 1020      , 2014        ,9     ,'Chemistry',    'B'  , 95, 88, 80, 80, 81,  'Admitted in 2014')











--

-- Get number of records inserted

-- SET row_count = ROW_COUNT();

-- Show Message count of records inserted

-- SELECT concat('Inseted ', row_count,' row(s) into records table.' ) AS Result;
-- END;

