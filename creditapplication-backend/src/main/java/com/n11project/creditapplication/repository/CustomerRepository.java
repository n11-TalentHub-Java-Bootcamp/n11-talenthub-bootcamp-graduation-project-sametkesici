package com.n11project.creditapplication.repository;

import com.n11project.creditapplication.model.Customer;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.Optional;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {

    Optional<Customer> findByIdentificationNumber(String identificationNumber);

    Optional<Customer> findByIdentificationNumberAndBirthDate(String identificationNumber , Date birthDate);
}
