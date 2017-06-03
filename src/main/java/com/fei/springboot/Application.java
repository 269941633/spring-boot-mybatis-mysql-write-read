package com.fei.springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.embedded.ConfigurableEmbeddedServletContainer;
import org.springframework.boot.context.embedded.EmbeddedServletContainerCustomizer;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;

@EnableAutoConfiguration
@ComponentScan(basePackages={"com.fei.springboot"})
@SpringBootApplication
public class Application extends SpringBootServletInitializer implements EmbeddedServletContainerCustomizer{

	 @Override  
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {  
        return application.sources(Application.class);  
    }  
  
	 
	 public static void main(String[] args) throws Exception {
	        SpringApplication.run(Application.class, args);
	    }

	public void customize(ConfigurableEmbeddedServletContainer configurableEmbeddedServletContainer) {
	//	configurableEmbeddedServletContainer.setPort(9090);
	}
}
