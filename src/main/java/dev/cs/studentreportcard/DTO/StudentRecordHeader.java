package dev.cs.studentreportcard.DTO;
import com.fasterxml.jackson.annotation.JsonInclude;
import dev.cs.studentreportcard.models.StudentRecord;
import lombok.*;
import lombok.experimental.FieldDefaults;


import java.util.Date;
import java.util.List;
@FieldDefaults(level = AccessLevel.PRIVATE)
@JsonInclude(JsonInclude.Include.NON_NULL)  // Ignore null value
public class StudentRecordHeader {
    private Integer studentId;
    private String firstName;
    private String middleName;
    private String lastName;
    private Date dateOfBirth;

   //TODO restrinct to one character
    private Character gender;

    private String academicYear;
    private Integer grade;

    // TODO this could be changed to like gender to Character
    private String section;

    // Calculated fields
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

    private Integer Q1TotalStudents;
    private Integer Q2TotalStudents;
    private Integer Q3TotalStudents;
    private Integer Q4TotalStudents;


    private List<StudentRecord> detailrows ;
    public StudentRecordHeader() {

    }
    public StudentRecordHeader(Integer studentId, String firstName, String middleName, String lastName, Date dateOfBirth, Character gender, String academicYear, Integer grade, String section, Integer numberOfSubjects, double quarterOneSum, Integer quarterOneRank, double quarterTwoSum, Integer quarterTwoRank, Integer semesterOneRank, double quarterThreeSum, Integer quarterThreeRank, double quarterFourSum, Integer quarterFourRank, Integer semesterTwoRank, Integer allSectionRank, Integer totalNumberOfStudents, Integer allSectionPercentile, byte[] photo, boolean isActive, Integer Q1TotalStudents, Integer Q2TotalStudents ,Integer Q3TotalStudents, Integer Q4TotalStudents ) {
        this.studentId = studentId;
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
        this.gender = gender;
        this.academicYear = academicYear;
        this.grade = grade;
        this.section = section;
        this.numberOfSubjects = numberOfSubjects;
        this.quarterOneSum = quarterOneSum;
        this.quarterOneRank = quarterOneRank;
        this.quarterTwoSum = quarterTwoSum;
        this.quarterTwoRank = quarterTwoRank;
        this.semesterOneRank = semesterOneRank;
        this.quarterThreeSum = quarterThreeSum;
        this.quarterThreeRank = quarterThreeRank;
        this.quarterFourSum = quarterFourSum;
        this.quarterFourRank = quarterFourRank;
        this.semesterTwoRank = semesterTwoRank;
        this.allSectionRank = allSectionRank;
        this.totalNumberOfStudents = totalNumberOfStudents;
        this.allSectionPercentile = allSectionPercentile;
        this.photo = photo;
        this.isActive = isActive;
        this.Q1TotalStudents = Q1TotalStudents;
        this.Q2TotalStudents = Q2TotalStudents;
        this.Q3TotalStudents = Q3TotalStudents;
        this.Q4TotalStudents = Q4TotalStudents;
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

