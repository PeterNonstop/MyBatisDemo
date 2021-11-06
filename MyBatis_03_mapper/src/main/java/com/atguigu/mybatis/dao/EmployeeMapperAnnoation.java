package com.atguigu.mybatis.dao;

import com.atguigu.mybatis.bean.Employee;
import org.apache.ibatis.annotations.Select;

/**
 * Created with IntelliJ IDEA.
 * To change it use File | Settings | Editor | File and Code Templates.
 *
 * @author Peter
 * @date 2021/11/4 0:24
 * @description TODO
 */
public interface EmployeeMapperAnnoation {

    @Select("select id, last_name, email, gender from tbl_employee where id = #{id}")
    public Employee getEmpById(Integer id);
}
