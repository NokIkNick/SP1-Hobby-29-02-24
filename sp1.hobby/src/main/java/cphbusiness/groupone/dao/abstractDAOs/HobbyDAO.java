package cphbusiness.groupone.dao.abstractDAOs;

import cphbusiness.groupone.model.Hobby;

public abstract class HobbyDAO extends DAO<Hobby, Integer> {
    public Hobby read(Integer id){
        return super.read(id, Hobby.class);
    }
}
