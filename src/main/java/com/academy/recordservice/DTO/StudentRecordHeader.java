package com.academy.recordservice.DTO;

import com.academy.recordservice.models.StudentRecord;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Date;
import java.util.List;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter

@FieldDefaults(level = AccessLevel.PRIVATE)
// @JsonInclude(JsonInclude.Include.NON_NULL)  // Ignore null value
public class StudentRecordHeader {
    private Integer studentId;
    private String firstName;
    private String middleName;
    private String lastName;
    private Date dateOfBirth;

    //TODO optional restrinct to one character on student table
    private Character gender;
    private String academicYear;
    private Integer grade;

    // TODO optional this could be changed to like gender to Character above
    private String section;
    // Calculated fields using query Rank , Dense_Rank ... functions
    private Integer numberOfSubjects;
    // quarter 1 semester 1
    private double quarterOneSum;
    private Integer quarterOneRank;
    // quarter  2 semester 1
    private double quarterTwoSum;
    private Integer quarterTwoRank;
    private Integer semesterOneRank;
    // quarter 3 semester 2
    private double quarterThreeSum;
    private Integer quarterThreeRank;
    // quarter 4  semester 2
    private double quarterFourSum;
    private Integer quarterFourRank;
    private Integer semesterTwoRank;
    // rank from all section and percentile
    private Integer allSectionRank;
    private Integer totalNumberOfStudents;
    private Integer allSectionPercentile;
    // student photo and status
    private byte[] photo;
    private boolean isActive;
    private Integer Q1StudentCount;
    private Integer Q2StudentCount;
    private Integer Q3StudentCount;
    private Integer Q4StudentCount;
    private Integer Q1AllSectionRank;
    private Integer Q2AllSectionRank;
    private Integer Q3AllSectionRank;
    private Integer Q4AllSectionRank;
    private List<StudentRecord> detailrows;

    public StudentRecordHeader(Integer studentId, String firstName, String middleName, String lastName, Date dateOfBirth, Character gender, String academicYear, Integer grade, String section, Integer numberOfSubjects, double quarterOneSum, Integer quarterOneRank, double quarterTwoSum, Integer quarterTwoRank, Integer semesterOneRank, double quarterThreeSum, Integer quarterThreeRank, double quarterFourSum, Integer quarterFourRank, Integer semesterTwoRank, Integer allSectionRank, Integer totalNumberOfStudents, Integer allSectionPercentile, byte[] photo, boolean isActive, Integer Q1StudentCount, Integer Q2StudentCount, Integer Q3StudentCount, Integer Q4StudentCount, Integer Q1AllSectionRank, Integer Q2AllSectionRank, Integer Q3AllSectionRank, Integer Q4AllSectionRank) {
        this.studentId             = studentId;
        this.firstName             = firstName;
        this.middleName            = middleName;
        this.lastName              = lastName;
        this.dateOfBirth           = dateOfBirth;
        this.gender                = gender;
        this.academicYear          = academicYear;
        this.grade                 = grade;
        this.section               = section;
        this.numberOfSubjects      = numberOfSubjects;
        this.quarterOneSum         = quarterOneSum;
        this.quarterOneRank        = quarterOneRank;
        this.quarterTwoSum         = quarterTwoSum;
        this.quarterTwoRank        = quarterTwoRank;
        this.semesterOneRank       = semesterOneRank;
        this.quarterThreeSum       = quarterThreeSum;
        this.quarterThreeRank      = quarterThreeRank;
        this.quarterFourSum        = quarterFourSum;
        this.quarterFourRank       = quarterFourRank;
        this.semesterTwoRank       = semesterTwoRank;
        this.allSectionRank        = allSectionRank;
        this.totalNumberOfStudents = totalNumberOfStudents;
        this.allSectionPercentile  = allSectionPercentile;
        this.photo                 = photo;
        this.isActive              = isActive;
        this.Q1StudentCount        = Q1StudentCount;
        this.Q2StudentCount        = Q2StudentCount;
        this.Q3StudentCount        = Q3StudentCount;
        this.Q4StudentCount        = Q4StudentCount;

        this.Q1AllSectionRank = Q1AllSectionRank;
        this.Q2AllSectionRank = Q2AllSectionRank;

        this.Q3AllSectionRank = Q3AllSectionRank;

        this.Q4AllSectionRank = Q4AllSectionRank;

    }

    public Integer getStudentId() {
        return studentId;
    }

