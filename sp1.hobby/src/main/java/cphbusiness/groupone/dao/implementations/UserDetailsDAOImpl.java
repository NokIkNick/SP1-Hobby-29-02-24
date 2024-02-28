package cphbusiness.groupone.dao.implementations;

import cphbusiness.groupone.config.HobbyConfig;
import cphbusiness.groupone.dao.abstractDAOs.UserDetailsDAO;
import cphbusiness.groupone.model.Hobby;
import cphbusiness.groupone.model.UserDetails;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;

import java.util.ArrayList;
import java.util.HashMap;
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
    public HashMap<UserDetails,List<Hobby>> getUserDetailsByPhoneNumber(int phoneNumber){
        EntityManager em = HobbyConfig.getInstance().createEntityManager();
        HashMap<UserDetails,List<Hobby>> userDetailsListHashMap = new HashMap<>();
        Query query = em.createQuery("select u from user_details u where u.phone_number =:phoneNumber");
        query.setParameter("phoneNumber",phoneNumber);
        List<Hobby> hobbies = new ArrayList<>();
        Query query1 = em.createQuery("select u.hobbies from users u where u.userDetails.phone_number =:phoneNumber");
        query1.setParameter("phoneNumber",phoneNumber);
        hobbies = query1.getResultList();
        if(hobbies.size() ==0){
            return null;
        }
        userDetailsListHashMap.put((UserDetails) query,hobbies );
        return userDetailsListHashMap;
    }
}
