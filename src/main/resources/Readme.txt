How to run test this project.
Note: this document should grow as a user's manual ( preferabley pdf)
 including screenshots at the end of the day.
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