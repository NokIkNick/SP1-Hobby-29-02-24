package cphbusiness.groupone.dao.abstractDAOs;

import cphbusiness.groupone.config.HobbyConfig;
import cphbusiness.groupone.dao.IDAO;
import cphbusiness.groupone.model.SuperEntity;
import cphbusiness.groupone.system.Logger;
import jakarta.persistence.EntityManagerFactory;

public abstract class DAO<T extends SuperEntity<IDType>, IDType> implements IDAO<T, IDType> {

    protected static EntityManagerFactory emf;

    public DAO(){
        emf = HobbyConfig.getInstance(/* Ja den skal være tom, da vi skal kunne teste på den! */);
    }


    @Override
    public void create(T in){
        if(in != null){
            try(var em = emf.createEntityManager()){
                em.getTransaction().begin();
                em.persist(in);
                Logger.consoleLog("Successfully persisted: "+in.getLoggerInfo());
                em.getTransaction().commit();
            }catch(Exception e){
                Logger.exceptionLog(e.toString());
            }
        }
    }
    public abstract T read(IDType id);
    @Override
    public T read (IDType id, Class<T> tClass) {
        try(var em = emf.createEntityManager()){
            T found = em.find(tClass,id);
            if(found != null){
                Logger.consoleLog("Successfully read: "+found.getLoggerInfo());
                return found;
            }
        }catch(Exception e){
            Logger.exceptionLog(e.toString());
        }
        return null;
    }

    @Override
    public T update(T obj, IDType id){
        T toReturn = null;
        if(obj != null){
            try(var em = emf.createEntityManager()){
                em.getTransaction().begin();
                Object found = em.find(getGenericType(obj), id);
                if(found != null){
                    toReturn = em.merge(obj);
                    Logger.consoleLog("Successfully updated: "+toReturn.getLoggerInfo());
                    em.getTransaction().commit();
                }
            }catch(Exception e){
                Logger.exceptionLog(e.toString());
            }
        }
        return toReturn;
    }
    @Override
    public T update(T obj) {
        return update(obj, obj.getID());
    }

    public void delete(Class<T> tClass, IDType id){
        try(var em = emf.createEntityManager()){
            em.getTransaction().begin();
            T found = (T) em.find(tClass,id);
            if(found != null){
                em.remove(found);
                Logger.consoleLog("Successfully deleted: "+found.getLoggerInfo());
                em.getTransaction().commit();
            }
        }catch(Exception e){
            Logger.exceptionLog(e.toString());
        }
    }

    public void delete(T t){
        delete(getGenericType(t), t.getID());
    }

    @SuppressWarnings("unchecked")
    static <T> Class<T> getGenericType(T t){
        return (Class<T>) t.getClass();
    }


}
