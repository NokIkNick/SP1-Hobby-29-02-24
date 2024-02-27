package cphbusiness.groupone.dao.implementations;

import cphbusiness.groupone.dao.abstractDAOs.UserDAO;
import cphbusiness.groupone.model.User;

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
}
