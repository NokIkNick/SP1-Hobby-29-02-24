package cphbusiness.groupone.dao.abstractDAOs;

import cphbusiness.groupone.config.HobbyConfig;
import cphbusiness.groupone.dao.IDAO;
import cphbusiness.groupone.model.DTO;
import jakarta.persistence.EntityManagerFactory;

public abstract class DAO<T extends DTO<IDType>, IDType> implements IDAO<T, IDType> {

    static EntityManagerFactory emf;

    public DAO(){
        emf = HobbyConfig.getInstance(/* Ja den skal være tom, da vi skal kunne teste på den! */);
    }


    @Override
    public void create(T in){
        if(in != null){
            try(var em = emf.createEntityManager()){
                em.getTransaction().begin();
                em.persist(in);
                em.getTransaction().commit();
            }
        }
    }
    public abstract T read(IDType id);
    @Override
    public T read (IDType id, Class<T> tClass) {
        try(var em = emf.createEntityManager()){
            T found = em.find(tClass,id);
            if(found != null){
                return found;
            }
        }
        return null;
    }

    @Override
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
    @Override
    public T update(T obj) {
        return update(obj, obj.getID());
    }

    @Override
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
        return t.getClass();
    }


}
