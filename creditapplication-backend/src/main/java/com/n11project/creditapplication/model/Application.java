package com.n11project.creditapplication.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity(name = "applications")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@SequenceGenerator(name = "seq_application", sequenceName = "seq_application")
public class Application {

  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_application")
  private Long id;

  @Column
  private Double creditLimit;

  @Column(nullable = false)
  @Enumerated(EnumType.STRING)
  private ApplicationStatus applicationStatus;

  @OneToOne
  @JoinColumn(name = "customer_id")
  private Customer customer;
}
