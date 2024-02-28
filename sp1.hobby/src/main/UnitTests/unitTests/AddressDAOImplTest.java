package unitTests;

import cphbusiness.groupone.dao.implementations.AddressDAOImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class AddressDAOImplTest {

    @Test
    void getInstance() {
        AddressDAOImpl dao = AddressDAOImpl.getInstance();
        Assertions.assertNotNull(dao);
        AddressDAOImpl dao2 = AddressDAOImpl.getInstance();
        Assertions.assertEquals(dao, dao2);
    }
}