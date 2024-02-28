package unitTests;

import cphbusiness.groupone.dao.abstractDAOs.HobbyDAO;
import cphbusiness.groupone.dao.implementations.HobbyDAOImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

@SuppressWarnings("FieldCanBeLocal")
class HobbyDAOImplTest {
    private static HobbyDAO dao;

    @BeforeAll
    static void beforeAll() {
        CommonTestData.setupHobbies();
        CommonTestData.setupZips();
        dao = HobbyDAOImpl.getInstance();
    }

    @Test
    void getInstance() {
        HobbyDAO dao = HobbyDAOImpl.getInstance();
        Assertions.assertNotNull(dao);
        HobbyDAO dao2 = HobbyDAOImpl.getInstance();
        Assertions.assertEquals(dao, dao2);
    }
}