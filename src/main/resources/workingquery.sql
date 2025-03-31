use nowdb;
-- again join this header to get all the details

select q2.*, f.subject,f.q1,f.q2,f.q3,f.q4
from records f
join LATERAL (

-- Join student table with the stat subquery using student_id on both columns
-- purpose is to fetch additional student info like name, date of birth ....
select distinct t.student_id, t.first_name, t.last_name, q.academic_year ,q.grade ,q.section
, q.Q1Sum , q.Q1Rank , q.Q2Sum , q.Q2Rank , q.Sem1Rank
, q.Q3Sum , q.Q3Rank , q.Q4Sum , q.Q4Rank , q.Sem2Rank, q.NumberOfSubjects
 from students t
left join (
-- NOTE : Sub query :
-- Data granularity is student_id, academic_year , grade & section
-- get the following student's header statistics from records table
-- granular data for joining later on by
-- header level statistics eg.
--           sum of q1, q2, q3, q4, sem 1, sem 2
--           rank of q1, q2, q3, q4 , sem 1, sem 2
-- count of subjects if students miss exams and should not be considered in statistics
-- TODO : Average for q1, q2, q3, q4, sem 1, sem 2

SELECT r.student_id , r.academic_year ,r.grade ,r.section, count(*) NumberOfSubjects
     , sum(r.q1) Q1Sum
     , RANK() OVER (PARTITION BY r.academic_year ,r.grade ,r.section order by r.q1 desc ) Q1Rank
     , sum( r.q2) as Q2Sum
     , RANK() OVER (PARTITION BY r.academic_year ,r.grade ,r.section order by r.q2 desc ) Q2Rank
     , RANK() OVER (PARTITION BY r.academic_year ,r.grade ,r.section order by sum(q1 + q2) desc ) Sem1Rank
     -- q2 and q4 and sem2
     , sum(r.q3) Q3Sum
     , DENSE_RANK() OVER (PARTITION BY r.academic_year ,r.grade ,r.section order by r.q3 desc ) Q3Rank
     , sum( r.q4) as Q4Sum
     , DENSE_RANK() OVER (PARTITION BY r.academic_year ,r.grade ,r.section order by r.q4 desc ) Q4Rank
     , DENSE_RANK() OVER (PARTITION BY r.academic_year ,r.grade ,r.section order by sum(q3 + q4) desc ) Sem2Rank
FROM records r
where r.student_id = 1001 AND r.academic_year ='2014'
GROUP BY r.student_id, r.academic_year ,r.grade ,r.section
Order by r.academic_year,r.grade, r.section
) as q
-- Join the two table using the pk and fk i,e student_id
ON t.student_id = q.student_id
group by q.student_id, q.academic_year ,q.grade ,q.section
) q2
ON f.student_id = q2.student_id
-- where f.student_id in('1001')
group by f.academic_year asc ,f.grade asc ,f.section , f.subject asc
--  Mule's Cross apply
select b.*
From
(select r.student_id , r.academic_year ,r.grade ,r.section, count(*) NumberOfSubjects
     , sum(r.q1) Q1Sum
     , RANK() OVER (PARTITION BY r.academic_year ,r.grade ,r.section order by r.q1 desc ) Q1Rank
     , sum( r.q2) as Q2Sum
     , RANK() OVER (PARTITION BY r.academic_year ,r.grade ,r.section order by r.q2 desc ) Q2Rank
     , RANK() OVER (PARTITION BY r.academic_year ,r.grade ,r.section order by sum(q1 + q2) desc ) Sem1Rank
     -- q2 and q4 and sem2
     , sum(r.q3) Q3Sum
     , DENSE_RANK() OVER (PARTITION BY r.academic_year ,r.grade ,r.section order by r.q3 desc ) Q3Rank
     , sum( r.q4) as Q4Sum
     , DENSE_RANK() OVER (PARTITION BY r.academic_year ,r.grade ,r.section order by r.q4 desc ) Q4Rank
     , DENSE_RANK() OVER (PARTITION BY r.academic_year ,r.grade ,r.section order by sum(q3 + q4) desc ) Sem2Rank
FROM records r
where r.student_id = 1001 AND r.academic_year =2014
) as a
JOIN LATERAL (
select *
from students  s
where a.student_id = s.student_id

) as b

-- GROUP BY r.student_id, r.academic_year ,r.grade ,r.section


-- check
SELECT
    r.student_id,
    r.academic_year,
    r.grade,
    r.section,
    SUM(r.total_score) AS total_score,
    AVG(r.total_score) AS average_score
FROM
    students s
INNER JOIN  (
    SELECT
        sr.student_id,
        sr.academic_year,
        sr.grade,
        sr.section,
        sr.total_score
    FROM
        results sr
    WHERE
        sr.student_id = s.student_id
) AS r
GROUP BY
    r.student_id,
    r.academic_year,
    r.grade,
    r.section;
