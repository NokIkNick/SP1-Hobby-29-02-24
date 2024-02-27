package unitTests;

import cphbusiness.groupone.config.HobbyConfig;
import cphbusiness.groupone.dao.abstractDAOs.UserDetailsDAO;
import cphbusiness.groupone.dao.abstractDAOs.ZipDAO;
import cphbusiness.groupone.dao.implementations.ZipDAOImpl;
import cphbusiness.groupone.model.Zip;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.testng.AssertJUnit.assertEquals;
import static org.testng.AssertJUnit.assertNotNull;

/**
 * Also tests DAO stuff
 */
class ZipDAOTest {
    private static ZipDAO dao;
    @BeforeAll
    static void beforeAll(){
        CommonTestData.setupHobbies();
        CommonTestData.setupZips();
        dao = ZipDAOImpl.getInstance();
    }

    @Test
    void getInstance() {
        ZipDAO dao = ZipDAOImpl.getInstance();
        assertNotNull(dao);
        ZipDAO dao2 = ZipDAOImpl.getInstance();
        assertEquals(dao, dao2);
    }

    @Test
    void create() {
        Zip z = new Zip();
        CommonTestData.popZip(z, 999511, "Harry", "Power", "Potter");
        try(EntityManager em = HobbyConfig.getInstance().createEntityManager()){
            assertNull(em.find(Zip.class, 999511));
            dao.create(z);
            assertNotNull(em.find(Zip.class, 999511));
            dao.delete(z);
        }
    }

    @Test
    void update() {
        Zip z = new Zip();
        CommonTestData.popZip(z, 999511, "Harry", "Power", "Potter");
        try(EntityManager em = HobbyConfig.getInstance().createEntityManager()){
            dao.create(z);
            assertTrue(em.find(Zip.class, z.getZip()).getCity_name() == "Harry");
            z.setCity_name("Spike");
            assertTrue(em.find(Zip.class, z.getZip()).getCity_name() == "Harry");
            dao.update(z);
            assertTrue(em.find(Zip.class, z.getZip()).getCity_name() == "Spike");
            dao.delete(z);
        }
    }

    @Test
    void delete() {
        Zip z = new Zip();
        CommonTestData.popZip(z, 999511, "Harry", "Power", "Potter");
        try(EntityManager em = HobbyConfig.getInstance().createEntityManager()){
            assertNull(em.find(Zip.class, 999511));
            dao.create(z);
            assertNotNull(em.find(Zip.class, 999511));
            dao.delete(z);
            assertNull(em.find(Zip.class, 999511));
        }
    }

    @Test
    void read() {
        assertNotNull(dao.read(999999));
    }
}