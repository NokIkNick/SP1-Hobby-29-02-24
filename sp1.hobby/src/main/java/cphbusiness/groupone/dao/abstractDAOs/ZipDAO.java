package cphbusiness.groupone.dao.abstractDAOs;

import cphbusiness.groupone.model.Hobby;
import cphbusiness.groupone.model.Zip;

public abstract class ZipDAO extends DAO<Zip, Integer> {
    public Zip read(int id){
        return super.read(id, Zip.class);
    }

}
