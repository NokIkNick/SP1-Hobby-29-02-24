package cphbusiness.groupone.dao.abstractDAOs;

import cphbusiness.groupone.config.HibernateConfig;
import cphbusiness.groupone.dao.IDAO;
import jakarta.persistence.EntityManagerFactory;
import org.hibernate.Hibernate;

public abstract class DAO <T> implements IDAO<T> {


    private static EntityManagerFactory emf;

    private EntityManagerFactory getInstance(EntityManagerFactory emf_){
        if(emf == null){
            emf = emf_;
        }
        return emf;
    }


    public DAO(EntityManagerFactory emf_){
        emf = getInstance(emf_);
    }


    public void create(T in){
        try(var em = emf.createEntityManager()){
            em.getTransaction().begin();
            if(in != null){
                em.persist(in);
                em.getTransaction().commit();
            }
        }
    }

    public T read (int id, T in){
        try(var em = emf.createEntityManager()){
            Object found = (T) em.find(in.getClass(), id);
            if(obj != null)
        }
    }

}
