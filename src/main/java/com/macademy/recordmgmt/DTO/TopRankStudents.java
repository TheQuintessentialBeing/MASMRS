package com.macademy.recordmgmt.DTO;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter

public class TopRankStudents {
    private String firstName;
    private String lastName;
    private String academicYear;
    private String grade;
    private String section;
    private String subject;
    private Integer rank;
    private double average;


}

