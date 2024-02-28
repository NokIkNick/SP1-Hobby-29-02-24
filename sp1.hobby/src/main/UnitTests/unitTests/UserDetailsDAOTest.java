package unitTests;

import cphbusiness.groupone.dao.abstractDAOs.UserDetailsDAO;
import cphbusiness.groupone.dao.implementations.UserDetailsDAOImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

class UserDetailsDAOTest {
    @BeforeAll
    static void beforeAll(){
        CommonTestData.setupHobbies();
        CommonTestData.setupZips();
    }

    @Test
    void getInstance() {
        UserDetailsDAO dao = UserDetailsDAOImpl.getInstance();
        Assertions.assertNotNull(dao);
        UserDetailsDAO dao2 = UserDetailsDAOImpl.getInstance();
        Assertions.assertEquals(dao, dao2);
    }
}