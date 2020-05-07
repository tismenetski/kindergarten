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
public class ChildPayment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @JsonFormat(pattern = "yyyy-mm")
    private Date month;

    @NotBlank
    private Integer amount;

    @ManyToOne
    @JoinColumn(name = "child_id")
    private Child child;
}
