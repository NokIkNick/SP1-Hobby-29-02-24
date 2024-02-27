package cphbusiness.groupone.dao.implementations;

import cphbusiness.groupone.config.HobbyConfig;
import cphbusiness.groupone.dao.abstractDAOs.UserDetailsDAO;
import cphbusiness.groupone.model.UserDetails;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;

public class UserDetailsDAOImpl extends UserDetailsDAO {



    @Override
    public UserDetails update(UserDetails obj, int id) {
        return null;
    }

    @Override
    public void delete(Class<UserDetails> userDetailsClass, int id) {

    }

    public UserDetails getTheDetail(String username){
        EntityManager em = HobbyConfig.getInstance().createEntityManager();
        em.getTransaction().begin();
        Query query = em.createQuery("select u from user_details u where u.id = :username");
        query.setParameter("username",username);
        if(query != null){
            UserDetails foundDetail = (UserDetails) query.getSingleResult();
            return foundDetail;
        }else{
            return null;
        }

    }
}
