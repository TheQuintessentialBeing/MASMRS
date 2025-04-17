package com.macademy.recordmgmt.utility;

import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
@NamedQueries({@NamedQuery(name = "SqlSearchRecordsByStudentId", query = "FROM StudentRecord r where r.StudentId=:studentid"), @NamedQuery(name = "sqlDeleteStudentByStudentId", query = "DELETE FROM Student p where p.studentId =:studentId"),// check param names here
})
public class HQL {}