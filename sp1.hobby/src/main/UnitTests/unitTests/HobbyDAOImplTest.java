package unitTests;

import cphbusiness.groupone.dao.abstractDAOs.HobbyDAO;
import cphbusiness.groupone.dao.abstractDAOs.UserDetailsDAO;
import cphbusiness.groupone.dao.implementations.HobbyDAOImpl;
import cphbusiness.groupone.dao.implementations.UserDetailsDAOImpl;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.testng.AssertJUnit.assertEquals;
import static org.testng.AssertJUnit.assertNotNull;

class HobbyDAOImplTest {
    private static HobbyDAO dao;

    @BeforeAll
    static void beforeAll(){
        CommonTestData.setupHobbies();
        CommonTestData.setupZips();
        dao = HobbyDAOImpl.getInstance();
    }

    @Test
    void getInstance() {
        HobbyDAO dao = HobbyDAOImpl.getInstance();
        assertNotNull(dao);
        HobbyDAO dao2 = HobbyDAOImpl.getInstance();
        assertEquals(dao, dao2);
    }
}