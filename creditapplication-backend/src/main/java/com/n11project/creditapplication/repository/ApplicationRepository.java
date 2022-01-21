package com.n11project.creditapplication.repository;

import com.n11project.creditapplication.model.Application;
import com.n11project.creditapplication.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ApplicationRepository extends JpaRepository<Application, Long> {

    Optional<Application> findByCustomer(Customer customer);

}
