
package com.example.responsentity.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.responsentity.entity.Employee;
import com.example.responsentity.entity.ResponseObject;
import com.example.responsentity.repository.EmployeeRepository;
import com.example.responsentity.service.EmployeeService;

@RestController
@RequestMapping("/employees")
public class EmployeeController {
	@Autowired
	private EmployeeService employeeService;
	
	@Autowired
	private EmployeeRepository employeeRepository;
		
	@PostMapping("/addemployee")
	public ResponseEntity<ResponseObject> addEmployee(@RequestBody Employee employee)
	{
		ResponseObject resObj = new ResponseObject();	
		 employeeService.saveEmployee(employee);
		 resObj.setStatusCode("201");
		 resObj.setUrl("employees/addemployee");
		 resObj.setResponse(employee);
		 return new ResponseEntity<>(resObj, HttpStatus.CREATED);
	}
	@GetMapping("/getemployees")
	public List<Employee> findAllEmployees()
	{
		return employeeService.getEmployees();
	}
	@GetMapping("/EmployeeById/{id}")
	public Employee findEmployeeById(@PathVariable int id)
	{
			return employeeService.getEmployeeById(id);
	}
	@PutMapping("/update/{id}")
	public  ResponseEntity<ResponseObject> updateEmployee(@PathVariable("id") int id, @RequestBody Employee employee )
	{
			if(employee.getId()!= 0)
			{
				try {
					employeeService.updateEmployee(employee, id);
					return new ResponseEntity<>(HttpStatus.ACCEPTED);
					}
				catch(Exception e)
				{
					return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
				}
				
			}
			return new ResponseEntity<>(HttpStatus.CONTINUE);		
	}
	@DeleteMapping("/delete")
    public String deleteBook(@RequestBody Employee employee) {
        employeeRepository.delete(employee);
        return "Deleted employee:"+employee;
    }

}
