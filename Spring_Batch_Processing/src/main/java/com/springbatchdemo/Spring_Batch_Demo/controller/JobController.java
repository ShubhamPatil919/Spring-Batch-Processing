package com.springbatchdemo.Spring_Batch_Demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springbatchdemo.Spring_Batch_Demo.runner.JobRunner;

@RestController
@RequestMapping("/run")
public class JobController {
	
	private JobRunner jobRunner;

	@Autowired
	public JobController(JobRunner jobRunner) {
		this.jobRunner = jobRunner;
	}

	@RequestMapping(value = "/job")
	public String runJob() {
		jobRunner.runBatchJob();
		return String.format("Job Demo is done Successfully, args");
	}
}
