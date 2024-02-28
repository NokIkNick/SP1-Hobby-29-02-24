package cphbusiness.groupone.dao.implementations;

import cphbusiness.groupone.dao.abstractDAOs.HobbyDAO;
import cphbusiness.groupone.exceptions.NoResultException;
import cphbusiness.groupone.model.Hobby;
import cphbusiness.groupone.system.ExceptionLogger;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;

import jakarta.persistence.TypedQuery;

import java.util.*;

import java.util.stream.Collectors;

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
            Query amountOfPeopleByHobby = em.createNamedQuery("Hobby.countOfPeopleByHobby")
                    .setParameter(1,id);
            if(amountOfPeopleByHobby != null) {
                return (int) amountOfPeopleByHobby.getSingleResult();
            }else {
                // logic to handle nothing found TODO
                ExceptionLogger.log(new NoResultException("No results found").toString());
                return 0;
            }
        }catch(Exception e){
            ExceptionLogger.log(e.toString());
        }
        return 0;
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
                ExceptionLogger.log(new NoResultException("No results found").toString());
            }
        }catch (Exception e){
            ExceptionLogger.log(e.toString());
        }
        return null;
    }

    @Override
    public Map<Hobby, Long> getHobbiesByPopularity(int pageNr, int pageSize) {
        Map<Hobby, Long> resultMap;
        try(EntityManager em = emf.createEntityManager()){
            TypedQuery<Object[]> query = em.createNamedQuery("Hobby.findMostPopularHobbies", Object[].class);
            query.setFirstResult((pageNr - 1) * pageSize);
            query.setMaxResults(pageSize);
            resultMap = query.getResultList().stream().collect(Collectors.toMap(row -> (Hobby) row[0], row-> (long) row[1]));

            // Sorting of map by count of users, and constructing new map
            Map<Hobby, Long> sorted = resultMap.entrySet()
                    .stream()
                    .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                    .collect(Collectors.toMap(
                            Map.Entry::getKey,
                            Map.Entry::getValue,
                            (oldValue,newValue) -> oldValue, //If the value is a duplicate, keep the old one.
                            LinkedHashMap::new)); //Workaround, so that we keep the order the values are placed in the map.
            return sorted;
        }catch (Exception e){
            ExceptionLogger.log(new NoResultException("No results found", e).toString());
        }
        return null;
    }
}
