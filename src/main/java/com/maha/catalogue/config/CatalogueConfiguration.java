package com.maha.catalogue.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.init.Jackson2RepositoryPopulatorFactoryBean;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;
import java.util.HashMap;

/**
 * This class is the configuation class for the spring boot project
 * It is detected by scan packages specified in {@link com.maha.catalogue.WatchCatalogueApplication}
 * Created by durga on 8/18/2020.
 */

@Configuration
@PropertySource({"classpath:application.properties"})
@EnableJpaRepositories(
        basePackages = "com.maha.catalogue",
        entityManagerFactoryRef = "h2EntityManager",
        transactionManagerRef = "h2TransactionManager")
public class CatalogueConfiguration {

    /**
     * Inject environment variable
     */
    @Autowired
    private Environment environment;


    /**
     * Primary Bean for h2 datasource
     * creates data source from application.properties
     * @return DataSource
     */
    @Primary
    @Bean
    @ConfigurationProperties(prefix="spring.datasource")
    public DataSource h2DataSource() {
        return DataSourceBuilder.create().build();
    }

    /**
     * Primary Bean for entity manager factory
     * @param dataSource
     * @return LocalContainerEntityManagerFactoryBean
     */
    @Primary
    @Bean
    public LocalContainerEntityManagerFactoryBean h2EntityManager(@Autowired DataSource dataSource) {
        final LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
        em.setDataSource(dataSource);
        em.setPackagesToScan("com.maha.catalogue");

        final HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        em.setJpaVendorAdapter(vendorAdapter);
        final HashMap<String, Object> properties = new HashMap<String, Object>();
        properties.put("hibernate.hbm2ddl.auto", environment.getProperty("hibernate.hbm2ddl.auto"));
        properties.put("hibernate.dialect", environment.getProperty("spring.jpa.database-platform"));
        em.setJpaPropertyMap(properties);

        return em;
    }


    /**
     * Primary Bean for Transaction Manager
     * @param entityManagerFactoryBean
     * @return TransactionManager
     */
    @Primary
    @Bean
    public PlatformTransactionManager h2TransactionManager(@Autowired LocalContainerEntityManagerFactoryBean entityManagerFactoryBean) {
        final JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(entityManagerFactoryBean.getObject());
        return transactionManager;
    }

    /**
     * This method populates the data from watches.json into h2 database on start of the application.
     * @return Jackson2RepositoryPopulatorFactoryBean
     */
    @Bean
    public Jackson2RepositoryPopulatorFactoryBean getRespositoryPopulator() {
        Jackson2RepositoryPopulatorFactoryBean factory = new Jackson2RepositoryPopulatorFactoryBean();
        factory.setResources(new Resource[]{new ClassPathResource("watches.json")});

        return factory;
    }
}
