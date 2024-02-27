package cphbusiness.groupone.dao.implementations;

import cphbusiness.groupone.dao.abstractDAOs.HobbyDAO;
import cphbusiness.groupone.model.Hobby;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;

import java.util.Map;

public class HobbyDAOImpl extends HobbyDAO {

    private static HobbyDAOImpl instance;

    public static HobbyDAOImpl getInstance(){
        if(instance == null){
            instance = new HobbyDAOImpl();
        }
        return instance;
    }

    public HobbyDAOImpl(){
        super();
    }

    @Override
    public int getCountOfPeopleByHobbyId(int id) {
        try(EntityManager em = super.emf.createEntityManager()){
            Query amoutOfPeopleByHobby = em.createNamedQuery("Hobby.countOfPeopleByHobby")
                    .setParameter(1,id);
            if(amoutOfPeopleByHobby != null) {
                return (int) amoutOfPeopleByHobby.getSingleResult();
            }else {
                // logic to handle nothing found
                return 0;
            }
        }
    }

    @Override
    public Map<Hobby, Integer> getHobbyWithCountOfInterestedPeople() {
        return null;
    }
}
