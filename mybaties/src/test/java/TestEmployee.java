import com.ydlclass.entity.Employee;
import com.ydlclass.mapper.EmployeeMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@Slf4j
public class TestEmployee {
    SqlSessionFactory sqlSessionFactory = null;


    @Before
    public void before() throws IOException {
        String resource = "mybatis-config.xml";
        InputStream inputStream = null;
        inputStream = Resources.getResourceAsStream(resource);
        // 1、创建一个SqlSessionFactory的 建造者 ，用于创建SqlSessionFactory
        // SqlSessionFactoryBuilder中有大量的重载的build方法，可以根据不同的入参，进行构建
        SqlSessionFactoryBuilder sqlSessionFactoryBuilder = new SqlSessionFactoryBuilder();
        sqlSessionFactory = sqlSessionFactoryBuilder.build(inputStream);
    }

    @Test
    public void testSqlSession() {
        log.debug("is [{}]", sqlSessionFactory);
        try (SqlSession session = sqlSessionFactory.openSession()) {
            List<Object> objects = session.selectList("user.select");
            log.debug("result is{}", objects);

        }
    }

    @Test
    public void testselect() {
        try (SqlSession session = sqlSessionFactory.openSession()) {


            EmployeeMapper mapper = session.getMapper(EmployeeMapper.class);
            //获得一个代理对象 jdk.proxy 类,  代理对象实现了UserMapper的接口
            //mapper 和xml
            List<Employee> employees = mapper.select(new Employee());
            for (Employee employee : employees) {
//                懒加载
                log.debug(employee.getDept().getName());
            }
//            log.debug("users is [{}]", employees);
        }
    }
    @Test
    public void testselect2() {
        try (SqlSession session = sqlSessionFactory.openSession()) {


            EmployeeMapper mapper = session.getMapper(EmployeeMapper.class);
            //获得一个代理对象 jdk.proxy 类,  代理对象实现了UserMapper的接口
            //mapper 和xml
            List<Employee> employees = mapper.select2(new Employee());
            log.debug("users is [{}]", employees);
        }
    }

}



