package cphbusiness.groupone.dao.implementations;

import cphbusiness.groupone.dao.abstractDAOs.HobbyDAO;
import cphbusiness.groupone.model.Hobby;

import java.util.Map;

public class HobbyDAOImpl extends HobbyDAO {

    private static HobbyDAOImpl instance;

    public static HobbyDAOImpl getInstance(){
        if(instance == null){
            instance = new HobbyDAOImpl();
        }
        return instance;
    }

    public HobbyDAOImpl(){
        super();
    }

    @Override
    public Map<Hobby, Integer> getHobbyWithCountOfInterestedPeople() {
        return null;
    }
}
