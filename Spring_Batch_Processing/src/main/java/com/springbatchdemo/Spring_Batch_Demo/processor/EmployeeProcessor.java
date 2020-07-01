package com.springbatchdemo.Spring_Batch_Demo.processor;

import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

import com.springbatchdemo.Spring_Batch_Demo.dto.EmployeeDTO;
import com.springbatchdemo.Spring_Batch_Demo.model.Employee;

@Component
public class EmployeeProcessor implements ItemProcessor<EmployeeDTO, Employee> {

	@Override
	public Employee process(EmployeeDTO dto) throws Exception {
		Employee employee = new Employee();
		employee.setEmployeeId(dto.getEmployeeId());
		employee.setFirstName(dto.getFirstName().toUpperCase());
		employee.setLastName(dto.getLastName().toUpperCase());
		employee.setEmail(dto.getEmail());
		employee.setAge(dto.getAge());
		System.out.println("Inside Processor : " + employee.toString());
		return employee;
	}

}
