package com.atguigu.mybatis.service;

import com.atguigu.mybatis.bean.Employee;
import com.atguigu.mybatis.dao.EmployeeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * To change it use File | Settings | Editor | File and Code Templates.
 *
 * @author Peter
 * @date 2021/11/6 18:19
 * @description TODO
 */
@Service
public class EmployeeService {

    @Autowired
    private EmployeeMapper employeeMapper;

    public List<Employee> getAllEmp() {
        System.out.println("employeeMapper = " + employeeMapper);

        return employeeMapper.getEmployee();
    }

    public Employee getEmpById(Integer id) {
        return employeeMapper.getEmpById(id);
    }


    public boolean addEmp(Employee employee) {
        return employeeMapper.addEmpoyee(employee);
    }
}
