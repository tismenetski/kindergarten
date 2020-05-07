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
public class Activity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank
    private String name;

    @NotBlank
    @JsonFormat(pattern = "yyyy-mm-dd hh:mm")
    private Date startDate;

    @NotBlank
    @JsonFormat(pattern = "yyyy-mm-dd hh:mm")
    private Date endDate;

    @ManyToOne
    @JoinColumn(name = "activityPerson_id",updatable = false, nullable = false)
    private ActivityPerson activityPerson;

}
