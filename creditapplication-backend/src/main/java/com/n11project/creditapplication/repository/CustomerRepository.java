package com.n11project.creditapplication.repository;

import com.n11project.creditapplication.model.Customer;
import java.util.Date;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {

  Optional<Customer> findByIdentificationNumber(String identificationNumber);

  Optional<Customer> findByIdentificationNumberAndBirthDate(String identificationNumber, Date birthDate);
}