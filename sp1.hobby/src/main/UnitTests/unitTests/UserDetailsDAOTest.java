package unitTests;

import cphbusiness.groupone.dao.abstractDAOs.UserDetailsDAO;
import cphbusiness.groupone.dao.implementations.UserDetailsDAOImpl;
import org.junit.jupiter.api.Test;

import static org.testng.AssertJUnit.assertEquals;
import static org.testng.AssertJUnit.assertNotNull;

class UserDetailsDAOTest {

    @Test
    void update() {
    }

    @Test
    void delete() {
    }

    @Test
    void getInstance() {
        UserDetailsDAO dao = UserDetailsDAOImpl.getInstance();
        assertNotNull(dao);
        UserDetailsDAO dao2 = UserDetailsDAOImpl.getInstance();
        assertEquals(dao, dao2);
    }
}