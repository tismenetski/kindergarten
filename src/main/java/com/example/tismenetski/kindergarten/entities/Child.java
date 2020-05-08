package com.example.tismenetski.kindergarten.entities;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Child {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String name;

    @NotNull
    private Double age;

    @NotBlank
    private String parent1Name;

    private String parent2Name;

    @NotBlank
    private String parent1Phone;
    private String parent2Phone;

    @NotNull
    private Integer monthlyPayment;

    @ManyToOne
    @JoinColumn(name = "childGroup_id",updatable = false, nullable = false)
    private ChildGroup childGroup;

    @OneToMany(mappedBy = "child",fetch = FetchType.LAZY)
    private List<ChildPayment> childPaymentList;

}
