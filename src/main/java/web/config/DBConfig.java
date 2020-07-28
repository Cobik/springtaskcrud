package web.config;


import org.hibernate.jpa.HibernatePersistenceProvider;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.*;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.util.Properties;


@Configuration
@EnableTransactionManagement
@ComponentScan(value = "web")
@PropertySource("classpath:config.properties")
public class DBConfig implements WebMvcConfigurer {

    @Value("${hibernate.connection.url}")
    private String dbUrl;

    @Value("${hibernate.connection.driver_class}")
    private String dbDriverClass;

    @Value("${hibernate.connection.username}")
    private String dbUsername;

    @Value("${hibernate.connection.password}")
    private String dbPassword;

    @Value("${hibernate.dialect}")
    private String dbDialect;

    @Value("${hibernate.hbm2ddl.auto}")
    private String dbHbm2ddl;

    @Value("${hibernate.show_sql}")
    private String dbShowSql;

    @Value("${hibernate.current_session_context_class}")
    private String dbSessionContextClass;


    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactoryBean() {

        LocalContainerEntityManagerFactoryBean entityManagerFactoryBean = new LocalContainerEntityManagerFactoryBean();
        entityManagerFactoryBean.setJpaVendorAdapter(vendorAdapter());
        entityManagerFactoryBean.setDataSource(getDataSource());
        entityManagerFactoryBean.setPersistenceProviderClass(HibernatePersistenceProvider.class);
        entityManagerFactoryBean.setPackagesToScan("web.model");
        entityManagerFactoryBean.setJpaProperties(hibernateProp());

        return entityManagerFactoryBean;
    }

    private HibernateJpaVendorAdapter vendorAdapter() {
        HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        vendorAdapter.setShowSql(true);
        return vendorAdapter;
    }

    @Bean
    public DataSource getDataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(dbDriverClass);
        dataSource.setUrl(dbUrl);
        dataSource.setUsername(dbUsername);
        dataSource.setPassword(dbPassword);
        return dataSource;
    }

    private Properties hibernateProp() {

        Properties properties = new Properties();
        properties.put("hibernate.dialect", dbDialect);
        properties.put("hibernate.show_sql", dbShowSql);
        properties.put("hibernate.current_session_context_class", dbSessionContextClass);
        properties.put("hibernate.hbm2ddl.auto", dbHbm2ddl);


        return properties;
    }

    @Bean
    public JpaTransactionManager getJpaTransactionManager(EntityManagerFactory entityManagerFactory) {
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(entityManagerFactory);
        return transactionManager;
    }

}
