package cphbusiness.groupone.dao.abstractDAOs;

import cphbusiness.groupone.model.Zip;

public abstract class ZipDAO extends DAO<Zip, Integer> {
    @Override
    public Zip read(Integer id){
        return super.read(id, Zip.class);
    }
}
