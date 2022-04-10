import com.ydlclass.entity.Dept;
import com.ydlclass.entity.Employee;
import com.ydlclass.mapper.DeptMapper;
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
public class TestDept {
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
    public void testselectall() {
        try (SqlSession session = sqlSessionFactory.openSession()) {


            DeptMapper mapper = session.getMapper(DeptMapper.class);
            //获得一个代理对象 jdk.proxy 类,  代理对象实现了UserMapper的接口
            //mapper 和xml
            List<Dept> depts = mapper.selectAll(null);
            log.debug("users is [{}]",depts);
        }
    }

    @Test
    public void testselect2() {
        try (SqlSession session = sqlSessionFactory.openSession()) {


            DeptMapper mapper = session.getMapper(DeptMapper.class);
            //获得一个代理对象 jdk.proxy 类,  代理对象实现了UserMapper的接口
            //mapper 和xml
            List<Dept> depts = mapper.select2(null);
            log.debug("users is [{}]",depts);
        }
    }


}



