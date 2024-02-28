package unitTests;

import cphbusiness.groupone.dao.abstractDAOs.UserDetailsDAO;
import cphbusiness.groupone.dao.implementations.UserDetailsDAOImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class UserDAOImplTest {


    @Test
    void getInstance() {
        UserDetailsDAO dao = UserDetailsDAOImpl.getInstance();
        Assertions.assertNotNull(dao);
        UserDetailsDAO dao2 = UserDetailsDAOImpl.getInstance();
        Assertions.assertEquals(dao, dao2);
    }
}