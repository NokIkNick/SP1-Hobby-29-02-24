package cphbusiness.groupone;

import cphbusiness.groupone.config.HobbyConfig;
import cphbusiness.groupone.dao.implementations.AddressDAOImpl;
import cphbusiness.groupone.dao.implementations.HobbyDAOImpl;
import cphbusiness.groupone.dao.implementations.UserDAOImpl;
import cphbusiness.groupone.dao.implementations.ZipDAOImpl;
import cphbusiness.groupone.model.*;
import jakarta.persistence.EntityManagerFactory;
import org.hibernate.Hibernate;

public class Main {
    public static void main(String[] args) {
        EntityManagerFactory emf = HobbyConfig.getInstance(false);
        UserDAOImpl userDAO = UserDAOImpl.getInstance();
        HobbyDAOImpl hobbyDAO = HobbyDAOImpl.getInstance();
        AddressDAOImpl addressDAO = AddressDAOImpl.getInstance();
        ZipDAOImpl zipDAO = ZipDAOImpl.getInstance();


        boolean wasFound = true;
        User user1 = userDAO.read("Christian12345");
        if(user1 == null){
            wasFound = false;
            user1 = new User("Christian12345", "1234", false);
        }

        //// if confused look at documentation of Address below
        UserDetails user1Details = user1.getUserDetails();
        user1Details.setAge(24);
        user1Details.setGender(Gender.MALE);
        user1Details.setPhone_number(4558879);

        Zip zip1 = zipDAO.read(9293);

        //// Wrong way of doing this: (due to working with JPA)
        /*
        Address address1 = new Address("Lyngby Hovedgade 2");*/

        //// The Right way: (We let UserDetails create a new one if it doesn't already exist)
        Address address1 = user1Details.getAddress();
        address1.setStreet("Lyngby Hovedgade 2");

        address1.setZip(zip1);
        user1Details.setAddress(address1);
        user1.setUserDetails(user1Details);

        Hobby hobby1 = hobbyDAO.read(1);
        user1.addHobby(hobby1);


        if(!wasFound)
            userDAO.create(user1);
        else
            userDAO.update(user1);

        /* Remember to close. */
        emf.close();
    }
}