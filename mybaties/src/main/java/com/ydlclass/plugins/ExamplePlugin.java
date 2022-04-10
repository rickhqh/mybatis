package com.ydlclass.plugins;

import com.ydlclass.PageHelper;
import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.SqlSource;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Signature;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;

import java.util.List;
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
        //拿到原始的sql
        Object[] args = invocation.getArgs();
        MappedStatement ms = (MappedStatement) args[0];
        BoundSql boundSql = ms.getBoundSql(args[1]);
        String sql = boundSql.getSql();
//        改造sql
        String newsql=sql+" limit "+ PageHelper.TL.get().getFrom()+","+PageHelper.TL.get().getOffset();
        SqlSource sqlsource= parameterObject -> new BoundSql(ms.getConfiguration(),newsql,null,args[1]);


        MappedStatement mappedstatement = new MappedStatement.Builder(ms.getConfiguration(), ms.getId() + "byPage", sqlsource, ms.getSqlCommandType()).cache(ms.getCache()).keyGenerator(ms.getKeyGenerator()).resultMaps(ms.getResultMaps()).build();

//        调用方法
        Executor executor = (Executor)invocation.getTarget();
        List<Object> returnObject = executor.query(mappedstatement, args[1], (RowBounds) args[2], (ResultHandler) args[3]);


//        System.out.println("执行之前");
//        System.out.println("1");
//        Object returnObject = invocation.proceed();
//        // implement post processing if need
//        System.out.println("执行之后");
//        Object someProperty = properties.get("someProperty");
//        System.out.println(someProperty);
        return returnObject;
    }
    @Override
    public void setProperties(Properties properties) {
        this.properties = properties;
    }
}
