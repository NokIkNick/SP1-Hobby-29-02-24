package unitTests;

import cphbusiness.groupone.config.HobbyConfig;
import cphbusiness.groupone.dao.abstractDAOs.ZipDAO;
import cphbusiness.groupone.dao.implementations.ZipDAOImpl;
import cphbusiness.groupone.model.Zip;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.Assert.*;

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
        Assertions.assertNotNull(dao);
        ZipDAO dao2 = ZipDAOImpl.getInstance();
        Assertions.assertEquals(dao, dao2);
    }

    @Test
    void create() {
        Zip z = new Zip();
        CommonTestData.popZip(z, 999511, "Harry", "Power", "Potter");
        try(EntityManager em = HobbyConfig.getInstance().createEntityManager()){
            Assertions.assertNull(em.find(Zip.class, 999511));
            dao.create(z);
            Assertions.assertNotNull(em.find(Zip.class, 999511));
            dao.delete(z);
        }
    }

    @Test
    void update() {
        Zip z = new Zip();
        CommonTestData.popZip(z, 999511, "Harry", "Power", "Potter");
        try(EntityManager em = HobbyConfig.getInstance().createEntityManager()){
            dao.create(z);
            Assertions.assertEquals("Harry", em.find(Zip.class, z.getZip()).getCity_name());
            z.setCity_name("Spike");
            Assertions.assertSame("Harry", em.find(Zip.class, z.getZip()).getCity_name());
            dao.update(z);
            Assertions.assertSame("Spike", em.find(Zip.class, z.getZip()).getCity_name());
            dao.delete(z);
        }
    }

    @Test
    void delete() {
        Zip z = new Zip();
        CommonTestData.popZip(z, 999511, "Harry", "Power", "Potter");
        try(EntityManager em = HobbyConfig.getInstance().createEntityManager()){
            Assertions.assertNull(em.find(Zip.class, 999511));
            dao.create(z);
            Assertions.assertNotNull(em.find(Zip.class, 999511));
            dao.delete(z);
            Assertions.assertNull(em.find(Zip.class, 999511));
        }
    }

    @Test
    void read() {
        Assertions.assertNotNull(dao.read(999999));
    }
}