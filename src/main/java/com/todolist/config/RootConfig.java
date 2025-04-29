package com.todolist.config;

import com.todolist.util.AppConstants;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import jakarta.persistence.EntityManagerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.Properties;

@Configuration
@EnableTransactionManagement
@ComponentScan(basePackages = {AppConstants.PACKAGE_DAO_SERVICE})
public class RootConfig {

    private String getEnvOrDefault(String envName, String defaultValue) {
        String value = System.getenv(envName);
        return (value != null && !value.isEmpty()) ? value : defaultValue;
    }

    @Bean
    public DataSource dataSource() {
        HikariConfig config = new HikariConfig();

        String dbUrl = getEnvOrDefault("DB_URL", AppConstants.DB_URL);
        String dbUser = getEnvOrDefault("DB_USER", AppConstants.DB_USERNAME);
        String dbPassword = getEnvOrDefault("DB_PASSWORD", AppConstants.DB_PASSWORD);
        String dbDriver = AppConstants.DB_DRIVER;

        if (dbUrl.contains("mysql:3306")) {
            dbDriver = "com.mysql.cj.jdbc.Driver";
        }

        config.setJdbcUrl(dbUrl);
        config.setUsername(dbUser);
        config.setPassword(dbPassword);
        config.setDriverClassName(dbDriver);
        config.setMaximumPoolSize(AppConstants.DB_MAX_POOL_SIZE);
        config.setMinimumIdle(AppConstants.DB_MIN_IDLE);
        config.setIdleTimeout(AppConstants.DB_IDLE_TIMEOUT);
        config.setPoolName(AppConstants.DB_POOL_NAME);

        return new HikariDataSource(config);
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
        LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
        em.setDataSource(dataSource());
        em.setPackagesToScan(AppConstants.PACKAGE_DOMAIN);

        JpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        em.setJpaVendorAdapter(vendorAdapter);
        em.setJpaProperties(additionalProperties());

        return em;
    }

    @Bean
    public PlatformTransactionManager transactionManager(EntityManagerFactory emf) {
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(emf);
        return transactionManager;
    }

    @Bean
    public PersistenceExceptionTranslationPostProcessor exceptionTranslation() {
        return new PersistenceExceptionTranslationPostProcessor();
    }

    private Properties additionalProperties() {
        Properties properties = new Properties();
        properties.setProperty(AppConstants.HIBERNATE_DIALECT, AppConstants.HIBERNATE_DIALECT_VALUE);
        properties.setProperty(AppConstants.HIBERNATE_SHOW_SQL, AppConstants.HIBERNATE_SHOW_SQL_VALUE);
        properties.setProperty(AppConstants.HIBERNATE_FORMAT_SQL, AppConstants.HIBERNATE_FORMAT_SQL_VALUE);
        properties.setProperty(AppConstants.HIBERNATE_HBM2DDL_AUTO, AppConstants.HIBERNATE_HBM2DDL_AUTO_VALUE);
        return properties;
    }
}