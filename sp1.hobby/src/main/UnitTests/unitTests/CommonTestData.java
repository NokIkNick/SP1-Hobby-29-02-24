package unitTests;

import cphbusiness.groupone.config.HobbyConfig;
import cphbusiness.groupone.dao.abstractDAOs.HobbyDAO;
import cphbusiness.groupone.dao.abstractDAOs.ZipDAO;
import cphbusiness.groupone.dao.implementations.HobbyDAOImpl;
import cphbusiness.groupone.dao.implementations.ZipDAOImpl;
import cphbusiness.groupone.model.Hobby;
import cphbusiness.groupone.model.Type;
import cphbusiness.groupone.model.Zip;
import jakarta.persistence.EntityManagerFactory;

class CommonTestData {
    public static EntityManagerFactory emf;
    public static int hobby1id;
    public static int hobby2id;

    private static void commonSetup(){
        if(emf == null)
            emf = HobbyConfig.getInstance(true);
    }

    static void setupHobbies(){
        commonSetup();
        //// okay to do this since it's guaranteed to not be in the test database, as it's empty
        HobbyDAO dao = HobbyDAOImpl.getInstance();
        Hobby h1 = new Hobby();
        popHobby(h1, Type.INDOORS, "gymnastic", "...", "Generel");
        dao.create(h1);
        hobby1id = h1.getID();
        Hobby h2 = new Hobby();
        popHobby(h2, Type.OUTDOORS, "Football", "...", "Generel");
        dao.create(h2);
        hobby2id = h2.getID();
    }
    
    static void setupZips(){
        commonSetup();
        //// okay to do this since it's guaranteed to not be in the test database, as it's empty
        ZipDAO dao = ZipDAOImpl.getInstance();
        Zip z1 = new Zip();
        popZip(z1, 999999, "Imaginaryland", "Yggdrasil", "Heaven");
        dao.create(z1);
        Zip z2 = new Zip();
        popZip(z2, 999998, "Maginaryland", "Yggdrasil", "Hell");
        dao.create(z2);
    }

    public static void popHobby(Hobby h, Type t, String name, String link, String cat){
        h.setType(t);
        h.setName(name);
        h.setWiki_link(link);
        h.setCategory(cat);
    }

    public static void popZip(Zip z, int zip, String city, String region, String municipality){
        z.setZip(zip);
        z.setCity_name(city);
        z.setRegion_name(region);
        z.setMunicipality_name(municipality);
    }

}
