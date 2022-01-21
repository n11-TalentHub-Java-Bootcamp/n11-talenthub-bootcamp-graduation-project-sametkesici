package com.n11project.creditapplication.model;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity(name = "applications")
@Getter
@Setter
@SequenceGenerator(name = "seq_application", sequenceName = "seq_application")
public class Application {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE , generator = "seq_application")
    private Long id;

    @Column
    private BigDecimal creditLimit;

    @Column
    @Enumerated(EnumType.STRING)
    private ApplicationStatus applicationStatus;

    @ManyToOne(fetch = FetchType.LAZY)
    private Customer customer;

}
