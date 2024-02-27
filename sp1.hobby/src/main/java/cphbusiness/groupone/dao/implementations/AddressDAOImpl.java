package cphbusiness.groupone.dao.implementations;

import cphbusiness.groupone.dao.abstractDAOs.AddressDAO;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class AddressDAOImpl extends AddressDAO {
    private static AddressDAOImpl instance;

    public static AddressDAOImpl getInstance(){
        if(instance == null){
            instance = new AddressDAOImpl();
        }
        return instance;
    }
}
