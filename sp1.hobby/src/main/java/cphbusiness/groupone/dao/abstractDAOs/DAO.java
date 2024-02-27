package cphbusiness.groupone.dao.abstractDAOs;

import cphbusiness.groupone.config.HibernateConfig;
import cphbusiness.groupone.config.HobbyConfig;
import cphbusiness.groupone.dao.IDAO;
import cphbusiness.groupone.dao.implementations.UserDetailsDAOImpl;
import cphbusiness.groupone.dao.implementations.ZipDAOImpl;
import cphbusiness.groupone.model.DTO;
import cphbusiness.groupone.model.User;
import jakarta.persistence.EntityManagerFactory;
import lombok.experimental.PackagePrivate;
import org.hibernate.Hibernate;

public abstract class DAO<T extends DTO<IDType>, IDType> implements IDAO<T, IDType> {

    static EntityManagerFactory emf;

    public DAO(){
        emf = HobbyConfig.getInstance();
    }


    public void create(T in){
        if(in != null){
            try(var em = emf.createEntityManager()){
                em.getTransaction().begin();
                em.persist(in);
                em.getTransaction().commit();
            }
        }
    }
    public T read (IDType id, Class<T> tClass) {
        try(var em = emf.createEntityManager()){
            T found = em.find(tClass,id);
            if(found != null){
                return found;
            }
        }
        return null;
    }

    @SuppressWarnings("unchecked")
    public T update(T obj, IDType id){
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
    public T update(T obj) {
        return update(obj, obj.getID());
    }

    public void delete(Class<T> tClass, IDType id){
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
