package com.n11project.creditapplication.repository;

import com.n11project.creditapplication.model.Application;
import com.n11project.creditapplication.model.Customer;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ApplicationRepository extends JpaRepository<Application, Long> {

  Optional<Application> findByCustomer(Customer customer);
}