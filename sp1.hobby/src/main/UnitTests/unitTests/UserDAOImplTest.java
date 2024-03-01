package unitTests;

import cphbusiness.groupone.config.HobbyConfig;
import cphbusiness.groupone.dao.abstractDAOs.HobbyDAO;
import cphbusiness.groupone.dao.abstractDAOs.UserDAO;
import cphbusiness.groupone.dao.abstractDAOs.UserDetailsDAO;
import cphbusiness.groupone.dao.abstractDAOs.ZipDAO;
import cphbusiness.groupone.dao.implementations.HobbyDAOImpl;
import cphbusiness.groupone.dao.implementations.UserDAOImpl;
import cphbusiness.groupone.dao.implementations.UserDetailsDAOImpl;
import cphbusiness.groupone.dao.implementations.ZipDAOImpl;
import cphbusiness.groupone.dto.UserUserDetailsDTO;
import cphbusiness.groupone.model.User;
import cphbusiness.groupone.model.UserDetails;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;

class UserDAOImplTest {
    private static UserDAO dao;

    @BeforeAll
    static void beforeAll(){
        CommonTestData.setupZips();
        CommonTestData.setupAddresses();
        CommonTestData.setupHobbies();
        dao = UserDAOImpl.getInstance();
    }

    @Test
    void getInstance() {
        UserDAO dao1 = UserDAOImpl.getInstance();
        Assertions.assertNotNull(dao1);
        UserDAO dao2 = UserDAOImpl.getInstance();
        Assertions.assertEquals(dao1, dao2);
    }

    @Test
    void getUsersAndHobbyCountByAddress() {
        Map<User, Integer> map = dao.getUsersAndHobbyCountByAddress(dao.read("test user 1").getUserDetails().getAddress());
        assertNotNull(map);
        assertEquals("test user Address", map.entrySet().iterator().next().getKey().getID());
    }

    @Test
    void getUsersByHobby() {
        HobbyDAO hDAO = HobbyDAOImpl.getInstance();
        List<UserUserDetailsDTO> list = dao.getUsersByHobby(hDAO.read(CommonTestData.hobby1id));
        assertEquals(1, list.size());
        assertEquals("name: test user hobbies. address:Test Hobbies. zip:999999. age:999. phone:0. gender:NOTSPECIFIED",
                list.get(0).toString());
    }

    @Test
    void usersFromGivenCity() {
        List<User> u = dao.usersFromGivenCity("Christiansborg");
        HobbyDAO hDAO = HobbyDAOImpl.getInstance();
        assertEquals(1, u.size());
        assertEquals(CommonTestData.hobby1id, u.get(0).getHobbies().iterator().next().getID().intValue());
        assertEquals("Sjælland", u.get(0).getUserDetails().getAddress().getZip().getMunicipality_name());
    }
}