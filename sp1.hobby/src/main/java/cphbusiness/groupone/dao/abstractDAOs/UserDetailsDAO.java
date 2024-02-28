package cphbusiness.groupone.dao.abstractDAOs;

import cphbusiness.groupone.model.Hobby;
import cphbusiness.groupone.model.UserDetails;

import java.util.HashMap;
import java.util.List;

public abstract class UserDetailsDAO extends DAO<UserDetails> {

    public abstract HashMap<UserDetails,List<Hobby>> getUserDetailsByPhoneNumber(int phoneNumber);

}
