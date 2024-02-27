package cphbusiness.groupone.dao.abstractDAOs;

import cphbusiness.groupone.model.UserDetails;

public abstract class UserDetailsDAO extends DAO<UserDetails> {

    public abstract UserDetails getTheDetail(String username);

}
