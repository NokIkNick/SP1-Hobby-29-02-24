package cphbusiness.groupone.dao.abstractDAOs;

import cphbusiness.groupone.model.Address;

public abstract class AddressDAO extends DAO<Address, String> {
    @Override
    public Address read(String id){
        return super.read(id, Address.class);
    }
}
