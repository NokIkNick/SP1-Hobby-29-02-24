package cphbusiness.groupone.dao.abstractDAOs;

import cphbusiness.groupone.config.HibernateConfig;
import cphbusiness.groupone.config.HobbyConfig;
import cphbusiness.groupone.dao.IDAO;
import cphbusiness.groupone.model.User;
import jakarta.persistence.EntityManagerFactory;
import org.hibernate.Hibernate;

public abstract class DAO <T> implements IDAO<T> {

    private static EntityManagerFactory emf;

    public DAO(){
        emf = HobbyConfig.getInstance(false);
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
    public T read (String id, Class<T> tClass) {
        try(var em = emf.createEntityManager()){
            T found = em.find(tClass,id);
            if(found != null){
                return found;
            }
        }
        return null;
    }

    public T read (int id, Class<T> tClass) {
        try(var em = emf.createEntityManager()){
            T found = em.find(tClass,id);
            if(found != null){
                return found;
            }
        }
        return null;
    }

    @SuppressWarnings("unchecked")
    public T update(T obj, String id){
        T toReturn = null;
        if(obj != null){
            try(var em = emf.createEntityManager()){
                em.getTransaction().begin();
                Object found = em.find(getGenericType(obj), id);
                if(found != null){
                   toReturn = em.merge(obj);
                   em.getTransaction().commit();
                }
            }
        }
        return toReturn;
    }

    public T update(T obj, int id){
        T toReturn = null;
        if(obj != null){
            try(var em = emf.createEntityManager()){
                em.getTransaction().begin();
                Object found = em.find(getGenericType(obj), id);
                if(found != null){
                    toReturn = em.merge(obj);
                    em.getTransaction().commit();
                }
            }
        }
        return toReturn;
    }

    public void delete(Class<T> tClass,String id){
        try(var em = emf.createEntityManager()){
            em.getTransaction().begin();
            Object found = em.find(tClass,id);
            if(found != null){
                em.remove(found);
                em.getTransaction().commit();
            }
        }
    }

    public void delete(Class<T> tClass,int id){
        try(var em = emf.createEntityManager()){
            em.getTransaction().begin();
            Object found = em.find(tClass,id);
            if(found != null){
                em.remove(found);
                em.getTransaction().commit();
            }
        }
    }


    static <T> Class getGenericType(T t){
        return getType(t);
    }

    static Class<?> getType(Object o){
        return o.getClass();
    }


}
