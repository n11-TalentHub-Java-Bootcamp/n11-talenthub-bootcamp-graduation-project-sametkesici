package com.n11project.creditapplication.model;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.persistence.*;
import java.math.BigDecimal;

@Entity(name = "applications")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@SequenceGenerator(name = "seq_application", sequenceName = "seq_application")
public class Application {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE , generator = "seq_application")
    private Long id;

    @Column
    private BigDecimal creditLimit;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private ApplicationStatus applicationStatus;

    @OneToOne()
    private Customer customer;

}
