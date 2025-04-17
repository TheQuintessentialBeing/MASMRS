use nowdb;
-- STUDENTS_RECORDS Should be deleted first but student should be loaded first ( reverse)
-- 0
select r.student_id, s.student_id, s.first_name, r.q1 from student_records r
inner join students s where r.student_id=s.student_id and r.student_id =1001
-- 1
select * from student_records
where
-- student_id=1005
academic_year=2017
and grade = 12
and section ='C';
-- 2
delete  from student_records where record_id > 0;
-- 3
select *  from students where student_id=1001;
-- 4
delete  from students where student_id > 0;
-- 5
select *  from students;
-- 6
CALL PROCEDURE `nowdb`.`spLoadTestData` ();
-- 7
select *  from students;
-- 8
select * from student_records where record_id > 0;
-- 9
CALL `nowdb`. `spLoadStudentRecordTestData` ();
-- 10
select * from student_records where record_id > 0;

-- alter table users_roles drop foreign key FKgd3iendaoyh04b95ykqise6qh
-- Check the view
-- 1
SELECT  * FROM  nowdb.vw_student_records_header

DROP VIEW `nowdb`.`vw_student_records_header`

select * from nowdb.student_records;

SELECT
             s.student_id /* ,s.first_name,s.middle_name,s.last_name,s.date_of_birth,s.gender */ ,r.academic_year,r.grade,r.section
            ,count(*) NumberOfSubjects
            ,sum(r.q1) Q1Sum
            ,RANK() OVER (PARTITION BY r.academic_year ,r.grade ,r.section order by r.q1 desc ) Q1Rank
            ,sum( r.q2) as Q2Sum
            ,RANK() OVER (PARTITION BY r.academic_year ,r.grade ,r.section order by r.q2 desc ) Q2Rank
            ,RANK() OVER (PARTITION BY r.academic_year ,r.grade ,r.section order by sum(q1 + q2) desc ) Sem1Rank
            ,sum(r.q3) Q3Sum
            ,DENSE_RANK() OVER (PARTITION BY r.academic_year ,r.grade ,r.section order by r.q3 desc ) Q3Rank
            ,sum( r.q4) as Q4Sum
            ,DENSE_RANK() OVER (PARTITION BY r.academic_year ,r.grade ,r.section order by r.q4 desc ) Q4Rank
            ,DENSE_RANK() OVER (PARTITION BY r.academic_year ,r.grade ,r.section order by sum(q1+ q2 +q3 + q4) desc ) Sem2Rank
            ,DENSE_RANK() OVER (PARTITION BY r.academic_year ,r.grade order by sum(q1+ q2 +q3 + q4) desc ) OverAllRank
            ,COUNT(*)     OVER (PARTITION BY r.academic_year ,r.grade order by sum(q1+ q2 +q3 + q4) desc ) TotalNumber
            ,NTILE(4)     OVER (PARTITION BY r.academic_year ,r.grade order by sum(q1+ q2 +q3 + q4) desc ) Over25NTileRank
            ,s.photo,s.is_Active
FROM students s
INNER JOIN student_records r on s.student_id = r.student_id
WHERE -- s.student_id = 1001 AND
r.academic_year in ('2016','2017')
GROUP BY r.academic_year ,r.grade ,r.section

use nowdb

SELECT  r.academic_year , r.grade , r.section , r.student_id , r.subject
, r.q1
, r.q2
, r.q3
, r.q4
FROM student_records r
WHERE  r.academic_year IN ('2016','2017')
ORDER BY r.academic_year , r.grade , r.section , r.student_id , r.subject




