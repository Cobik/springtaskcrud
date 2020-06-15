package web.utils;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import web.model.User;

import java.util.Properties;

public class DBHelper {
    private static DBHelper instance;
    private static SessionFactory sessionFactory;

    private DBHelper(){
    }

    public static DBHelper getInstance() {
        if (instance == null) {
            instance = new DBHelper();
        }
        return instance;
    }

    @SuppressWarnings("UnusedDeclaration")
    public static Configuration getConfiguration() {
        Configuration configuration = new Configuration();
        configuration.addAnnotatedClass(User.class);
        Properties property = PropertyReader.getProperties("config.properties");


        configuration.setProperty("hibernate.dialect", property.getProperty("hibernate.dialect"));
        configuration.setProperty("hibernate.connection.driver_class", property.getProperty("hibernate.connection.driver_class"));
        configuration.setProperty("hibernate.connection.url", property.getProperty("hibernate.connection.url"));
        configuration.setProperty("hibernate.connection.username", property.getProperty("hibernate.connection.username"));
        configuration.setProperty("hibernate.connection.password", property.getProperty("hibernate.connection.password"));
        configuration.setProperty("hibernate.show_sql", property.getProperty("hibernate.show_sql"));
        configuration.setProperty("hibernate.current_session_context_class",property.getProperty("hibernate.current_session_context_class"));
        configuration.setProperty("hibernate.hbm2ddl.auto", "create");
        return configuration;
    }

    public static SessionFactory createSessionFactory() {
        Configuration configuration = getConfiguration();
        StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder();
        builder.applySettings(configuration.getProperties());
        ServiceRegistry serviceRegistry = builder.build();
        return configuration.buildSessionFactory(serviceRegistry);
    }

    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            sessionFactory = createSessionFactory();
        }
        return sessionFactory;
    }
}
