package com.n11project.creditapplication.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

@Entity(name = "customers")
@Getter
@Setter
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

    private BigDecimal monthlyIncome;

    @Column(unique = true , nullable = false)
    private String phoneNumber;

    @Column
    @Temporal(TemporalType.DATE)
    private Date birthDate;

    @Column
    private BigDecimal assurance;

    @Column
    private Integer creditScore;


}
