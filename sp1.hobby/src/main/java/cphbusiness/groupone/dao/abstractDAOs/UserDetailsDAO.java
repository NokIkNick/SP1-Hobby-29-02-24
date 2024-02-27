package cphbusiness.groupone.dao.abstractDAOs;

import cphbusiness.groupone.model.Hobby;
import cphbusiness.groupone.model.UserDetails;

public abstract class UserDetailsDAO extends DAO<UserDetails, String> {
    public UserDetails read(String id){
        return super.read(id, UserDetails.class);
    }
}
