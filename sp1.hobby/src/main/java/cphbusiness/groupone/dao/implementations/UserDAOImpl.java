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


    @Override
    public User read(int id, User in) {
        return null;
    }

    @Override
    public User update(User obj, int id) {
        return null;
    }

    @Override
    public void delete(Class<User> userClass, int id) {

    }
}