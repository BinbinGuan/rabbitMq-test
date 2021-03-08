package com.example.elssticsearch.controller;

import com.example.elssticsearch.dao.EmployeeRepository;
import com.example.elssticsearch.entity.Employee;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

/**
 * @author: GuanBin
 * @date: Created in 下午3:44 2021/3/8
 */
@Slf4j
@RestController
@RequestMapping("es")
public class EmployeeController {

    @Autowired
    private EmployeeRepository employeeRepository;

    @RequestMapping(path = "/add",method = RequestMethod.GET)
    public String add() {
        Employee employee = new Employee();
        employee.setId("1");
        employee.setFirstName("binbin");
        employee.setLastName("guan");
        employee.setAge(29);
        employee.setAbout("i am binbin.guan");
        log.info("新增成功");
        employeeRepository.save(employee);
        return "success";
    }

    @RequestMapping(path = "/findById", method = RequestMethod.GET)
    public Employee query(@RequestParam(value = "id") String id){
        Optional<Employee> employeeOptional = employeeRepository.findById(id);
        if(employeeOptional.isPresent()){
            log.info("查询成功");
            return employeeOptional.get();
        }
        return null;
    }

    @RequestMapping(path = "/update", method = RequestMethod.GET)
    public Employee update(@RequestParam(value = "id") String id){
        Optional<Employee> employeeOptional = employeeRepository.findById(id);
        Employee employee=null;
        if(employeeOptional.isPresent()){
            employee = employeeOptional.get();
            employee.setFirstName("tomcat");
            employeeRepository.save(employee);
            log.info("更新成功");
        }
        return employee;
    }


    @RequestMapping(path = "/delete", method = RequestMethod.DELETE)
    public String delete(@RequestParam(value = "id") String id){
        Optional<Employee> employeeOptional = employeeRepository.findById(id);
        employeeRepository.delete(employeeOptional.get());
        return "success";
    }
}
