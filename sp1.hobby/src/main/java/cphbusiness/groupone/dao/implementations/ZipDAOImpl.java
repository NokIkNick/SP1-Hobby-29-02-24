package cphbusiness.groupone.dao.implementations;

import cphbusiness.groupone.dao.abstractDAOs.ZipDAO;

public class ZipDAOImpl extends ZipDAO {

    private static ZipDAOImpl instance;

    public static ZipDAOImpl getInstance(){
        if(instance == null){
            instance = new ZipDAOImpl();
        }
        return instance;
    }

}