    public void setStudentId(Integer studentId) {
        this.studentId = studentId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public Character getGender() {
        return gender;
    }

    public void setGender(Character gender) {
        this.gender = gender;
    }

    public String getAcademicYear() {
        return academicYear;
    }

    public void setAcademicYear(String academicYear) {
        this.academicYear = academicYear;
    }

    public Integer getGrade() {
        return grade;
    }

    public void setGrade(Integer grade) {
        this.grade = grade;
    }

    public String getSection() {
        return section;
    }

    public void setSection(String section) {
        this.section = section;
    }

    public Integer getNumberOfSubjects() {
        return numberOfSubjects;
    }

    public void setNumberOfSubjects(Integer numberOfSubjects) {
        this.numberOfSubjects = numberOfSubjects;
    }

    public double getQuarterOneSum() {
        return quarterOneSum;
    }

    public void setQuarterOneSum(double quarterOneSum) {
        this.quarterOneSum = quarterOneSum;
    }

    public Integer getQuarterOneRank() {
        return quarterOneRank;
    }

    public void setQuarterOneRank(Integer quarterOneRank) {
        this.quarterOneRank = quarterOneRank;
    }

    public double getQuarterTwoSum() {
        return quarterTwoSum;
    }

    public void setQuarterTwoSum(double quarterTwoSum) {
        this.quarterTwoSum = quarterTwoSum;
    }

    public Integer getQuarterTwoRank() {
        return quarterTwoRank;
    }

    public void setQuarterTwoRank(Integer quarterTwoRank) {
        this.quarterTwoRank = quarterTwoRank;
    }

    public Integer getSemesterOneRank() {
        return semesterOneRank;
    }

    public void setSemesterOneRank(Integer semesterOneRank) {
        this.semesterOneRank = semesterOneRank;
    }

    public double getQuarterThreeSum() {
        return quarterThreeSum;
    }

    public void setQuarterThreeSum(double quarterThreeSum) {
        this.quarterThreeSum = quarterThreeSum;
    }

    public Integer getQuarterThreeRank() {
        return quarterThreeRank;
    }

    public void setQuarterThreeRank(Integer quarterThreeRank) {
        this.quarterThreeRank = quarterThreeRank;
    }

    public double getQuarterFourSum() {
        return quarterFourSum;
    }

    public void setQuarterFourSum(double quarterFourSum) {
        this.quarterFourSum = quarterFourSum;
    }

    public Integer getQuarterFourRank() {
        return quarterFourRank;
    }

    public void setQuarterFourRank(Integer quarterFourRank) {
        this.quarterFourRank = quarterFourRank;
    }

    public Integer getSemesterTwoRank() {
        return semesterTwoRank;
    }

    public void setSemesterTwoRank(Integer semesterTwoRank) {
        this.semesterTwoRank = semesterTwoRank;
    }

    public Integer getAllSectionRank() {
        return allSectionRank;
    }

    public void setAllSectionRank(Integer allSectionRank) {
        this.allSectionRank = allSectionRank;
    }

    public Integer getTotalNumberOfStudents() {
        return totalNumberOfStudents;
    }

    public void setTotalNumberOfStudents(Integer totalNumberOfStudents) {
        this.totalNumberOfStudents = totalNumberOfStudents;
    }

    public Integer getAllSectionPercentile() {
        return allSectionPercentile;
    }

    public void setAllSectionPercentile(Integer allSectionPercentile) {
        this.allSectionPercentile = allSectionPercentile;
    }

    public Integer getQ1StudentCount() {
        return Q1StudentCount;
    }

    public void setQ1StudentCount(Integer q1StudentCount) {
        Q1StudentCount = q1StudentCount;
    }

    public Integer getQ2StudentCount() {
        return Q2StudentCount;
    }

    public void setQ2StudentCount(Integer q2StudentCount) {
        Q2StudentCount = q2StudentCount;
    }

    public Integer getQ3StudentCount() {
        return Q3StudentCount;
    }

    public void setQ3StudentCount(Integer q3StudentCount) {
        Q3StudentCount = q3StudentCount;
    }

    public Integer getQ4StudentCount() {
        return Q4StudentCount;
    }

    public void setQ4StudentCount(Integer q4StudentCount) {
        Q4StudentCount = q4StudentCount;
    }

    public Integer getQ1AllSectionRank() {
        return Q1AllSectionRank;
    }

    public void setQ1AllSectionRank(Integer q1AllSectionRank) {
        Q1AllSectionRank = q1AllSectionRank;
    }

    public Integer getQ2AllSectionRank() {
        return Q2AllSectionRank;
    }

    public void setQ2AllSectionRank(Integer q2AllSectionRank) {
        Q2AllSectionRank = q2AllSectionRank;
    }

    public Integer getQ3AllSectionRank() {
        return Q3AllSectionRank;
    }

    public void setQ3AllSectionRank(Integer q3AllSectionRank) {
        Q3AllSectionRank = q3AllSectionRank;
    }

    public Integer getQ4AllSectionRank() {
        return Q4AllSectionRank;
    }

    public void setQ4AllSectionRank(Integer q4AllSectionRank) {
        Q4AllSectionRank = q4AllSectionRank;
    }

    public byte[] getPhoto() {
        return photo;
    }

    public void setPhoto(byte[] photo) {
        this.photo = photo;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public List<StudentRecord> getDetailrows() {
        return detailrows;
    }

    public void setDetailrows(List<StudentRecord> detailrows) {
        this.detailrows = detailrows;
    }

}

