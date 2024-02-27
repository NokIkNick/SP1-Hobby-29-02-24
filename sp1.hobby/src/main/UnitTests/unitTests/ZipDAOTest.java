package unitTests;

import cphbusiness.groupone.config.HobbyConfig;
import cphbusiness.groupone.dao.abstractDAOs.ZipDAO;
import cphbusiness.groupone.dao.implementations.ZipDAOImpl;
import jakarta.persistence.EntityManagerFactory;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.testng.AssertJUnit.assertEquals;
import static org.testng.AssertJUnit.assertNotNull;

class ZipDAOTest {
    static EntityManagerFactory emf;
    @BeforeAll
    static void beforeAll(){
        emf = HobbyConfig.getInstance(true);
        CommonTestData.setupHobbies();
    }

    @Test
    void getInstance() {
        ZipDAO dao = ZipDAOImpl.getInstance();
        assertNotNull(dao);
        ZipDAO dao2 = ZipDAOImpl.getInstance();
        assertEquals(dao, dao2);
    }
}