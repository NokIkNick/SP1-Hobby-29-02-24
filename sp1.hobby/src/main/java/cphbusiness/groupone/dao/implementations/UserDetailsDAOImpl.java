package cphbusiness.groupone.dao.implementations;

import cphbusiness.groupone.config.HibernateConfig;
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

    public List<Integer> getPhoneNumbersFromGivenPerson(String username){
        EntityManager em = HobbyConfig.getInstance().createEntityManager();
        Query query = em.createQuery("select u.userDetails.phone_number from users u where username = :username");
        query.setParameter("username",username);
        List<Integer> phoneNumbers = query.getResultList();
        return phoneNumbers;
    }


}
