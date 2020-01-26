package com.example.kafka.springbootkafkaproducerexample.resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.kafka.springbootkafkaproducerexample.Model.Employee;

@RestController
@RequestMapping("kafka")
public class EmployeeResource {

	@Autowired
	private KafkaTemplate<String, String> kafkaTemplate;
	

	@Autowired
	private KafkaTemplate<String, Employee> kafkaTemplateEmployee;
	
	private final String TOPIC = "Kafka_Example";	
	
	@GetMapping("/publish/{message}")
	public String post(@PathVariable("message") final String message) {
		
		kafkaTemplate.send(TOPIC,message);
		
		return "Publish message successfully";
	}
	
	@GetMapping("/publish/employee/{name}/{salary}")
	public String post(@PathVariable("name") final String name, @PathVariable("salary") final String salary) {
		
		kafkaTemplateEmployee.send(TOPIC,new Employee(name,salary));
		
		return "Publish message successfully";
	}	
}
