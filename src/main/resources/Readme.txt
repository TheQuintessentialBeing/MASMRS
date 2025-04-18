How to run test this project.
Note: this document should grow as a user's manual written in word and later to convert into pdf
 including screenshots and depict the flows.

1. look for the properties file - db name
2. check (change) username and password of your database
3. load student data (first) -- files are in resources/data folder
 3. 1 user post man to load  localhost:8081/students/loadstudent
4. load student record data (second) -- files are in resources/data folder
 4,1 use post man to load localhost:8081/students/loadstudentrecords
5. use the queries included in query.sql file to verify step 3 and 4 are successful.
6. then run the project
7.  register users - like Kidus's detail with email and password
7.1 log in again using email and password
7.2 click "Records" button
   7.2.1 will open localhost:8081/records/search page will open
7.3 Enter student id { eg. 1001, and a/year 2014)
 7.3.1 will open http://localhost:8081/records/search/1001/2014 page will open
7.4 Click View Report button - it will open a pdf report

TODO
-- look the resources/TODO.txt file and features you want or removed

--TODO READ
===================================================================================================================================
=Role Types                     |Purpose                                                                                          =
===================================================================================================================================
= 1.ROLE_STUDENT               | search reports by id and year & able to print ; send message on contact us and use about us page =
= 2.ROLE_TEACHER               | add / delete / edit / read records of the grade and sections assigned                            =
= 3.ROLE_ADMIN                 | can register add / delete(flag) / edit / read students                                           =
= 4.ROLE_HR                    | can work with employess & student payments ( future purpose )                                    =
= 5.ROLE_DIRECTORS             | see important statistics of total students , top students .....                                  =
===================================================================================================================================
SAMPLE USERS
id             email                           first_name           last_name       password         Role
========================================================================================================================================
1              student@mierafacademy.com        student             student         student          ROLE_STUDENT    ( app does by default)
3              teacher@@mierafacademy.com       teacher             teacher         teacher          ROLE_TEACHER    ( need update from student to teacher )
5              admin@mierafacademy.com          admin               admin           admin            ROLE_ADMIN      ( need update from student to admin )
7              hr@mierafacademy.com             hr                  hr              hr               ROLE_HR         ( need update from student to hr )
9              directors@mierafacademy.com     directors            directors       directors        ROLE_DIRECTORS  ( need update from student to directors )
sample update statment
update role set name = 'ROLE_TEACHER' where id =8; //

use the ff query to verify
use 'replacemewithyourdatabasenamehere'
Select u.first_name, u.last_name, u.email,r.name
from user u
inner join users_roles ur on u.id = ur.user_id
inner join role r on r.id = ur.user_id


How we can create those roles - our program always register students two ways to do this
1. change the hard code at .... class find this later on
2. register all of them as student but change it in the database table

1. <!-- section 1: Profile (For all NOT logged-in or visitor users)-->
       sec:authorize="!isAuthenticated()">

     <a class="nav-link" href="/">Home</a>
     <a href="/login" class="nav-link">Log in</a>
     <a href="/contactus" class="nav-link">Contact us</a>
     <a href="/aboutus" class="nav-link">About us</a>

2. <!--Profile (For all logged-in users)-->
     all users logged in has access to Home page
     <div sec:authorize="isAuthenticated() ">
     <a class="nav-link" href="/">Home</a>

     2.1 <!-- Students Only -->

     <li sec:authorize="hasRole('ROLE_STUDENT')" class="nav-item">
     <a class="nav-link" href="/records/search">Records</a>
     <li sec:authorize="hasRole('ROLE_STUDENT')" class="nav-item">
     <a href="/contactus" class="nav-link">Contact us</a>
     <a href="/aboutus" class="nav-link">About us</a>

     2.2 <!-- Teachers Only -->

      sec:authorize="hasRole('ROLE_TEACHER')">
      <div class="dropdown" style="display: inline!important;">
      Student Record Dashboard
      <ul class="dropdown-menu">
      <li><a class="nav-link css-custom-color" href="/student/add">Add</a></li>
      <li><a class="nav-link css-custom-color" href="/student/edit">Edit</a></li>
      <li><a class="nav-link css-custom-color" href="/student/delete">Delete</a>
      <li><a class="nav-link css-custom-color" href="/student/delete">View</a></li>
      <!-- Admins & HR Only Note for multiple roles use hasAnyRole -->

      2.3. <! ROLE_ADMIN and ROLE_HR

      sec:authorize="hasAnyRole('ROLE_ADMIN','ROLE_HR')" class="nav-item">
      <a class="nav-link" href="/employees">Employees</a>

      2.4  <!-- Directors and Admins Only -->

      <li sec:authorize="hasAnyRole('ROLE_ADMIN','ROLE_DIRECTORS')" class="nav-item">
      <a class="nav-link" href="student/statistics">Student Stats </a>
      <a sec:authentication="name" class="nav-link"></a>
      <!-- the above line is from spring framework auhentication name is read from the login automatically -->
      <a th:href="@{/logout}" class="nav-link">Log out</a>

