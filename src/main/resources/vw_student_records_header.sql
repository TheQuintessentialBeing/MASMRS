use nowdb;

-- select * from students;
-- select * from student_records;
-- select * from vw_student_records_header;

CREATE  VIEW `vw_student_records_header` AS

SELECT r.fk_student_id 'studentId' , r.academic_year ,r.grade ,r.section, count(*) NumberOfSubjects,  sum(r.q1) Q1Sum
     , RANK() OVER (PARTITION BY r.academic_year ,r.grade ,r.section order by r.q1 desc ) Q1Rank, sum( r.q2) as Q2Sum
     , RANK() OVER (PARTITION BY r.academic_year ,r.grade ,r.section order by r.q2 desc ) Q2Rank
     , RANK() OVER (PARTITION BY r.academic_year ,r.grade ,r.section order by sum(q1 + q2) desc ) Sem1Rank, sum(r.q3) Q3Sum
     , DENSE_RANK() OVER (PARTITION BY r.academic_year ,r.grade ,r.section order by r.q3 desc ) Q3Rank, sum( r.q4) as Q4Sum
     , DENSE_RANK() OVER (PARTITION BY r.academic_year ,r.grade ,r.section order by r.q4 desc ) Q4Rank
     , DENSE_RANK() OVER (PARTITION BY r.academic_year ,r.grade ,r.section order by sum(q1+ q2 +q3 + q4) desc ) Sem2Rank
     , DENSE_RANK() OVER (PARTITION BY r.academic_year ,r.grade order by sum(q1+ q2 +q3 + q4) desc ) OverAllRank
     , COUNT(*)     OVER (PARTITION BY r.academic_year ,r.grade order by sum(q1+ q2 +q3 + q4) desc ) TotalNumber
     , NTILE(4)     OVER (PARTITION BY r.academic_year ,r.grade order by sum(q1+ q2 +q3 + q4) desc ) Over25NTileRank
FROM student_records r
-- WHERE r.fk_student_id = 1001 AND r.academic_year ='2014'
GROUP BY r.fk_student_id, r.academic_year ,r.grade ,r.section
-- ORDER BY r.academic_year ,r.grade, r.section;

-- CALL `nowdb`.`spLoadTestData`();
-- Select * from vw_student_records_header;

/*
-- TODO
-- Note:
-- So if a an active student visit the site before his first quarter he still get empty report
-- so full outer join should be used - to include that
-- but mysql dont support FULL OUTER JOIN  - so we are forced to use unions otherwise only one on the join would be enough
-- not union gives only distincts rows but not union all ; both needs column rows to have the same name and type

SELECT s.student_id studentId, concat(s.first_name , ' ' , s.middle_name , ' ' , s.last_name ) 'Full Name'
     , s.date_of_birth , s.gender
     , r.academic_year ,r.grade ,r.section, count(*) NumberOfSubjects,  sum(r.q1) Q1Sum
     , RANK() OVER (PARTITION BY r.academic_year ,r.grade ,r.section order by r.q1 desc ) Q1Rank, sum( r.q2) as Q2Sum
     , RANK() OVER (PARTITION BY r.academic_year ,r.grade ,r.section order by r.q2 desc ) Q2Rank
     , RANK() OVER (PARTITION BY r.academic_year ,r.grade ,r.section order by sum(q1 + q2) desc ) Sem1Rank, sum(r.q3) Q3Sum
     , DENSE_RANK() OVER (PARTITION BY r.academic_year ,r.grade ,r.section order by r.q3 desc ) Q3Rank, sum( r.q4) as Q4Sum
     , DENSE_RANK() OVER (PARTITION BY r.academic_year ,r.grade ,r.section order by r.q4 desc ) Q4Rank
     , DENSE_RANK() OVER (PARTITION BY r.academic_year ,r.grade ,r.section order by sum(q1+ q2 +q3 + q4) desc ) Sem2Rank
     , DENSE_RANK() OVER (PARTITION BY r.academic_year ,r.grade order by sum(q1+ q2 +q3 + q4) desc ) OverAllRank
     , COUNT(*)     OVER (PARTITION BY r.academic_year ,r.grade order by sum(q1+ q2 +q3 + q4) desc ) TotalNumber
     , NTILE(4)     OVER (PARTITION BY r.academic_year ,r.grade order by sum(q1+ q2 +q3 + q4) desc ) Over25NTileRank
     , s.photo,s.is_Active
FROM students s
INNER JOIN student_records r
on s.student_id = r.fk_student_id
GROUP BY r.fk_student_id, r.academic_year ,r.grade ,r.section

-- WHERE r.fk_student_id = 1001 AND r.academic_year ='2014'

UNION ALL

SELECT s.student_id studentId, concat(s.first_name , ' ' , s.middle_name , ' ' , s.last_name ) 'Full Name'
     , s.date_of_birth , s.gender
     , r.academic_year ,r.grade ,r.section, count(*) NumberOfSubjects,  sum(r.q1) Q1Sum
     , RANK() OVER (PARTITION BY r.academic_year ,r.grade ,r.section order by r.q1 desc ) Q1Rank, sum( r.q2) as Q2Sum
     , RANK() OVER (PARTITION BY r.academic_year ,r.grade ,r.section order by r.q2 desc ) Q2Rank
     , RANK() OVER (PARTITION BY r.academic_year ,r.grade ,r.section order by sum(q1 + q2) desc ) Sem1Rank, sum(r.q3) Q3Sum
     , DENSE_RANK() OVER (PARTITION BY r.academic_year ,r.grade ,r.section order by r.q3 desc ) Q3Rank, sum( r.q4) as Q4Sum
     , DENSE_RANK() OVER (PARTITION BY r.academic_year ,r.grade ,r.section order by r.q4 desc ) Q4Rank
     , DENSE_RANK() OVER (PARTITION BY r.academic_year ,r.grade ,r.section order by sum(q1+ q2 +q3 + q4) desc ) Sem2Rank
     , DENSE_RANK() OVER (PARTITION BY r.academic_year ,r.grade order by sum(q1+ q2 +q3 + q4) desc ) OverAllRank
     , COUNT(*)     OVER (PARTITION BY r.academic_year ,r.grade order by sum(q1+ q2 +q3 + q4) desc ) TotalNumber
     , NTILE(4)     OVER (PARTITION BY r.academic_year ,r.grade order by sum(q1+ q2 +q3 + q4) desc ) Over25NTileRank
     , s.photo,s.is_Active
FROM student_records r
LEFT JOIN students s
on s.student_id = r.fk_student_id
-- WHERE r.fk_student_id = 1001 AND r.academic_year ='2014'
GROUP BY r.fk_student_id, r.academic_year ,r.grade ,r.section
*/