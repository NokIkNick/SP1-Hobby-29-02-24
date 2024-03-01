package cphbusiness.groupone;

import cphbusiness.groupone.config.HobbyConfig;

import cphbusiness.groupone.dao.implementations.*;
import cphbusiness.groupone.model.*;
import jakarta.persistence.EntityManagerFactory;

public class Main {
    public static void main(String[] args) {
        EntityManagerFactory emf = HobbyConfig.getInstance(false);

        UserDAOImpl userDAO = UserDAOImpl.getInstance();
        HobbyDAOImpl hobbyDAO = HobbyDAOImpl.getInstance();
        AddressDAOImpl addressDAO = AddressDAOImpl.getInstance();
        ZipDAOImpl zipDAO = ZipDAOImpl.getInstance();
        UserDetailsDAOImpl userDetailsDAO = new UserDetailsDAOImpl();

        userDAO.create(new User("testmand","whatAnAwesomePassword",false));

        //userDetailsDAO.changeGender(Gender.FEMALE,"Christian1234");


        /*List<User> users = userDAO.usersFromGivenCity("Kongerslev");

        List<Integer> phonenumbers = userDetailsDAO.getPhoneNumbersFromGivenPerson("Christian1234");
        for(Integer i : phonenumbers){
            System.out.println(i);
        }
        
            /*Hobby hobby1 = hobbyDAO.read(1, Hobby.class);
            Zip zip1 = zipDAO.read(9293, Zip.class);
            boolean wasFound = true;
            User user1 = userDAO.read("Christian1234", User.class);
            if (user1 == null) {
                wasFound = false;
                user1 = new User("Christian1234", "1234", false);
            }
        UserDetailsDAO userDetailsDAO = UserDetailsDAOImpl.getInstance();

    /*    // US - 6
        Map<Hobby,Integer> result = hobbyDAO.getHobbyWithCountOfInterestedPeople();
       for (Map.Entry<Hobby,Integer> m : result.entrySet()){
           Hobby hobby = m.getKey();
           Integer interested = m.getValue();
           if(interested > 0) {
               System.out.println("Hobby " + hobby.getName() + " has " + interested + " interested people");
           }
       }*/
       /////////

        /*
        System.out.println(hobbyDAO.getCountOfPeopleByHobbyId(1));
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
        /*Address address1 = user1Details.getAddress();
        address1.setStreet("Lyngby Hovedgade 2");

        address1.setZip(zip1);
        //user1Details.setAddress(address1);
        user1.setUserDetails(user1Details);

        Hobby hobby1 = hobbyDAO.read(1);
        user1.addHobby(hobby1);


        if(!wasFound)
            userDAO.create(user1);
        else
            userDAO.update(user1)
            
        User user2 = userDAO.read("Christian1234");
        if(user2 == null){
            user2 = new User("Christian1234", "1234", false);
            userDAO.create(user2);
        }
        userDAO.delete(user2);
        */
        //userDAO.create(new User("Coolguy","coolpassword",false));

        /*User testUser = userDAO.read("Coolguy");
        if(testUser == null){
            testUser = new User("Coolguy", "stop", false);
            userDAO.create(testUser);
        }
        Hobby hobby2 = hobbyDAO.read(1);
        UserDetails userDetails1 = testUser.getUserDetails();
        userDetails1.setAge(22);
        userDetails1.setGender(Gender.MALE);

        Address testAddress = userDetails1.getAddress();
        Zip testZip = testAddress.getZip();

        if (testZip == null) {
            testZip = zipDAO.read(2700);
        }


        testAddress.setZip(testZip);
        testAddress.setStreet("Bellahøjvej 31");
        testAddress.setUserDetails(userDetails1);
        userDetails1.setPhone_number(42212345);
        
        testUser.addHobby(hobby2);
        userDAO.update(testUser);
        List<UserUserDetailsDTO> usersByHobbyList = userDAO.getUsersByHobby(hobby2);
        usersByHobbyList.forEach(System.out::println);


        Map<Hobby, Long> popularHobbies = hobbyDAO.getHobbiesByPopularity(1,10);
        for(Map.Entry<Hobby, Long> entry : popularHobbies.entrySet()){
            System.out.println(entry.getKey().getName() + " Number of participants:"+ entry.getValue());
        }
        // US - 10
        Map<User,Integer> result1 = userDAO.getUsersAndHobbyCountByAddress(testAddress);
        for (Map.Entry<User,Integer> m : result1.entrySet()){
            User user = m.getKey();
            Integer hobbyCount = m.getValue();
            if(hobbyCount > 0) {
                System.out.println("User " + user.getUsername() + " has " + hobbyCount + " hobbies");
            }
        }
        
        /* Remember to close. */
        emf.close();
    }
}