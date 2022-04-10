package com.ydlclass.plugins;

import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Signature;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;

import java.util.Properties;

/**
 * @author 小松
 */
@Intercepts({@Signature(
        type= Executor.class,
        method = "query",
        args = {MappedStatement.class,Object.class, RowBounds.class, ResultHandler.class})})
public class ExamplePlugin implements Interceptor {
    private Properties properties = new Properties();
    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        // implement pre processing if need
        System.out.println("执行之前");
        Object returnObject = invocation.proceed();
        // implement post processing if need
        System.out.println("执行之后");
        Object someProperty = properties.get("someProperty");
        System.out.println(someProperty);
        return returnObject;
    }
    @Override
    public void setProperties(Properties properties) {
        this.properties = properties;
    }
}
