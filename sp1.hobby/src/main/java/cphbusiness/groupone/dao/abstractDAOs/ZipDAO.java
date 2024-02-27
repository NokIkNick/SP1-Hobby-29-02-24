package cphbusiness.groupone.dao.abstractDAOs;

import cphbusiness.groupone.model.Zip;

import java.util.HashMap;

public abstract class ZipDAO extends DAO<Zip> {
    public abstract HashMap<String,Integer> listOfCitiesAndZip();
}
