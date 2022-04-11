
import com.github.pagehelper.PageHelper;
import com.ydlclass.mapper.UserMapper;
import com.ydlclass.entity.User;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Before;
import org.junit.Test;


import java.io.*;
import java.util.List;

@Slf4j
public class TestMybatis {
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
    public void testMapper() {
        try (SqlSession session = sqlSessionFactory.openSession()) {

            //
            UserMapper mapper = session.getMapper(UserMapper.class);
//            PageHelper.startPage(10,10);
            PageHelper.startPage(2, 5);


            //获得一个代理对象 jdk.proxy 类,  代理对象实现gbhujioiugfxcfvgbhyjuioigfvcx 了UserMappe的接口
            //mapper 和xml
            List<User> users = mapper.selectAll();
            log.debug("users is [{}]", users);
        }
    }

    @Test
    public void testid() {
        try (SqlSession session = sqlSessionFactory.openSession()) {
            UserMapper mapper = session.getMapper(UserMapper.class);
            //获得一个代理对象 jdk.proxy 类,  代理对象实现了UserMappe的接口
            //mapper 和xml
            User user = mapper.selectOne(1);
            log.debug("users is [{}]", user);
        }
    }

    @Test
    public void testselect() {
        try (SqlSession session = sqlSessionFactory.openSession()) {


            UserMapper mapper = session.getMapper(UserMapper.class);
            //获得一个代理对象 jdk.proxy 类,  代理对象实现了UserMapper的接口
            //mapper 和xml
            List<User> users = mapper.select("itnanls", "123456");
            log.debug("users is [{}]", users);
        }
    }

    @Test
    public void testselectByUser() {
        try (SqlSession session = sqlSessionFactory.openSession()) {


            UserMapper mapper = session.getMapper(UserMapper.class);
            //获得一个代理对象 jdk.proxy 类,  代理对象实现了UserMapper的接口
            //mapper 和xml
//            User param = new User(1, "itnanls", "123456");
            User param = new User();
            param.setId(2);
//            param.setUsername("itnanls");
            List<User> users = mapper.selectByUser(param);
            log.debug("users is [{}]", users);
        }
    }

    @Test
    public void testInsert() {
        try (SqlSession session = sqlSessionFactory.openSession()) {
            try {
                UserMapper mapper = session.getMapper(UserMapper.class);
                //获得一个代理对象 jdk.proxy 类,  代理对象实现了UserMapper的接口
                //mapper 和xml
                User param = new User(null, "hj1j", "hjjyyds");

                int i = mapper.insert(param);
                log.debug("user is [{}]", i);

                log.debug("{}",param.getId());
//                int k = 1 / 0;
                session.commit();
            } catch (Exception e) {
                log.error("chao", e);
                session.rollback();
            }


        }
    }

    @Test
    public void testUpdate() {
        try (SqlSession session = sqlSessionFactory.openSession(true)) {
            try {
                UserMapper mapper = session.getMapper(UserMapper.class);
                //获得一个代理对象 jdk.proxy 类,  代理对象实现了UserMapper的接口
                //mapper 和xml
                User param = new User(11, "hjj", null);
                int i = mapper.update(param);
                log.debug("user is [{}]", i);
//                session.commit();
            } catch (Exception e) {
                log.error("chao", e);
                session.rollback();
            }
        }
    }

    @Test
    public void testdelteByIds() {
        try (SqlSession session = sqlSessionFactory.openSession(true)) {
            try {
                UserMapper mapper = session.getMapper(UserMapper.class);

                int i = mapper.deleteByIds(List.of(1, 11, 10));
                log.debug("user is [{}]", i);
            } catch (Exception e) {
                log.error("chao", e);
                session.rollback();
            }


        }
    }

    @Test
    public void testinsertByIds() {
        try (SqlSession session = sqlSessionFactory.openSession(true)) {
            try {
                UserMapper mapper = session.getMapper(UserMapper.class);
                List<User> users = List.of(new User(21, "rick", "111"),
                        new User(22, "rick2", "111"),
                        new User(23, "rick3", "111")
                );
                int i = mapper.insertByIds(users);
                log.debug("user is [{}]", i);
            } catch (Exception e) {
                log.error("chao", e);
                session.rollback();
            }


        }
    }


}
