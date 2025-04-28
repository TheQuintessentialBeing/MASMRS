package com.macademy.recordmgmt.models;

import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity // make it JPA entity
@Table(name = "Students") // change to any name in database from here
@FieldDefaults(level = AccessLevel.PRIVATE) //make all fields access specifier private
// @SequenceGenerator(name = "studentNumber_Seq", initialValue = 100, allocationSize = 1)
@Getter
@Setter
public class Student {
    @Id
//    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "studentNumber_Seq")
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "studentNumber_Seq")

    @Setter(AccessLevel.PRIVATE)
    Integer studentId;
    @Column(nullable = false, length = 50)
    String firstName;
    @Column(nullable = false, length = 50)
    String middleName;
    @Column(nullable = false, length = 50)
    String lastName;
    @Column(nullable = true)
    LocalDate dateOfBirth;
    @Column(nullable = false, length = 1)
    String gender;
    @Column(nullable = false)
    LocalDate registrationDate;

    @Column(name = "photo")
    String photo;
    @Column(nullable = false, length = 50)
    String kifleKetema;
    @Column(columnDefinition = "varchar(50) default NULL")
    String kebele;
    @Column(nullable = false, length = 50)
    String houseNumber;
    @Column(nullable = false, length = 50)
    String phone;
    @Column(length = 150)
    String comment;
    @Column(nullable = false)
    boolean isActive;

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

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public LocalDate getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(LocalDate registrationDate) {
        this.registrationDate = registrationDate;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getKifleKetema() {
        return kifleKetema;
    }

    public void setKifleKetema(String kifleKetema) {
        this.kifleKetema = kifleKetema;
    }

    public String getKebele() {
        return kebele;
    }

    public void setKebele(String kebele) {
        this.kebele = kebele;
    }

    public String getHouseNumber() {
        return houseNumber;
    }

    public void setHouseNumber(String houseNumber) {
        this.houseNumber = houseNumber;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public boolean getIsActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }
}
