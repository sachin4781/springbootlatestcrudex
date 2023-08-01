package com.csi.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Employee {

    @Id
    @GeneratedValue

    private int empid;

    @Size(min = 2,message = "Name Should Be Atleast 2 Character..")
    private String empname;

    private String empaddress;

    private double empsalary;

    private long empcontactnumber;

    @Email(message = "Email Id Must Be Valid..")
    @Column(unique = true)
    private String empemailid;

    private String emppassword;


}
