package com.atguigu.mybatis.controller;

import com.atguigu.mybatis.bean.Employee;
import com.atguigu.mybatis.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * To change it use File | Settings | Editor | File and Code Templates.
 *
 * @author Peter
 * @date 2021/11/6 18:15
 * @description TODO
 */
@Controller
public class EmployeeController {

    @Autowired
    EmployeeService employeeService;

    @Autowired
    private ApplicationContext applicationContext;

    @RequestMapping("/getEmps")
    public String getEmployee(Map<String, Object> map) {
        System.out.println("getEmployee========");
        System.out.println(Arrays.asList(applicationContext.getBeanDefinitionNames()));

        //Employee emp = employeeService.getEmpById(1);
        //System.out.println("emp = " + emp);

        List<Employee> emps = employeeService.getAllEmp();

        System.out.println("emps = " + emps);

        map.put("allEmps", emps);

        return "list";
    }

    @RequestMapping("/addEmp")
    @ResponseBody
    public String addEmployee(Map<String, Object> map) {

        Employee employee = new Employee(null, "xiao", "xiao@test.com", "1");

        boolean addEmp = employeeService.addEmp(employee);

        System.out.println("employee.getId() = " + employee.getId());

        if (addEmp) {
            return "add success";
        } else {
            return "Failed!!!";
        }
    }

    @RequestMapping("/addBatchEmp")
    @ResponseBody
    public String addBatchEmployee(Map<String, Object> map) {

        employeeService.addBatchEmp();

        return "add success";

    }
}
