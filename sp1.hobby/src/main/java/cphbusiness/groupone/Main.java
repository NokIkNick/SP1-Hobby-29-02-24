package cphbusiness.groupone;

import cphbusiness.groupone.config.HibernateConfig;
import cphbusiness.groupone.config.HobbyConfig;
import cphbusiness.groupone.dao.implementations.AddressDAOImpl;
import cphbusiness.groupone.dao.implementations.HobbyDAOImpl;
import cphbusiness.groupone.dao.implementations.UserDAOImpl;
import cphbusiness.groupone.dao.implementations.ZipDAOImpl;
import cphbusiness.groupone.model.*;
import jakarta.persistence.EntityManagerFactory;

import java.util.Set;

public class Main {
    public static void main(String[] args) {
        EntityManagerFactory emf = HobbyConfig.getInstance(false);
        ZipDAOImpl zipDAO = new ZipDAOImpl();
        System.out.println(zipDAO.listOfCitiesAndZip());
    }


}