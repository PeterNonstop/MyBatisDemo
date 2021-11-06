package com.atguigu.mybatis.test;

import com.atguigu.mybatis.bean.Employee;
import com.atguigu.mybatis.dao.EmployeeMapper;
import com.atguigu.mybatis.dao.EmployeeMapperAnnoation;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;

/**
 * Created with IntelliJ IDEA.
 * To change it use File | Settings | Editor | File and Code Templates.
 *
 *
 * 1. 接口式编程
 *    原生：        Dao       ====>  DaoImp
 *    mybatis:     Mapper    ====>  xxMapper.xml
 *
 * 2. sqlSession代表与数据库的一次会话；用完必须关闭
 * 3. sqlSession和connection一样都是非线程安全, 每次使用都应该去获取新的对象
 * 4. mapper接口没有实现类，但是mybatis会为这个接口生成一个代理对象
 *    （将接口与xml进行绑定）
 * 5. 两个重要的配置文件：
 *    5.1 mybatis的全局配置文件，包含数据库连接池信息。。。(可以没有)
 *    5.2 sql映射文件，保存了每一个sql语句的映射信息。(一定要有)
 *
 * @author Peter
 * @date 2021/11/3 15:04
 * @description TODO
 */
public class MyBatisTest {

    public SqlSessionFactory getSqlSessionFactory() throws IOException {
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);

        return new SqlSessionFactoryBuilder().build(inputStream);
    }

    /**
     * 1. 根据xml配置文件（mybatis-config.xml 全局配置文件）创建一个SqlSessionFactory对象和数据库运行环境信息
     * 2. sql映射文件，配置了每一条sql，以及sql封闭规则等
     * 3. 将sql映射文件注册到全局配置文件中
     * 4. 写代码
     * 4.1 根据全局配置文件得到sqlSessionFactory；
     * 4.2 使用sqlSessionFactory，获取到SqlSession对象进行增删改查；一个SqlSession对象就代表和数据库的一次对话，用完关闭.
     * 4.3 使用sql的唯一标识来告诉Mybatis执行哪个sql,sql都只保存在sql映射文件中。
     *
     * @throws IOException
     */
    @Test
    public void test() throws IOException {
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory =
                new SqlSessionFactoryBuilder().build(inputStream);

        // 2.获取SqlSession实例，能直接执行已经映射的sql语句
        SqlSession openSession = sqlSessionFactory.openSession();

        try {
            Employee employee = openSession.selectOne("com.atguigu.mybatis.EmployeeMapper.selectEmp", 1);
            System.out.println("employee = " + employee);
        } finally {
            openSession.close();
        }
    }

    @Test
    public void test01() throws IOException {
        // 1.获取sqlSessionFactory对象
        SqlSessionFactory sqlSessionFactory = getSqlSessionFactory();

        // 2.获取sqlSession对象
        SqlSession openSession = sqlSessionFactory.openSession();

        try {
            // 3.获取接口的实现类
            // 会为接口自动创建一个代理对象，代理对象去实现增删改查
            EmployeeMapper mapper = openSession.getMapper(EmployeeMapper.class);
            // mapper = class com.sun.proxy.$Proxy6
            System.out.println("mapper = " + mapper.getClass());

            Employee employee = mapper.getEmpById(1);

            System.out.println("employee = " + employee);
        } finally {
            openSession.close();
        }
    }

    @Test
    public void testAnnoation() throws IOException {
        // 1.获取sqlSessionFactory对象
        SqlSessionFactory sqlSessionFactory = getSqlSessionFactory();

        // 2.获取sqlSession对象
        SqlSession openSession = sqlSessionFactory.openSession();

        try {
            // 3.获取接口的实现类
            // 会为接口自动创建一个代理对象，代理对象去实现增删改查
            EmployeeMapperAnnoation mapper = openSession.getMapper(EmployeeMapperAnnoation.class);
            // mapper = class com.sun.proxy.$Proxy6
            System.out.println("mapper = " + mapper.getClass());

            Employee employee = mapper.getEmpById(1);

            System.out.println("employee = " + employee);
        } finally {
            openSession.close();
        }
    }

}
