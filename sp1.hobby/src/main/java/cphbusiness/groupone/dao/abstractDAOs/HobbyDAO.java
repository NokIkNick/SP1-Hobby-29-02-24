package cphbusiness.groupone.dao.abstractDAOs;

import cphbusiness.groupone.model.Hobby;

public abstract class HobbyDAO extends DAO<Hobby, Integer> {
    @Override
    public Hobby read(Integer id){
        return super.read(id, Hobby.class);
    }
}
