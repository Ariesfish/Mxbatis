package xyz.ariesfish.mxbatis.sqlsession.proxy;

import xyz.ariesfish.mxbatis.config.Mapper;
import xyz.ariesfish.mxbatis.utils.Executor;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.sql.Connection;
import java.util.Map;

public class MapperProxy implements InvocationHandler {

    private Map<String, Mapper> mappers;
    private Connection connection;

    public MapperProxy(Map<String, Mapper> mappers, Connection conn) {
        this.mappers = mappers;
        this.connection = conn;
    }

    /**
     * 对方法增强，即调用selectList方法
     * @param proxy
     * @param method
     * @param args
     * @return
     * @throws Throwable
     */
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        // 1.获取方法名
        String methodName = method.getName();
        // 2.获取方法所在类的名称
        String className = method.getDeclaringClass().getName();
        // 3.组合Key
        String key = className + "." + methodName;
        // 4.获取mappers中的mapper对象
        Mapper mapper = mappers.get(key);
        // 5.判断是否有mapper
        if (mapper == null) {
            throw new IllegalArgumentException("传入的全限定类名有误");
        }
        // 6.调用工具类执行操作
        return new Executor().selectList(mapper, connection);
    }
}
