package com.springbatchdemo.Spring_Batch_Demo.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.springbatchdemo.Spring_Batch_Demo.model.Employee;

@Repository
public interface EmployeeRepo extends JpaRepository<Employee, String> {

}
