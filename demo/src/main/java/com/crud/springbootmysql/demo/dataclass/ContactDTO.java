package com.crud.springbootmysql.demo.dataclass;

//import javax.validation.constraints.NotEmpty;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

public class ContactDTO {
    private Long id;
    @NotEmpty(message = "Please provide a salutation")
    @Size(min = 2)
    private String salutation;
    @NotEmpty(message = "Please provide a first name")
    @Size(min = 2)
    private String firstName;
    @NotEmpty(message = "Please provide a last name")
    @Size(min = 2)
    private String lastName;
    private String displayName;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date birthDate;
    public String notifyHasBirthdaySoon ;
    @Email
    private String email;
    private String phoneNumber;

    // NoArgsConstructor
//    public ContactDTO() {
//        super();
//    }
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id= id;
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

    public String getnotificationHasBirthdaySoon() {
        return notifyHasBirthdaySoon;
    }
    public void setnotificationHasBirthdaySoon(String notifyHasBirthdaySoon) {
        this.notifyHasBirthdaySoon=notifyHasBirthdaySoon;
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
