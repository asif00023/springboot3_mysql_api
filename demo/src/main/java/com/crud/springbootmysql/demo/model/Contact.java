package com.crud.springbootmysql.demo.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.util.Date;

import static com.fasterxml.jackson.databind.type.LogicalType.DateTime;
//import javax.validation.constraints.DecimalMin;
//import javax.validation.constraints.NotEmpty;
//import javax.validation.constraints.NotNull;

@Entity
public class Contact {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    public String salutation;
//    @NotEmpty(message = "Please provide a name")
    public String firstName;
    public String lastName;
    public String displayName;
    public Date birthDate;
    public Date creationTimestamp; //= DateTime.Now;
    public Date lastChangeTimestamp =new Date();
    public String email;
    public String phoneNumber;

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    public String getSalutation() {
        return salutation;
    }
    public void setSalutation(String salutation) {
        this.salutation=salutation;
    }

    public String getFirstName() {
        return firstName;
    }
    public void setFirstName(String firstName) {
        this.firstName=firstName;
    }

    public String getLastName() {
        return lastName;
    }
    public void setLastName(String lastName) {
        this.lastName=lastName;
    }

    public String getDisplayName() {
        return displayName;
    }
    public void setDisplayName(String displayName) {
        this.displayName=displayName;
    }

    public Date getBirthDate() {
        return birthDate;
    }
    public void setBirthDate(Date birthDate) {
        this.birthDate=birthDate;
    }

    public Date getCreationTimestamp() {
        return creationTimestamp;
    }
    public void setCreationTimestamp(Date creationTimestamp) {
        this.creationTimestamp=creationTimestamp;
    }

    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email=email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber=phoneNumber;
    }
}
