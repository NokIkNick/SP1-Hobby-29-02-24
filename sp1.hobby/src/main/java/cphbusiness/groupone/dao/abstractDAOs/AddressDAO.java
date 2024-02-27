package cphbusiness.groupone.dao.abstractDAOs;

import cphbusiness.groupone.dao.implementations.AddressDAOImpl;
import cphbusiness.groupone.model.Address;
import cphbusiness.groupone.model.Hobby;

public abstract class AddressDAO extends DAO<Address, String> {
    public Address read(String id){
        return super.read(id, Address.class);
    }
}
