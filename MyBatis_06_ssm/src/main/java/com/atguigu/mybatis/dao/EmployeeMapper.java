package com.atguigu.mybatis.dao;

import com.atguigu.mybatis.bean.Employee;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * To change it use File | Settings | Editor | File and Code Templates.
 *
 * @author Peter
 * @date 2021/11/3 15:47
 * @description TODO
 */
@Component
public interface EmployeeMapper {

    public Employee getEmpById(Integer id);

    public List<Employee> getEmployee();

    public boolean addEmpoyee(Employee employee);
}
