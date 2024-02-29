package cphbusiness.groupone.config;

import jakarta.persistence.EntityManagerFactory;
import lombok.NoArgsConstructor;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

import java.util.HashMap;
import java.util.Properties;
import java.util.Set;


@SuppressWarnings("rawtypes")
@NoArgsConstructor(access = lombok.AccessLevel.PRIVATE)
public class HibernateConfig {

    private static final HashMap<Set<Class>,EntityManagerFactory> entityManagerFactories = new HashMap<>();

    private static EntityManagerFactory buildEntityFactoryConfig(boolean isDevEnv, Set<Class> classes) {
        try {
            Configuration configuration = new Configuration();

            //// This is my own workaround to the configuration because I was tired of duplicate code.
            classes.forEach(configuration::addAnnotatedClass);

            Properties props = new Properties();

            if(isDevEnv)
                props.put("hibernate.connection.url", "jdbc:postgresql://localhost:5432/hobby?currentSchema=dev");
            else
                props.put("hibernate.connection.url", "jdbc:postgresql://localhost:5432/hobby?currentSchema=public");
            props.put("hibernate.connection.username", "postgres");
            props.put("hibernate.connection.password", "postgres");
            props.put("hibernate.show_sql", "false"); // show sql in console
            props.put("hibernate.format_sql", "true"); // format sql in console
            props.put("hibernate.use_sql_comments", "true"); // show sql comments in console

            //props.put("hibernate.dialect", "org.hibernate.dialect.PostgreSQLDialect"); // dialect for postgresql - already default
            props.put("hibernate.connection.driver_class", "org.postgresql.Driver"); // driver class for postgresql
            props.put("hibernate.archive.autodetection", "class"); // hibernate scans for annotated classes
            props.put("hibernate.current_session_context_class", "thread"); // hibernate current session context

            props.put("hibernate.metamodel.mapping.DiscriminatorType.getJdbcTypeCount()", "all");

            //// hibernate creates tables based on entities - create = new each run - update = persistent
            if(isDevEnv)
                props.put("hibernate.hbm2ddl.auto", "create");
            else
                //// in case we want to put production as update, dev is always create
                props.put("hibernate.hbm2ddl.auto", "update");



            return getEntityManagerFactory(configuration, props);
        } catch (Throwable ex) {
            System.err.println("Initial SessionFactory creation failed." + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }

    private static EntityManagerFactory getEntityManagerFactory(Configuration configuration, Properties props) {
        configuration.setProperties(props);

        ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties()).build();
        System.out.println("Hibernate Java Config serviceRegistry created");

        SessionFactory sf = configuration.buildSessionFactory(serviceRegistry);
        return sf.unwrap(EntityManagerFactory.class);
    }

    public static EntityManagerFactory getEntityManagerFactoryConfig(boolean isDevEnv, Set<Class> classes) {
        if(!entityManagerFactories.containsKey(classes) || !entityManagerFactories.get(classes).isOpen()){
            EntityManagerFactory emf = buildEntityFactoryConfig(isDevEnv, classes);
            entityManagerFactories.remove(classes);
            entityManagerFactories.put(classes, emf);
        }
        return entityManagerFactories.get(classes);
    }
}