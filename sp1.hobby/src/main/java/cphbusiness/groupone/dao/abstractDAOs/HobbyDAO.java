package cphbusiness.groupone.dao.abstractDAOs;

import cphbusiness.groupone.model.Hobby;

import java.util.Map;

public abstract class HobbyDAO extends DAO<Hobby> {
    public abstract Map<Hobby,Integer> getHobbyWithCountOfInterestedPeople();
}
