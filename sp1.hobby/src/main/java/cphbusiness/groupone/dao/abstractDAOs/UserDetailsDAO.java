package cphbusiness.groupone.dao.abstractDAOs;

import cphbusiness.groupone.model.UserDetails;


import java.util.List;

public abstract class UserDetailsDAO extends DAO<UserDetails, String> {
    public UserDetails read(String id){
        return super.read(id, UserDetails.class);
    }
    public abstract List<Integer> getPhoneNumbersFromGivenPerson(String username);
    public abstract UserDetails getTheDetail(String username);
}
