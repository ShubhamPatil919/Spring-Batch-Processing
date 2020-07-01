package com.springbatchdemo.Spring_Batch_Demo.job;

import javax.sql.DataSource;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.database.BeanPropertyItemSqlParameterSourceProvider;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import com.springbatchdemo.Spring_Batch_Demo.dto.EmployeeDTO;
import com.springbatchdemo.Spring_Batch_Demo.mapper.EmployeeFieldRowMapper;
import com.springbatchdemo.Spring_Batch_Demo.model.Employee;
import com.springbatchdemo.Spring_Batch_Demo.processor.EmployeeProcessor;

@Configuration
public class Demo1 {

	private JobBuilderFactory jobBuilderFactory;
	private StepBuilderFactory stepBuilderFactory;
	private EmployeeProcessor employeeProcessor;
	private DataSource dataSource;

	@Autowired
	public Demo1(JobBuilderFactory jobBuilderFactory, StepBuilderFactory stepBuilderFactory, DataSource dataSource) {

		this.jobBuilderFactory = jobBuilderFactory;
		this.stepBuilderFactory = stepBuilderFactory;
		this.dataSource = dataSource;
	}

	@Qualifier(value = "demo1")
	@Bean
	public Job demo1job() {
		return this.jobBuilderFactory.get("demo1").start(step1Demo1()).build();
	}

	private Step step1Demo1() {
		return this.stepBuilderFactory.get("step1").<EmployeeDTO, Employee>chunk(5).reader(employeeReader())
				.processor(employeeProcessor).writer(employeeDbWriter()).build();
	}

	@Bean
	@StepScope
	Resource inputFileResource(@Value("#{jobParameter[fileName]}") final String fileName) {
		return new ClassPathResource(fileName);
	}

	@Bean
	@StepScope
	public FlatFileItemReader<EmployeeDTO> employeeReader() {
		FlatFileItemReader<EmployeeDTO> reader = new FlatFileItemReader<>();
		reader.setResource(inputFileResource(null));
		reader.setLineMapper(new DefaultLineMapper<EmployeeDTO>() {
			{
				setLineTokenizer(new DelimitedLineTokenizer() {
					{
						setNames("employeeId", "firstName", "lastName", "email", "age");
						setDelimiter(",");
					}
				});
				setFieldSetMapper(new EmployeeFieldRowMapper());
			}
		});
		return reader;
	}

	@Bean
	public JdbcBatchItemWriter<Employee> employeeDbWriter() {
		JdbcBatchItemWriter<Employee> itemWriter = new JdbcBatchItemWriter<>();
		itemWriter.setDataSource(dataSource);
		itemWriter.setSql(
				"insert into employee(employee_Id,employee_FirstName,employee_LastName,employee_Email,employee_Age) values(:employeeId,:firstName,:lastName,:email,:age)");
		itemWriter.setItemSqlParameterSourceProvider(new BeanPropertyItemSqlParameterSourceProvider<Employee>());
		return itemWriter;

	}

}
