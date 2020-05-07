package com.example.tismenetski.kindergarten.entities;


import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.Date;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Salary {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @JsonFormat(pattern = "yyyy-mm")
    private Date month;

    @ManyToOne
    @JoinColumn(name = "worker_id",updatable = false, nullable = false)
    private Worker salaryWorker;

    @NotBlank
    private Double amount;

}
