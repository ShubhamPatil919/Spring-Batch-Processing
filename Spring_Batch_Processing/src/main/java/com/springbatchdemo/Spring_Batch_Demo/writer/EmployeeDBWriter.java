package com.springbatchdemo.Spring_Batch_Demo.writer;

import java.util.List;

import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.springbatchdemo.Spring_Batch_Demo.model.Employee;
import com.springbatchdemo.Spring_Batch_Demo.repo.EmployeeRepo;

@Component
public class EmployeeDBWriter implements ItemWriter<Employee> {

	@Autowired
	private EmployeeRepo employeeRepo;

	@Override
	public void write(List<? extends Employee> employees) throws Exception {
		employeeRepo.saveAll(employees);
		System.out.println("Inside Writer " + employees);
	}
}
