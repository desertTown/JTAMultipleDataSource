package com.evan.jta.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jta.atomikos.AtomikosDataSourceBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.Database;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.Properties;

/**
 * @author EvanHuang
 * @date 1/10/2019 6:27 PM
 * @since
 */
@Configuration
@EnableTransactionManagement(proxyTargetClass = true)
@EnableJpaRepositories(basePackages = "com.evan.jta.repository.ds2", entityManagerFactoryRef = "entityManagerFactory2", transactionManagerRef = "transactionManager")
public class JpaConfigDs2 {
    @Bean(name = "dataSource2", initMethod = "init", destroyMethod = "close")
    @ConfigurationProperties(prefix = "spring.jta.atomikos.datasource.jta-product")
    public DataSource dataSource() {
        System.out.println("jta-user dataSource init");
        return new AtomikosDataSourceBean();
    }

    @Bean(name = "jpaVendorAdapter2")
    public JpaVendorAdapter jpaVendorAdapter() {
        System.out.println("jpaVendorAdapter2 init");
        HibernateJpaVendorAdapter adapter = new HibernateJpaVendorAdapter();
        adapter.setShowSql(true);
        adapter.setDatabase(Database.MYSQL);
        adapter.setDatabasePlatform("org.hibernate.dialect.MySQLDialect");
        adapter.setGenerateDdl(true);
        return adapter;
    }

    @Bean(name = "entityManagerFactory2")
    @DependsOn({"atomikosJtaPlatfom"})
    public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
        System.out.println("entityManagerFactory2 init");
        LocalContainerEntityManagerFactoryBean entityManager = new LocalContainerEntityManagerFactoryBean();


        entityManager.setJpaVendorAdapter(jpaVendorAdapter());
        entityManager.setPackagesToScan("com.evan.jta.model.ds2");// entity package
        entityManager.setJtaDataSource(dataSource());

        Properties properties = new Properties();
        properties.put("hibernate.transaction.jta.platform","com.evan.jta.config.AtomikosJtaPlatfom");

        properties.put("hibernate.dialect", "org.hibernate.dialect.MySQLDialect");
        properties.put("hibernate.show_sql", "true");
        properties.put("hibernate.format_sql", "true");
        properties.put("hibernate.current_session_context_class", "jta");
        properties.put("hibernate.transaction.factory_class", "org.hibernate.engine.transaction.internal.jta.CMTTransactionFactory");

        entityManager.setJpaProperties(properties);
        return entityManager;

    }
}
