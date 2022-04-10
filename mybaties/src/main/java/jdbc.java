import com.ydlclass.entity.User;

import java.sql.*;

public class jdbc {
    public static void main(String[] args) throws Exception {

        //1.数据库连接的4个基本要素：
        String url = "jdbc:mysql://localhost:3306/ssm?characterEncoding=utf8&serverTimezone=Asia/Shanghai";
        String username = "root";
        String password = "111111";
        //8.0之后名字改了  com.mysql.cj.jdbc.Driver
//        String driverName = "com.mysql.cj.jdbc.Driver";

        //2.实例化Driver
//        Class clazz = Class.forName(driverName);
//        Driver driver = (Driver) clazz.newInstance();
//        //3.注册驱动
//        DriverManager.registerDriver(driver);
        //4.获取连接
        Connection conn = DriverManager.getConnection(url, username, password);

        PreparedStatement preparedStatement = conn.prepareStatement("select * from user where id = ?");
        preparedStatement.setInt(1, 1);
        ResultSet resultSet = preparedStatement.executeQuery();

        // 处理结果集
        while (resultSet.next()) {
            User user = new User();
            user.setId(resultSet.getInt("id"));
            user.setUsername(resultSet.getString("username"));
            user.setPassword(resultSet.getString("password"));
            System.out.println(user);
        }
    }
}

