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
import org.springframework.orm.jpa.vendor.EclipseLinkJpaVendorAdapter;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.Properties;

/**
 * @author EvanHuang
 * @date 1/10/2019 4:18 PM
 * @since
 */
@Configuration
@EnableTransactionManagement(proxyTargetClass = true)
@EnableJpaRepositories(basePackages = "com.evan.jta.repository.ds1", entityManagerFactoryRef = "entityManagerFactory", transactionManagerRef = "transactionManager")
public class JpaConfigDs1 {
    @Bean(name = "dataSource", initMethod = "init", destroyMethod = "close")
    @ConfigurationProperties(prefix = "spring.jta.atomikos.datasource.jta-user")
    public DataSource dataSource() {
        System.out.println("jta-user dataSource init");
        return new AtomikosDataSourceBean();
    }

    @Bean(name = "jpaVendorAdapter")
    public JpaVendorAdapter jpaVendorAdapter() {
        System.out.println("jpaVendorAdapter init");
        EclipseLinkJpaVendorAdapter adapter = new EclipseLinkJpaVendorAdapter();
        adapter.setShowSql(true);
        adapter.setDatabase(Database.MYSQL);
        adapter.setDatabasePlatform("org.eclipse.persistence.platform.database.MySQLPlatform");
        adapter.setGenerateDdl(true);
        return adapter;
    }

    @Bean(name = "entityManagerFactory")
    public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
        System.out.println("entityManagerFactory init");
        LocalContainerEntityManagerFactoryBean entityManager = new LocalContainerEntityManagerFactoryBean();

        entityManager.setJpaVendorAdapter(jpaVendorAdapter());
        // entity package
        entityManager.setPackagesToScan("com.evan.jta.model.ds1");
        entityManager.setJtaDataSource(dataSource());

        Properties properties = new Properties();
        properties.put("eclipselink.weaving", "false");
        properties.put("eclipselink.jdbc.cache-statements", "true");
        properties.put("eclipselink.logging.level", "FINEST");
        properties.put("eclipselink.allow-zero-id", "true");

        properties.put("eclipselink.target-server", "com.evan.jta.config.AtomikosTransactionController");
        properties.put("eclipselink.external-transaction-controller", "true");
        entityManager.setJpaProperties(properties);
        entityManager.setPersistenceUnitName("entityManagerFactory_user");
        return entityManager;

    }
}
