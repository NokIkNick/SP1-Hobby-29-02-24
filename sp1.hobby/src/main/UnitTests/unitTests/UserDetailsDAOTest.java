package unitTests;

import cphbusiness.groupone.dao.abstractDAOs.UserDetailsDAO;
import cphbusiness.groupone.dao.implementations.UserDetailsDAOImpl;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.testng.AssertJUnit.assertEquals;
import static org.testng.AssertJUnit.assertNotNull;

class UserDetailsDAOTest {
    private static UserDetailsDAO dao;
    @BeforeAll
    static void beforeAll(){
        CommonTestData.setupHobbies();
        CommonTestData.setupZips();
        dao = UserDetailsDAOImpl.getInstance();
    }

    @Test
    void getInstance() {
        UserDetailsDAO dao = UserDetailsDAOImpl.getInstance();
        assertNotNull(dao);
        UserDetailsDAO dao2 = UserDetailsDAOImpl.getInstance();
        assertEquals(dao, dao2);
    }
}