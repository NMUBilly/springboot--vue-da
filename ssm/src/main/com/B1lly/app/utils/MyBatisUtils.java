package com.B1lly.app.utils;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.Reader;

public class MyBatisUtils {

    private static SqlSessionFactory sqlSessionFactory = null;

    static{
        try {
            //获得数据库配置文件的连接信息，封装Reader
            Reader reader = Resources.getResourceAsReader("mybatis.xml");
            sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 该方法返回SqlSession对象，该对象是mybatis操作数据库表的对象，该类中含有操作数据库的增删改查方法
     *
     * @return
     */
    public static SqlSession getSqlSession() {
        return sqlSessionFactory.openSession();
    }

    /**
     * 关闭连接释放资源
     *
     * @param session
     */
    public static void close(SqlSession session) {
        if (session != null) {
            session.close();
        }
    }
}
