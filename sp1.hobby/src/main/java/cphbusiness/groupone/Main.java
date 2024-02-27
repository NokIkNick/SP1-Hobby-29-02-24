package cphbusiness.groupone;

import cphbusiness.groupone.config.HibernateConfig;
import cphbusiness.groupone.config.HobbyConfig;
import cphbusiness.groupone.dao.implementations.AddressDAOImpl;
import cphbusiness.groupone.dao.implementations.HobbyDAOImpl;
import cphbusiness.groupone.dao.implementations.UserDAOImpl;
import cphbusiness.groupone.dao.implementations.ZipDAOImpl;
import cphbusiness.groupone.model.*;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;

import java.util.Set;

public class Main {
    public static void main(String[] args) {
        EntityManagerFactory emf = HobbyConfig.getInstance(false);
        UserDAOImpl userDAO = UserDAOImpl.getInstance();
        HobbyDAOImpl hobbyDAO = HobbyDAOImpl.getInstance();
        AddressDAOImpl addressDAO = AddressDAOImpl.getInstance();
        ZipDAOImpl zipDAO = ZipDAOImpl.getInstance();

       /* try(EntityManager em = emf.createEntityManager()) {
            em.getTransaction().begin();*/
            Hobby hobby1 = hobbyDAO.read(1, Hobby.class);
            Zip zip1 = zipDAO.read(9293, Zip.class);

            User user1 = new User("Christian1234", "1234", false);
            //em.persist(user1);
            Address address1 = new Address("Lyngby Hovedgade 2");
            //em.persist(address1);
            UserDetails user1Details = new UserDetails();
            user1Details.setAge(24);
            user1Details.setGender(Gender.MALE);
            user1Details.setPhone_number(4558879);
            //em.persist(user1Details);

            address1.setZip(zip1);
            user1Details.addAddress(address1);
            user1.addUserDetails(user1Details);


            //em.persist(address1);
            user1.addUserDetails(user1Details);
            user1.addHobby(hobby1);

            userDAO.create(user1);
            /*em.getTransaction().commit();*/

    }
}