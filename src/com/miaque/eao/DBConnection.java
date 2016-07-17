package com.miaque.eao;

import javax.sql.DataSource;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * Created by Mique on 2016/7/17.
 */
public class DBConnection {

    public static Connection getConnection() {

        Connection connection = null;

        try {
            // 初始化Context，使用InitialContext初始化Context；
            Context initCtx = new InitialContext();

            // 通过JNDI查找数据源，该JNDI为java:com/env/jdbc/test，分成两个部分
            // java:com/env是Tomcat固定的，Tomcat提供的JNDI绑定都必须加上该前缀
            // jdbc/test是定义数据源时数据源名
            Context envCtx = (Context) initCtx.lookup("java:com/env");
            DataSource ds = (DataSource) envCtx.lookup("jdbc/test");

            // 获取数据连接
            connection = ds.getConnection();

        } catch (NamingException ex) {
            System.out.println("数据源没找着！");
            ex.printStackTrace();
        } catch (SQLException ex) {
            System.out.println("获取连接对象失败！");
            ex.printStackTrace();
        }
        return connection;
    }
}
