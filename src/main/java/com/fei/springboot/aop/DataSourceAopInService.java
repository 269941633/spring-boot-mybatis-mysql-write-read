package com.fei.springboot.aop;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.core.PriorityOrdered;
import org.springframework.stereotype.Component;

import com.fei.springboot.config.dbconfig.DataSourceContextHolder;
import com.fei.springboot.config.dbconfig.DataSourceType;

/**
 * 在service层觉得数据源
 * 
 * 必须在事务AOP之前执行，所以实现Ordered,order的值越小，越先执行
 * 如果一旦开始切换到写库，则之后的读都会走写库
 * 
 * @author Jfei
 *
 */
@Aspect
@EnableAspectJAutoProxy(exposeProxy=true,proxyTargetClass=true)
@Component
public class DataSourceAopInService implements PriorityOrdered{

private static Logger log = LoggerFactory.getLogger(DataSourceAopInService.class);
	
/*	@Before("execution(* com.fei.springboot.service..*.find*(..)) "
			+ " or execution(* com.fei.springboot.service..*.get*(..)) "
			+ " or execution(* com.fei.springboot.service..*.query*(..))")
    public void setReadDataSourceType() {
		//如果已经开启写事务了，那之后的所有读都从写库读
		if(!DataSourceType.write.getType().equals(DataSourceContextHolder.getReadOrWrite())){
			DataSourceContextHolder.setRead();
		}
        
    }

    @Before("execution(* com.fei.springboot.service..*.insert*(..)) "
    		+ " or execution(* com.fei.springboot.service..*.update*(..))"
    		+ " or execution(* com.fei.springboot.service..*.add*(..))")
    public void setWriteDataSourceType() {
        DataSourceContextHolder.setWrite();
    }*/
    

	@Before("execution(* com.fei.springboot.service..*.*(..)) "
			+ " and @annotation(com.fei.springboot.annotation.ReadDataSource) ")
	public void setReadDataSourceType() {
		//如果已经开启写事务了，那之后的所有读都从写库读
		if(!DataSourceType.write.getType().equals(DataSourceContextHolder.getReadOrWrite())){
			DataSourceContextHolder.setRead();
		}
	    
	}
	
	@Before("execution(* com.fei.springboot.service..*.*(..)) "
			+ " and @annotation(com.fei.springboot.annotation.WriteDataSource) ")
	public void setWriteDataSourceType() {
	    DataSourceContextHolder.setWrite();
	}
    
	@Override
	public int getOrder() {
		
		return HIGHEST_PRECEDENCE;
	}

}
