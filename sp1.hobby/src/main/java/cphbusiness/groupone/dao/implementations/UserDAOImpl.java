package cphbusiness.groupone.dao.implementations;

import cphbusiness.groupone.config.HibernateConfig;
import cphbusiness.groupone.config.HobbyConfig;
import cphbusiness.groupone.dao.abstractDAOs.UserDAO;
import cphbusiness.groupone.model.User;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Query;

import java.util.List;

public class UserDAOImpl extends UserDAO {

    private static UserDAOImpl instance;

    public static UserDAOImpl getInstance(){
        if(instance == null){
            instance = new UserDAOImpl();
        }
        return instance;
    }

    public UserDAOImpl(){
        super();
    }



    @Override
    public User update(User obj, int id) {
        return null;
    }

    @Override
    public void delete(Class<User> userClass, int id) {

    }

    public List<User> usersFromGivenCity(String city){
        EntityManager entityManager = HobbyConfig.getInstance().createEntityManager();
        //entityManager.getTransaction().begin();
        Query query = entityManager.createQuery("select u from users u where u.userDetails.address.zip.city_name = :city");
        query.setParameter("city",city);
        List<User> users = query.getResultList();
        return users;
    }


}
