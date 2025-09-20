use nowdb;
select s.student_id ,    -- // 0
       s.first_name ,    -- // 1
       s.middle_name ,   -- // 2
       s.last_name ,     -- // 3
       s.date_of_birth , -- // 4
       s.gender ,        -- // 5
       r.academic_year , -- // 6
       r.grade ,         -- // 7
       r.section ,       -- // 8
       count(*) NumberOfSubjects , -- // 9
       sum(r.q1) Q1Sum , -- // 10
       RANK() OVER (PARTITION BY r.academic_year, r.grade, r.section ORDER BY sum(r.q1) DESC) Q1Rank , -- // 11
       sum(r.q2) Q2Sum , -- // 12
       RANK() OVER (PARTITION BY r.academic_year, r.grade, r.section ORDER BY sum(r.q2) DESC) Q2Rank , -- // 13
       RANK() OVER (PARTITION BY r.academic_year, r.grade, r.section ORDER BY sum(q1 + q2) DESC) Sem1Rank , -- // 14
       sum(r.q3) Q3Sum ,  -- // 15
       RANK() OVER (PARTITION BY r.academic_year, r.grade, r.section ORDER BY sum(r.q3) DESC) Q3Rank , -- // 16
       sum(r.q4) Q4Sum ,  -- // 17
       RANK() OVER (PARTITION BY r.academic_year, r.grade, r.section ORDER BY sum(r.q4) DESC) Q4Rank , -- // 18
       DENSE_RANK() OVER (PARTITION BY r.academic_year, r.grade, r.section ORDER BY sum(q3 + q4) DESC) Sem2Rank , -- // 19
       DENSE_RANK() OVER (PARTITION BY r.academic_year, r.grade ORDER BY sum(q1+ q2 +q3 + q4) DESC) OverAllRank , -- // 20
       COUNT(*) OVER (PARTITION BY r.academic_year, r.grade, r.section ORDER BY 1 DESC) TotalNumber , -- // 21
       NTILE(4) OVER (PARTITION BY r.academic_year, r.grade ORDER BY sum(q1+ q2 +q3 + q4) DESC) Over25NTileRank , -- //22
       s.photo,           -- // 23 photo
       s.is_Active ,      -- //24  is_Active
       sub.Q1TotalStudents Q1StudentCount ,  -- //25 Q1StudentCount
       sub.Q2TotalStudents Q2StudentCount ,  -- //26 Q2StudentCount
       sub.Q3TotalStudents Q3StudentCount ,  -- //27 Q3StudentCount
       sub.Q4TotalStudents Q4TotalStudents , -- //28 Q4TotalStudents
       RANK() OVER (PARTITION By r.academic_year, r.grade Order by sum(r.q1)Desc) Q1AllSectionRank, -- // 29 Q1AllSectionRank
       RANK() OVER (PARTITION By r.academic_year, r.grade Order by sum(r.q2)Desc) Q2AllSectionRank, -- // 30 Q2AllSectionRank
       RANK() OVER (PARTITION By r.academic_year, r.grade Order by sum(r.q3)Desc) Q3AllSectionRank, -- // 31 Q3AllSectionRank
       RANK() OVER (PARTITION By r.academic_year, r.grade Order by sum(r.q4)Desc) Q4AllSectionRank  -- // 32 Q4AllSectionRank
       FROM students s
            INNER JOIN student_records r ON s.student_id = r.student_id
            and s.student_id =1
            INNER JOIN
              (
              select count(distinct case
                                         when r.q1 > 0 then r.student_id
                                     end) Q1TotalStudents,
                      count(distinct case
                                         when r.q2 > 0 then r.student_id
                                     end) Q2TotalStudents,
                      count(distinct case
                                         when r.q3 > 0 then r.student_id
                                     end) Q3TotalStudents,
                      count(distinct case
                                         when r.q4 > 0 then r.student_id
                                     end) Q4TotalStudents ,
                      r.academic_year,
                      r.grade
               from student_records r
               group by r.academic_year,
                        r.grade
                        ) sub
            WHERE sub.academic_year=2014 -- r.academic_year
              AND sub.grade= r.grade
              GROUP BY r.student_id,
                     r.academic_year,
                     r.grade
