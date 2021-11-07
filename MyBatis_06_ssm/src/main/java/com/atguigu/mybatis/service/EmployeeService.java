package com.atguigu.mybatis.service;

import com.atguigu.mybatis.bean.Employee;
import com.atguigu.mybatis.dao.EmployeeMapper;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

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

    @Autowired
    private SqlSession sqlSession;

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

    public void addBatchEmp() {
        EmployeeMapper mapper = sqlSession.getMapper(EmployeeMapper.class);

        for (int i = 0; i < 1000; i++) {
            String substring = UUID.randomUUID().toString().substring(0, 6);
            mapper.addEmpoyee(new Employee(null, substring, substring + "@qq.com", "1"));
        }
    }
}
