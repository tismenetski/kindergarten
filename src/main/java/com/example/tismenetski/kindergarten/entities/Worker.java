package com.example.tismenetski.kindergarten.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.Date;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Worker {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String name;

    @NotBlank
    private Integer age;

    @JsonFormat(pattern = "yyyy-MM-dd")
    @NotBlank
    private Date birthday;

    @NotBlank
    private String sex;

    @NotBlank
    private Double hourlyPayment;

    @OneToMany(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY, mappedBy = "worker")
    private List<Shift> shifts;

    @OneToMany(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY, mappedBy = "salaryWorker")
    private List<Salary> salaries;
}
