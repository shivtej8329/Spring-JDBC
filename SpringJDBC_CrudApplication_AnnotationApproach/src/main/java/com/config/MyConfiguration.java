package com.config;

import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import com.mainapp.Crud;

@Configuration
@ComponentScan("com.mainapp")
@PropertySource("classpath:config.properties")
public class MyConfiguration {

	@Autowired
	private Environment environment;

	@Bean
	public DataSource dataSource() {

		DriverManagerDataSource ds = new DriverManagerDataSource();

		ds.setUrl(environment.getProperty("db.url"));
		ds.setUsername(environment.getProperty("db.username"));
		ds.setPassword(environment.getProperty("db.password"));
		ds.setDriverClassName(environment.getProperty("db.driver"));

		return ds;
	}

	@Bean
	public JdbcTemplate jdbcTemplate() {
		return new JdbcTemplate(dataSource());
	}

	@Bean("crud")
	public Crud crud() {
		Crud crud = new Crud();
		crud.setJdbcTemplate(jdbcTemplate());
		return crud;
	}
}
