package cphbusiness.groupone.dao.implementations;

import cphbusiness.groupone.dao.abstractDAOs.HobbyDAO;
import cphbusiness.groupone.model.Hobby;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;

import java.util.HashMap;
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
    // US - 5
    @Override
    public int getCountOfPeopleByHobbyId(int id) {
        try(EntityManager em = super.emf.createEntityManager()){
            Query amoutOfPeopleByHobby = em.createNamedQuery("Hobby.countOfPeopleByHobby")
                    .setParameter(1,id);
            if(amoutOfPeopleByHobby != null) {
                return (int) amoutOfPeopleByHobby.getSingleResult();
            }else {
                // logic to handle nothing found TODO
                return 0;
            }
        }
    }
    // US-6
    @Override
    public Map<Hobby, Integer> getHobbyWithCountOfInterestedPeople() {
        try (EntityManager em = emf.createEntityManager()) {
            TypedQuery<Object[]> resultQuery = em.createNamedQuery("Hobby.findHobbiesWithInterestCounts", Object[].class);
            if (resultQuery != null) {
                Map<Hobby, Integer> hobbyIntegerMap = new HashMap<>();
                for (Object[] result : resultQuery.getResultList()) {
                    Hobby hobby = (Hobby) result[0];
                    Integer interestedCount = (int) result[1];
                    hobbyIntegerMap.put(hobby, interestedCount);
                }
                return hobbyIntegerMap;
            } else {
                // add logic to handle no information found // TODO
                return null;
            }
        }
    }
}
