package cphbusiness.groupone.dao.abstractDAOs;

import cphbusiness.groupone.model.Hobby;
import cphbusiness.groupone.model.User;

public abstract class UserDAO extends DAO<User, String> {
    public User read(String id){
        return super.read(id, User.class);
    }


}
