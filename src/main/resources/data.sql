
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


-- above rows are working and should be enterd before records as student_id fkey is needed
insert into Students (student_id, first_name, middle_name, last_name, date_of_birth, gender, registration_date, photo, kifle_ketema, kebele, house_number, phone, comment,is_active)
values (1001, 'Mattie', 'Keen', 'Christiano', '1993-02-03', 'M', '2005-10-24', null, 'France', 1, 'Vagney', '249-354-5277', '4/9/2020',true)
,(1002, 'Kanya', 'Tiler', 'Maynard', '1995-10-02', 'F', '2018-12-06', null, 'China', 2, 'Qiaolin', '191-282-1592', '5/5/2021',true)
,(1003, 'Lodovico', 'Percival', 'Nealon', '1992-03-10', 'M', '2022-11-06', null, 'Portugal', 3, 'Ameixoeira', '274-546-9306', '12/29/2021',true);
;


insert into Records
(records_id,student_id, academic_year, grade,subject  ,  section, q1,  q2, q3, q4, updated_by, comment) values
 -- student 1
 ( 1      ,1001      , 2016         ,10     ,'English', 'A'  , 100, 90, 80, 80, 1, 'New')
,( 2      ,1001      , 2016         ,10     ,'Math', 'A'  , 100, 90, 80, 80, 1, 'New')
,( 3      ,1001      , 2016         ,10     ,'Physics', 'A'  , 100, 90, 80, 80, 1, 'New')
,( 4      ,1001      , 2016         ,10     ,'Biology', 'A'  , 100, 90, 80, 80, 1, 'New')
,( 5      ,1001      , 2016         ,10     ,'Chemistry', 'A'  , 100, 90, 80, 80, 1, 'New')
,( 6      ,1001      , 2016         ,10     ,'IT', 'A'  , 100, 90, 80, 80, 1, 'New')

-- //--
,( 7      ,1001      , 2017         ,11     ,'English', 'D'  , 100, 90, 0, 0, 1, 'New')
,( 8      ,1001      , 2017         ,11     ,'Math', 'D'  , 100, 90, 0, 0, 1, 'New')
,( 9      ,1001      , 2017         ,11     ,'Physics', 'D'  , 100, 90, 0, 0, 1, 'New')
,( 10      ,1001      , 2017         ,11     ,'Biology', 'D'  , 100, 90, 0, 0, 1, 'New')
,( 11      ,1001      , 2017         ,11     ,'Chemistry', 'D'  , 100, 90, 0, 0, 1, 'New')
,( 12      ,1001      , 2017         ,11     ,'IT', 'D'  , 100, 90, 0, 0, 1, 'New')
-- student 2 gr 9
 ,(13      ,1002      , 2015         ,9     ,'English', 'A'  , 100, 90, 80, 80, 1, 'New')
,( 14      ,1002      , 2015         ,9     ,'Math', 'A'  , 100, 90, 80, 80, 1, 'New')
,( 15      ,1002      , 2015         ,9     ,'Physics', 'A'  , 100, 90, 80, 80, 1, 'New')
,( 16      ,1002      , 2015         ,9     ,'Biology', 'A'  , 100, 90, 80, 80, 1, 'New')
,( 17      ,1002      , 2015         ,9     ,'Chemistry', 'A'  , 100, 90, 80, 80, 1, 'New')
,( 18      ,1002      , 2015         ,9     ,'IT', 'A'  , 100, 90, 80, 80, 1, 'New')

