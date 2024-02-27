package unitTests;

import cphbusiness.groupone.dao.abstractDAOs.UserDetailsDAO;
import cphbusiness.groupone.dao.implementations.UserDetailsDAOImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.testng.AssertJUnit.assertEquals;
import static org.testng.AssertJUnit.assertNotNull;

class UserDetailsDAOTest {
    @SuppressWarnings("FieldCanBeLocal")
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
        Assertions.assertNotNull(dao);
        UserDetailsDAO dao2 = UserDetailsDAOImpl.getInstance();
        Assertions.assertEquals(dao, dao2);
    }
}