package cphbusiness.groupone.dao.abstractDAOs;

import cphbusiness.groupone.model.Address;
import cphbusiness.groupone.model.User;

import java.util.Map;

public abstract class UserDAO extends DAO<User, String> {
    public User read(String id){
        return super.read(id, User.class);
    }
    public abstract Map<User,Integer> getUsersAndHobbyCountByAddress(Address address);
}
