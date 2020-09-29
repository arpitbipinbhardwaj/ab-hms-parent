package com.ab.hms.common.database.connection;

import java.util.Properties;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
		basePackages = {
				"com.ab.hms.access.repository",
				"com.ab.hms.common.repository"
		})
@EntityScan({
			"com.ab.hms.access.model",
			"com.ab.hms.common.model"
		})
@EnableCaching
public class DatabaseConnectionConfiguration {

	@Autowired
	private Environment env;
	
	@Bean(name = "dataSource")
	@Primary
	public DataSource dataSource() {
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setUsername(env.getProperty("spring.datasource.username"));
		dataSource.setPassword(env.getProperty("spring.datasource.password"));
		dataSource.setUrl(env.getProperty("spring.datasource.url"));
		dataSource.setDriverClassName(env.getProperty("spring.datasource.driver-class-name"));
		
		return dataSource;
	}
	
	@Bean(name = "entityManagerFactory")
	@Primary
	public LocalContainerEntityManagerFactoryBean entityManagerFactory(
			EntityManagerFactoryBuilder builder, @Qualifier("dataSource") DataSource dataSource
			) {
		LocalContainerEntityManagerFactoryBean entityManager = builder.dataSource(dataSource)
				.packages("com.ab.hms.access.model",
						"com.ab.hms.common.model")
				.persistenceUnit("mySql").build();
		entityManager.setJpaProperties(databaseProperties());
		return entityManager;		
	}
	
	@Bean(name = "transactionManager")
	@Primary
	public PlatformTransactionManager transactionManager(
			@Qualifier("entityManagerFactory") EntityManagerFactory entityManagerFactory
			) {
		return new JpaTransactionManager(entityManagerFactory);
	}
	
	private Properties databaseProperties() {
		Properties properties = new Properties();
		properties.setProperty("hibernate.hbm2ddl.auto",env.getProperty("spring.jpa.hibernate.ddl-auto"));
//		properties.setProperty("spring.jpa.properties.hibernate.default_schema",env.getProperty("spring.jpa.properties.hibernate.default_schema"));
		properties.setProperty("hibernate.show_sql",env.getProperty("spring.jpa.show-sql"));
//		properties.setProperty("hibernate.jdbc.batch_size",env.getProperty("spring.jpa.properties.hibernate.jdbc.batch_size"));
//		properties.setProperty("hibernate.order_inserts",env.getProperty("spring.jpa.properties.hibernate.order_inserts"));
//		properties.setProperty("hibernate.order_updates",env.getProperty("spring.jpa.properties.hibernate.order_updates"));
//		properties.setProperty("hibernate.jdbc.batch_versioned_data",env.getProperty("spring.jpa.properties.hibernate.jdbc.batch_versioned_data"));
//		properties.setProperty("hibernate.jdbc.fetch_size",env.getProperty("spring.jpa.properties.hibernate.jdbc.fetch_size"));
//		properties.setProperty("spring.datasource.initialization-mode",env.getProperty("spring.datasource.initialization-mode"));
		
		return properties;
	}
}
