package cphbusiness.groupone;

import cphbusiness.groupone.config.HobbyConfig;
<<<<<<< Updated upstream
import cphbusiness.groupone.dao.implementations.*;
=======
import cphbusiness.groupone.dao.IDAO;
import cphbusiness.groupone.dao.implementations.HobbyDAOImpl;
import cphbusiness.groupone.dao.implementations.UserDAOImpl;
>>>>>>> Stashed changes
import cphbusiness.groupone.model.*;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;

<<<<<<< Updated upstream
import java.util.ArrayList;
import java.util.List;
=======
import java.util.List;
import java.util.Set;
>>>>>>> Stashed changes

public class Main {
    public static void main(String[] args) {
        EntityManagerFactory emf = HobbyConfig.getInstance(false);

<<<<<<< Updated upstream
        UserDAOImpl userDAO = UserDAOImpl.getInstance();
        HobbyDAOImpl hobbyDAO = HobbyDAOImpl.getInstance();
        AddressDAOImpl addressDAO = AddressDAOImpl.getInstance();
        ZipDAOImpl zipDAO = ZipDAOImpl.getInstance();
        UserDetailsDAOImpl userDetailsDAO = new UserDetailsDAOImpl();

        List<User> users = userDAO.usersFromGivenCity("Kongerslev");

            /*Hobby hobby1 = hobbyDAO.read(1, Hobby.class);
            Zip zip1 = zipDAO.read(9293, Zip.class);
            boolean wasFound = true;
            User user1 = userDAO.read("Christian1234", User.class);
            if (user1 == null) {
                wasFound = false;
                user1 = new User("Christian1234", "1234", false);
            }

            Address address1 = new Address("Lyngby Hovedgade 2");

            UserDetails user1Details = new UserDetails();
            user1Details.setAge(24);
            user1Details.setGender(Gender.MALE);
            user1Details.setPhone_number(4558879);

            address1.setZip(zip1);
            user1Details.addAddress(address1);
            user1.setUserDetails(user1Details);
            user1.addHobby(hobby1);

            //em.persist(address1);
            if (!wasFound)
                userDAO.create(user1);
           // em.getTransaction().commit();

            /* Remember to close. */
            //emf.close();
            //emf.close();

        //}
=======
        
>>>>>>> Stashed changes
    }
}