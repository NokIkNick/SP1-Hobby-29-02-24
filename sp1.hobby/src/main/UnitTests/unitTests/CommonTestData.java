package unitTests;

import cphbusiness.groupone.config.HobbyConfig;
import cphbusiness.groupone.dao.abstractDAOs.HobbyDAO;
import cphbusiness.groupone.dao.implementations.HobbyDAOImpl;
import cphbusiness.groupone.model.Hobby;
import cphbusiness.groupone.model.Type;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;

class CommonTestData {
    static EntityManagerFactory emf;
    private static void commonSetup(){
        if(emf == null)
            emf = HobbyConfig.getInstance(true);
    }
    static void setupHobbies(){
        HobbyDAO dao = HobbyDAOImpl.getInstance();
        Hobby h1 = new Hobby();
        h1.setType(Type.INDOORS);
        h1.setName("gymnastic");
        h1.setWiki_link("...");
        h1.setCategory("Hello");
        dao.create(h1);
    }


}
