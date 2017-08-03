package com.allstack;

import java.util.Properties;

import javax.sql.DataSource;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.allstack.dao.CourseDao;
import com.allstack.dao.CourseSectionDao;
import com.allstack.services.CourseSectionService;
import com.allstack.services.CourseService;

@SpringBootApplication
public class App 
{
    public static void main( String[] args )
    {
    	SpringApplication.run(App.class, args);
    }
    
    @Bean
    public CourseService courseService(){
    	return new CourseService();
    }
    
    @Bean
    public CourseSectionService courseSectionService(){
    	return new CourseSectionService();
    }
    
    @Bean
    public CourseDao courseDao(){
    	return new CourseDao();
    }
    
    @Bean
    public CourseSectionDao courseSectionDao(){
    	return new CourseSectionDao();
    }
   
    @Bean
    public DataSource dataSource() {
        return DataSourceBuilder
            .create()
            .username("root")
            .password("123456")
            .url("jdbc:mysql://localhost:3306/i80")
            .driverClassName("com.mysql.jdbc.Driver")
            .build();
    }
    
    @Bean
    public LocalSessionFactoryBean sessionFactory() {
       LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
       sessionFactory.setDataSource(dataSource());
       sessionFactory.setPackagesToScan(
         new String[] { "com.allstack.pojo" });
       sessionFactory.setHibernateProperties(hibernateProperties());
  
       return sessionFactory;
    }
    
    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurerAdapter() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/api/**").allowedOrigins("http://localhost:3000");
            }
        };
    }
    
    Properties hibernateProperties() {
        return new Properties() {
           {
              setProperty("hibernate.show_sql", "true");
              setProperty("hibernate.dialect", "org.hibernate.dialect.MySQLDialect");
           }
        };
     }
}
