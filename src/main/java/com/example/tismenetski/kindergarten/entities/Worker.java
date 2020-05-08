package com.example.tismenetski.kindergarten.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Worker {
    private static final String MY_TIME_ZONE="Asia/Jerusalem";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String name;

    @NotNull
    private Integer age;

    @JsonFormat(pattern = "yyyy-MM-dd",timezone = MY_TIME_ZONE)
    @NotNull
    private Date birthday;

    @NotBlank
    private String sex;

    @NotNull
    private Double hourlyPayment;

    @OneToMany(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY, mappedBy = "worker")
    private List<Shift> shifts;

    @OneToMany(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY, mappedBy = "salaryWorker")
    private List<Salary> salaries;


    private Boolean isActive; // Needed for filtering only active workers for job handling and all workers for past records like salaries and expenses
}
