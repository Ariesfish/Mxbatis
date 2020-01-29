package xyz.ariesfish.mxbatis.sqlsession;

import xyz.ariesfish.mxbatis.config.Configuration;

public class DefaultSqlSessionFactory implements SqlSessionFactory {

    private Configuration cfg;

    public DefaultSqlSessionFactory(Configuration cfg) {
        this.cfg = cfg;
    }

    public SqlSession openSession() {
        return new DefaultSqlSession(cfg);
    }
}
