package unitTests;

import cphbusiness.groupone.config.HobbyConfig;
import jakarta.persistence.EntityManagerFactory;

class CommonTestData {
    static EntityManagerFactory emf;
    private static void commonSetup(){
        if(emf == null)
            emf = HobbyConfig.getInstance(true);
    }
    static void setupHobbies(){

    }


}
