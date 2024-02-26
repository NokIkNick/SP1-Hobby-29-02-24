package cphbusiness.groupone;

import cphbusiness.groupone.config.HibernateConfig;
import cphbusiness.groupone.model.*;
import jakarta.persistence.EntityManagerFactory;

import java.util.Set;

public class Main {
    public static void main(String[] args) {
        Set<Class> classes = Set.of(User.class, Address.class, Gender.class, Hobby.class, UserDetails.class, Zip.class);
        EntityManagerFactory emf = HibernateConfig.getEntityManagerFactoryConfig(false, classes);
    }
}