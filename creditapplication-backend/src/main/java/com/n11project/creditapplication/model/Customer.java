package com.n11project.creditapplication.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import java.util.Date;

@Entity(name = "customers")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@SequenceGenerator(name = "seq_customer", sequenceName = "seq_customer")
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE , generator = "seq_customer")
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String lastName;

    @Column(unique = true , nullable = false)
    private String identificationNumber;

    @Column(nullable = false)
    private Double monthlyIncome;

    @Column(unique = true , nullable = false)
    private String phoneNumber;

    @Column(nullable = false)
    @Temporal(TemporalType.DATE)
    private Date birthDate;

    @Column
    private Double assurance;

    @Column()
    private Integer creditScore;

    @OneToOne(cascade = CascadeType.ALL , mappedBy = "customer")
    @JsonIgnore
    private Application application;


}
