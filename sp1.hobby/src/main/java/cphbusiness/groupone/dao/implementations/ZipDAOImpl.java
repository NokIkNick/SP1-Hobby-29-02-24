package cphbusiness.groupone.dao.implementations;

import cphbusiness.groupone.config.HibernateConfig;
import cphbusiness.groupone.config.HobbyConfig;
import cphbusiness.groupone.dao.IDAO;
import cphbusiness.groupone.dao.abstractDAOs.ZipDAO;
import cphbusiness.groupone.model.Zip;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

public class ZipDAOImpl extends ZipDAO {
    EntityManager em = HobbyConfig.getInstance().createEntityManager();
    private static ZipDAOImpl instance;

    public static ZipDAOImpl getInstance(){
        if(instance == null){
            instance = new ZipDAOImpl();
        }
        return instance;
    }

    public HashMap<String,Integer> listOfCitiesAndZip(){
        HashMap<String, Integer> theCityAndZip = new HashMap<>();
        List<Zip> zips = em.createQuery("select z from Zip z").getResultList();
        for(Zip zip:zips){
            theCityAndZip.put(zip.getCity_name(), zip.getZip());
        }
        return theCityAndZip;

    }


}
