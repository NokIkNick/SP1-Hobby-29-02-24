package cphbusiness.groupone.dao.implementations;

import cphbusiness.groupone.dao.abstractDAOs.UserDetailsDAO;
import cphbusiness.groupone.model.UserDetails;

public class UserDetailsDAOImpl extends UserDetailsDAO {

    private static UserDetailsDAOImpl instance;

    public static UserDetailsDAOImpl getInstance(){
        if(instance == null){
            instance = new UserDetailsDAOImpl();
        }
        return instance;
    }
}
