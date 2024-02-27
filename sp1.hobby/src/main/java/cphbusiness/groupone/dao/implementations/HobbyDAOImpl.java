package cphbusiness.groupone.dao.implementations;

import cphbusiness.groupone.dao.abstractDAOs.HobbyDAO;
import cphbusiness.groupone.model.Hobby;

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

}
