package cphbusiness.groupone.dao.abstractDAOs;

import cphbusiness.groupone.model.Hobby;

import java.util.Map;

public abstract class HobbyDAO extends DAO<Hobby> {
    // US - 5
    public abstract int getCountOfPeopleByHobbyId(int id);
    // US - 6
    public abstract Map<Hobby,Integer> getHobbyWithCountOfInterestedPeople();
}
