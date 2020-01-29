package xyz.ariesfish.mxbatis.utils;

import xyz.ariesfish.mxbatis.config.Configuration;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 * 创建数据源的工具类
 */
public class DataSourceUtil {
    /**
     * 获取一个链接
     * @param cfg
     * @return
     */
    public static Connection getConnection(Configuration cfg) {
        try {
            Class.forName(cfg.getDriver());
            return DriverManager.getConnection(cfg.getUrl(), cfg.getUsername(), cfg.getPassword());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
