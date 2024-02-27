package cphbusiness.groupone.dao.abstractDAOs;

import cphbusiness.groupone.model.UserDetails;

import java.util.List;

public abstract class UserDetailsDAO extends DAO<UserDetails> {

    public abstract List<Integer> getPhoneNumbersFromGivenPerson(String username);

}