-- //-- gr 10
,( 19      ,1002      , 2016         ,10     ,'English', 'D'  , 100, 90, 0, 0, 1, 'New')
,( 20      ,1002      , 2016         ,10     ,'Math', 'D'  , 100, 90, 0, 0, 1, 'New')
,( 21      ,1002      , 2016         ,10     ,'Physics', 'D'  , 100, 90, 0, 0, 1, 'New')
,( 22      ,1002      , 2016         ,10     ,'Biology', 'D'  , 100, 90, 0, 0, 1, 'New')
,( 23      ,1002      , 2016         ,10     ,'Chemistry', 'D'  , 100, 90, 0, 0, 1, 'New')
,( 24      ,1002      , 2016         ,10     ,'IT', 'D'  , 100, 90, 0, 0, 1, 'New')
-- // gr 11
,( 25      ,1002      , 2017         ,11     ,'English', 'E'  , 100, 90, 0, 0, 1, 'New')
,( 26      ,1002      , 2017         ,11     ,'Math', 'E'  , 100, 90, 0, 0, 1, 'New')
,( 27      ,1002      , 2017         ,11     ,'Physics', 'E'  , 100, 90, 0, 0, 1, 'New')
,( 28      ,1002      , 2017         ,11     ,'Biology', 'E'  , 100, 90, 0, 0, 1, 'New')
,( 29      ,1002      , 2017         ,11     ,'Chemistry', 'E'  , 100, 90, 0, 0, 1, 'New')
,( 30      ,1002      , 2017         ,11     ,'IT', 'E'  , 100, 90, 0, 0, 1, 'New')

-- //-- student 3  gr 9
,( 31      ,1003      , 2014         ,11     ,'English', 'D'  , 100, 90, 0, 0, 1, 'New')
,( 32      ,1003      , 2014         ,11     ,'Math', 'D'  , 100, 90, 0, 0, 1, 'New')
,( 33      ,1003      , 2014         ,11     ,'Physics', 'D'  , 100, 90, 0, 0, 1, 'New')
,( 34      ,1003      , 2014         ,11     ,'Biology', 'D'  , 100, 90, 0, 0, 1, 'New')
,( 35      ,1003      , 2014         ,11     ,'Chemistry', 'D'  , 100, 90, 0, 0, 1, 'New')
,( 36      ,1003      , 2014         ,11     ,'IT', 'D'  , 100, 90, 0, 0, 1, 'New')

-- // gr 10
,(37      ,1003      , 2015         ,10     ,'English', 'A'  , 100, 90, 80, 80, 1, 'New')
,( 38      ,1003      , 2015         ,10     ,'Math', 'A'  , 100, 90, 80, 80, 1, 'New')
,( 39      ,1003      , 2015         ,10     ,'Physics', 'A'  , 100, 90, 80, 80, 1, 'New')
,( 40      ,1003      , 2015         ,10     ,'Biology', 'A'  , 100, 90, 80, 80, 1, 'New')
,( 41      ,1003      , 2015         ,10     ,'Chemistry', 'A'  , 100, 90, 80, 80, 1, 'New')
,( 42      ,1003      , 2015         ,10     ,'IT', 'A'  , 100, 90, 80, 80, 1, 'New')

-- //-- gr 11
,( 43      ,1003      , 2016         ,11     ,'English', 'C'  , 100, 90, 65, 36, 1, 'New')
,( 44      ,1003      , 2016         ,11     ,'Math', 'C'  , 100, 90, 100, 50, 1, 'New')
,( 45      ,1003      , 2016         ,11     ,'Physics', 'C'  , 100, 90, 70, 79, 1, 'New')
,( 46      ,1003      , 2016         ,11     ,'Biology', 'C'  , 100, 90, 75, 83, 1, 'New')
,( 47      ,1003      , 2016         ,11     ,'Chemistry', 'C'  , 100, 90, 87, 0, 1, 'New')
,( 48      ,1003      , 2016         ,11     ,'IT', 'C'  , 100, 90, 85, 86, 1, 'New')

-- //-- gr 12
,( 49      ,1003      , 2017         ,12     ,'English', 'D'  , 100, 90, 0, 0, 1, 'New')
,( 50      ,1003      , 2017         ,12     ,'Math', 'D'  , 100, 90, 0, 0, 1, 'New')
,( 51      ,1003      , 2017         ,12     ,'Physics', 'D'  , 100, 90, 0, 0, 1, 'New')
,( 52      ,1003      , 2017         ,12     ,'Biology', 'D'  , 100, 90, 0, 0, 1, 'New')
,( 53      ,1003      , 2017         ,12     ,'Chemistry', 'D'  , 100, 90, 0, 0, 1, 'New')
,( 54      ,1003      , 2017         ,12     ,'IT', 'D'  , 100, 90, 0, 0, 1, 'New');







