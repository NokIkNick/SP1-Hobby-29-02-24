package cphbusiness.groupone.dao.implementations;

import cphbusiness.groupone.dao.abstractDAOs.UserDetailsDAO;

public class UserDetailsDAOImpl extends UserDetailsDAO {

    private static UserDetailsDAOImpl instance;

    public static UserDetailsDAOImpl getInstance(){
        if(instance == null){
            instance = new UserDetailsDAOImpl();
        }
        return instance;
    }
}
