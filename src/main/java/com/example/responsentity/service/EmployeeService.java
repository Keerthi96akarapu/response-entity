package com.example.responsentity.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.responsentity.entity.Employee;
import com.example.responsentity.repository.EmployeeRepository;

@Service
public class EmployeeService {
	@Autowired
	private EmployeeRepository employeeRepository;
	
	public Employee saveEmployee(Employee employee) {
		return employeeRepository.save(employee);
	}
	
	public List<Employee> saveEmployees(List<Employee> employees){
		return employeeRepository.saveAll(employees);
	}
    public List<Employee> getEmployees() {
		
		return employeeRepository.findAll();
	}
	public Employee getEmployeeById(int Id) {
		
		return employeeRepository.findById(Id).orElse(null);
	}
	public Employee updateEmployee(Employee employee,int id) {
		Employee emp = employeeRepository.findById(id).get();
		emp.setName(emp.getName());
		return employeeRepository.save(employee);

}
}
