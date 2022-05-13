package com.igas.express;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;



@SpringBootApplication
public class IGasExpressApplication extends org.springframework.boot.web.servlet.support.SpringBootServletInitializer {
	
  @Override
  protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(IGasExpressApplication.class);
        }


	public static void main(String[] args) {
	SpringApplication.run(IGasExpressApplication.class, args);
	}
}
