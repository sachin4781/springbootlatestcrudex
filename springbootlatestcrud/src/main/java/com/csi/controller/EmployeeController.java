package com.csi.controller;

import com.csi.exception.RecordNotFoundException;
import com.csi.model.Employee;
import com.csi.service.EmployeeServiceImpl;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/Employees")
public class EmployeeController {

    @Autowired
    EmployeeServiceImpl employeeServiceImpl;


    @PostMapping("/savedata")

    public ResponseEntity<Employee> savedata(@Valid @RequestBody Employee employee) {
        return new ResponseEntity<>(employeeServiceImpl.savedata(employee), HttpStatus.CREATED);
    }

    @GetMapping("/getdatabyid/{empid}")

    public ResponseEntity<Optional<Employee>> getdatabyid(@PathVariable int empid) {
        return ResponseEntity.ok(employeeServiceImpl.getdatabyid(empid));
    }

    @GetMapping("/getalldata")

    public ResponseEntity<List<Employee>> getalldata() {
        return ResponseEntity.ok(employeeServiceImpl.getalldata());
    }

    @PutMapping("/updatedata/{empid}")

    public ResponseEntity<Employee> updatedata(@Valid @RequestBody Employee employee, @PathVariable int empid) {

        Employee employee1 = employeeServiceImpl.getdatabyid(empid).orElseThrow(() -> new RecordNotFoundException("Employee Id Does Not Exist.."));

        employee1.setEmppassword(employee.getEmppassword());
        employee1.setEmpid(employee.getEmpid());
        employee1.setEmpaddress(employee.getEmpaddress());
        employee1.setEmpemailid(employee.getEmpemailid());
        employee1.setEmpname(employee.getEmpname());
        employee1.setEmpsalary(employee.getEmpsalary());

        return new ResponseEntity<>(employeeServiceImpl.updatedata(employee1), HttpStatus.CREATED);
    }


    @PatchMapping("/address/{empid}/{empaddress}")

    public ResponseEntity<Employee> addresschange(@PathVariable int empid, @PathVariable String empaddress) {

        Employee employee = employeeServiceImpl.getdatabyid(empid).orElseThrow(() -> new RecordNotFoundException("Employee Id Does Not Exist.."));

        employee.setEmpaddress(empaddress);

        return ResponseEntity.ok(employeeServiceImpl.patchdata(employee));
    }

    @DeleteMapping("/deletedatabyid/{empid}")

    public ResponseEntity<String> deletedatabyid(@PathVariable int empid) {
        employeeServiceImpl.deletedatabyid(empid);

        return ResponseEntity.ok("Data Deleted SuccessFully..");
    }


}
