package com.example.multidc.repository.employee;

import com.example.multidc.entity.employee.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<Address, Long> {
}
