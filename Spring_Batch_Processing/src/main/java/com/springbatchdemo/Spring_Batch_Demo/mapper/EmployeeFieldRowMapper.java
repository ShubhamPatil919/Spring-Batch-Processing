package com.springbatchdemo.Spring_Batch_Demo.mapper;

import org.springframework.batch.item.file.mapping.FieldSetMapper;
import org.springframework.batch.item.file.transform.FieldSet;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindException;

import com.springbatchdemo.Spring_Batch_Demo.dto.EmployeeDTO;


public class EmployeeFieldRowMapper implements FieldSetMapper<EmployeeDTO> {

	@Override
	public EmployeeDTO mapFieldSet(FieldSet fieldSet) throws BindException {

		EmployeeDTO employee = new EmployeeDTO();
		employee.setEmployeeId(fieldSet.readString("employeeId"));
		employee.setFirstName(fieldSet.readString("firstName"));
		employee.setLastName(fieldSet.readString("lastName"));
		employee.setEmail(fieldSet.readString("email"));
		try {
			employee.setAge(fieldSet.readInt("age"));
		} catch (Exception ex) {

		}
		return employee;
	}

}
