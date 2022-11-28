package com.nighthawk.spring_portfolio.mvc.person;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;
import org.springframework.format.annotation.DateTimeFormat;

import com.vladmihalcea.hibernate.type.json.JsonType;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

/*
Person is a POJO, Plain Old Java Object.
First set of annotations add functionality to POJO
--- @Setter @Getter @ToString @NoArgsConstructor @RequiredArgsConstructor
The last annotation connect to database
--- @Entity
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@TypeDef(name="json", typeClass = JsonType.class)
public class Person {
    
    // automatic unique identifier for Person record
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    // email, password, roles are key attributes to login and authentication
    @NotEmpty
    @Size(min=5)
    @Column(unique=true)
    @Email
    private String email;

    @NotEmpty
    private String password;

    // @NonNull, etc placed in params of constructor: "@NonNull @Size(min = 2, max = 30, message = "Name (2 to 30 chars)") String name"
    @NonNull
    @Size(min = 2, max = 30, message = "Name (2 to 30 chars)")
    private String name;

    @DateTimeFormat(pattern = "yyyy-mm-dd")
    private Date dob;

    @NonNull
    @Size(min = 2, max = 30, message = "Gender (Male, Female, Other)")
    private String gender;
    
    @Column(unique=false)
    private int height;

    @Column(unique=false)
    private int weight;

    /* HashMap is used to store JSON for daily "stats"
    "stats": {
        "2022-11-13": {
            "calories": 2200,
            "steps": 8000
        }
    }
    */
    @Type(type="json")
    @Column(columnDefinition = "jsonb")
    private Map<String,Map<String, Object>> stats = new HashMap<>(); 
    

    // Constructor used when building object from an API
    public Person(String email, String password, String name, Date dob, String gender, int height, int weight) {
        this.email = email;
        this.password = password;
        this.name = name;
        this.dob = dob;
        this.gender = gender;
        this.height = height;
        this.weight = weight;
    }

    public String toString(){
        return ("{ \"email\": " + this.email + ", " + "\"password\": " + this.password + ", " + "\"name\": " + this.name + ", " + "\"dob\": " + this.dob + ", " + "\"gender\": " + this.gender + ", " + "\"height\": " + this.height + ", " + "\"weight\": " + this.weight + " }" );
    }


    public int getAge() { //getAge with the age creator 
        if (this.dob != null) {
            LocalDate birthDay = this.dob.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
            return Period.between(birthDay, LocalDate.now()).getYears(); }
        return -1;
    }

    public String getAgeToString(){ //turns it into a string 
        return ("{ \"name\": " + this.name + " ," + "\"age\": " + this.getAge() + " }" );
    }


    public static void main(String[] args) throws ParseException{
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-mm-dd");
        Date myDate = sdf.parse("2006-05-23");

        Person person = new Person("saathvika.ajith@gmail.com", "pwd123", "Saathvika Ajith", myDate, "Female", 61, 121 );
        Person noArgsPerson = new Person();
        System.out.println(noArgsPerson);
        System.out.println(person.toString());
        System.out.println(person.getAgeToString());
    }

}