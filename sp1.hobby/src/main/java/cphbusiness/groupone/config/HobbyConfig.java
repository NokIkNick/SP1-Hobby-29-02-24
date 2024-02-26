package cphbusiness.groupone.config;

import cphbusiness.groupone.model.*;
import jakarta.persistence.EntityManagerFactory;

import java.util.Set;

public class HobbyConfig {
    private static EntityManagerFactory emf;
    @SuppressWarnings({"rawtypes", "FieldMayBeFinal"})
    private static Set<Class> classes = Set.of(Address.class, Gender.class, Hobby.class, User.class, UserDetails.class, Zip.class);
    private static boolean isDevEnv = false;
    public static EntityManagerFactory getInstance(boolean devEnv){
        if((emf == null || !emf.isOpen()) || devEnv != isDevEnv){
            emf = HibernateConfig.getEntityManagerFactoryConfig(devEnv, classes);
            isDevEnv = devEnv;
        }
        return emf;
    }
    public static EntityManagerFactory getInstance() {
        return getInstance(isDevEnv);
    }
}
