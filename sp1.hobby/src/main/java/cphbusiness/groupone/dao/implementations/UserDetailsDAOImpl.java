package cphbusiness.groupone.dao.implementations;

import cphbusiness.groupone.config.HobbyConfig;
import cphbusiness.groupone.dao.abstractDAOs.UserDetailsDAO;
import cphbusiness.groupone.model.UserDetails;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;

import java.util.List;

public class UserDetailsDAOImpl extends UserDetailsDAO {



    @Override
    public UserDetails update(UserDetails obj, int id) {
        return null;
    }

    @Override
    public void delete(Class<UserDetails> userDetailsClass, int id) {

    }



    @Override
    public List<UserDetails> userDetailByPhoneNumber(int phonenumber) {
        EntityManager em = HobbyConfig.getInstance().createEntityManager();
        em.getTransaction().begin();
        Query query = em.createQuery("select userDetails from users u where u.userDetails.phone_number = :phonenumber");
        query.setParameter("phonenumber",phonenumber);
        List<UserDetails> userDetails = query.getResultList();
        return userDetails;
    }
}
