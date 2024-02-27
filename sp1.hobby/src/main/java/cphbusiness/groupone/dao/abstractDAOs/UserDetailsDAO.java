package cphbusiness.groupone.dao.abstractDAOs;

import cphbusiness.groupone.model.UserDetails;

import java.util.List;

public abstract class UserDetailsDAO extends DAO<UserDetails> {

    public abstract List<UserDetails> userDetailByPhoneNumber(int phonenumber);

}
