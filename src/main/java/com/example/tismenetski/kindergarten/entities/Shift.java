package com.example.tismenetski.kindergarten.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Value;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.Date;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Shift {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String name;

    @NotBlank
    @JsonFormat(pattern ="hh:mm")
    private Date startDate;


    @NotBlank
    @JsonFormat(pattern ="hh:mm")
    private Date endDate;

    @ManyToOne
    @JoinColumn(name = "worker_id")
    private Worker worker;

    @NotBlank
    @Value(value = "1") //Needs To be tested
    private Double shiftRate;

    //Consider placing a relationship between childGroup and shifts to place shifts in childGroups

}
