package cphbusiness.groupone.dao.implementations;

import cphbusiness.groupone.config.HobbyConfig;
import cphbusiness.groupone.dao.abstractDAOs.UserDetailsDAO;
import cphbusiness.groupone.exceptions.NoResultException;
import cphbusiness.groupone.model.Gender;
import cphbusiness.groupone.model.User;
import cphbusiness.groupone.model.UserDetails;
import cphbusiness.groupone.system.ExceptionLogger;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import java.util.List;


public class UserDetailsDAOImpl extends UserDetailsDAO {

    private static UserDetailsDAOImpl instance;

    public static UserDetailsDAOImpl getInstance(){
        if(instance == null){
            instance = new UserDetailsDAOImpl();
        }
        return instance;
    }

    public List<Integer> getPhoneNumbersFromGivenPerson(String username){
        try(EntityManager em = HobbyConfig.getInstance().createEntityManager()){
            Query query = em.createQuery("select u.userDetails.phone_number from users u where username = :username");
            query.setParameter("username",username);
            List<Integer> phoneNumbers = query.getResultList();
            return phoneNumbers;
        }

    }

    public UserDetails getTheDetail(String username){
        try(EntityManager em = HobbyConfig.getInstance().createEntityManager()){
            Query query = em.createQuery("select u from user_details u where u.id = :username");
            query.setParameter("username",username);
            if(query != null){
                UserDetails foundDetail = (UserDetails) query.getSingleResult();
                return foundDetail;
            }else{
                ExceptionLogger.log(new NoResultException("No results found").toString());
                return null;
            }
         }
    }

    public void changeGender(Enum gender,String username){
        try(EntityManager em = HobbyConfig.getInstance().createEntityManager()){
            em.getTransaction().begin();
            Query query = em.createQuery("select u from user_details u where u.id =:username");
            query.setParameter("username",username);
            UserDetails userDetail = (UserDetails) query.getSingleResult();
            userDetail.setGender((Gender) gender);
            em.getTransaction().commit();

        }


    }

}
