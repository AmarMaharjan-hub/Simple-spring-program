package com.javaspringproject.springboot.student;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
//import org.hibernate.annotations.Entity;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.Period;

@Entity
@Data
@NoArgsConstructor
public class Student {
    @Id
    @GeneratedValue(
            strategy = GenerationType.AUTO,
            generator = "student_sequence"
    )
    @SequenceGenerator(
            name = "student_sequence",
            sequenceName = "student_sequence",
            allocationSize = 1
    )

    @Column
    private Long id;
    @Column

    private String name;
    @Transient
    private Integer age;
    @Column
    private LocalDate dob;
    @Column
    private String email;

    public Student(String name,
                   LocalDate dob,
                   String email) {
        this.name = name;
        this.dob = dob;
        this.email = email;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return Period.between(this.dob, LocalDate.now()).getYears();
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public LocalDate getDob() {
        return dob;
    }

    public void setDob(LocalDate dob) {
        this.dob = dob;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", dob=" + dob +
                ", email='" + email + '\'' +
                '}';
    }
}
