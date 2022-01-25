package com.n11project.creditapplication.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
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
    private BigDecimal monthlyIncome;

    @Column(unique = true , nullable = false)
    private String phoneNumber;

    @Column(nullable = false)
    private Date birthDate;

    @Column
    private BigDecimal assurance;

    @Column(nullable = false)
    private Integer creditScore;


}
