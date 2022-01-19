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

    @Column
    private String name;

    @Column
    private String lastName;

    @Column(unique = true)
    private String identificationNumber;

    @Column
    private BigDecimal monthlyIncome;

    @Column(unique = true)
    private String phoneNumber;

    @Column
    private Date birthDate;

    @Column
    private BigDecimal assurance;

}
