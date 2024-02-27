package cphbusiness.groupone.dao.implementations;

import cphbusiness.groupone.dao.abstractDAOs.AddressDAO;
import cphbusiness.groupone.model.Address;

public class AddressDAOImpl extends AddressDAO {
    private static AddressDAOImpl instance;

    public static AddressDAOImpl getInstance(){
        if(instance == null){
            instance = new AddressDAOImpl();
        }
        return instance;
    }



}
