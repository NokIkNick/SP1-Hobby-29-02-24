package unitTests;

import cphbusiness.groupone.dao.abstractDAOs.HobbyDAO;
import cphbusiness.groupone.dao.abstractDAOs.UserDAO;
import cphbusiness.groupone.dao.implementations.HobbyDAOImpl;
import cphbusiness.groupone.dao.implementations.UserDAOImpl;
import cphbusiness.groupone.model.User;
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
        User u1 = new User("Christian", "barnaby", false);
        User u2 = new User("Patrick", "barnaby", false);
        UserDAO userdao = UserDAOImpl.getInstance();
        userdao.create(u1);
        u1.addHobby(dao.read(CommonTestData.hobby1id));
        u1.addHobbyToInterests(dao.read(CommonTestData.hobby1id));
        userdao.update(u1);
        userdao.create(u2);
        u2.addHobbyToInterests(dao.read(CommonTestData.hobby1id));
        userdao.update(u2);
    }

    @Test
    void getInstance() {
        HobbyDAO dao = HobbyDAOImpl.getInstance();
        Assertions.assertNotNull(dao);
        HobbyDAO dao2 = HobbyDAOImpl.getInstance();
        Assertions.assertEquals(dao, dao2);
    }

    @Test
    void getCountOfPeopleByHobbyId() {
        Assertions.assertEquals(1, dao.getCountOfPeopleByHobbyId(CommonTestData.hobby1id));
    }

    @Test
    void getHobbyWithCountOfInterestedPeople() {
        int i = dao.getHobbyWithCountOfInterestedPeople().entrySet().iterator().next().getValue();
        Assertions.assertEquals(2, i);
    }

    @Test
    void getHobbiesByPopularity() {
        Assertions.assertEquals(CommonTestData.hobby1id, dao.getHobbiesByPopularity(1,1).entrySet().iterator().next().getKey().getID());
    }
}