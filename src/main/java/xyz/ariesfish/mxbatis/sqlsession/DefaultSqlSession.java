package xyz.ariesfish.mxbatis.sqlsession;

import xyz.ariesfish.mxbatis.config.Configuration;
import xyz.ariesfish.mxbatis.sqlsession.proxy.MapperProxy;
import xyz.ariesfish.mxbatis.utils.DataSourceUtil;

import java.lang.reflect.Proxy;
import java.sql.Connection;

public class DefaultSqlSession implements SqlSession {

    private Configuration cfg;
    private Connection connection;
    public DefaultSqlSession(Configuration cfg) {
        this.cfg = cfg;
        connection = DataSourceUtil.getConnection(cfg);
    }

    public <T> T getMapper(Class<T> daoInterfaceClass) {
        return (T) Proxy.newProxyInstance(daoInterfaceClass.getClassLoader(),
                new Class[]{ daoInterfaceClass },
                new MapperProxy(cfg.getMappers(), connection));
    }

    public void close() {
        if (connection != null) {
            try {
                connection.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }
}
