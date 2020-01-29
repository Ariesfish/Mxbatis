package xyz.ariesfish.mxbatis.sqlsession;

import xyz.ariesfish.mxbatis.config.Configuration;
import xyz.ariesfish.mxbatis.utils.XMLConfigBuilder;

import java.io.InputStream;

public class SqlSessionFactoryBuilder {

    /**
     * 根据参数的字节输入流构建SqlSessionFactory
     * @param config
     * @return
     */
    public SqlSessionFactory build(InputStream config) {
        Configuration cfg = XMLConfigBuilder.loadConfiguration(config);
        return new DefaultSqlSessionFactory(cfg);
    }
}
