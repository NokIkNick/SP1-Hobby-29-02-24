package cphbusiness.groupone.dao.implementations;

import cphbusiness.groupone.config.HobbyConfig;
import cphbusiness.groupone.dao.abstractDAOs.UserDAO;
import cphbusiness.groupone.exceptions.NoResultException;
import cphbusiness.groupone.model.Address;
import cphbusiness.groupone.model.User;
import cphbusiness.groupone.system.Logger;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import cphbusiness.groupone.dto.UserUserDetailsDTO;
import cphbusiness.groupone.model.Hobby;

public class UserDAOImpl extends UserDAO {

    private static UserDAOImpl instance;

    public static UserDAOImpl getInstance(){
        if(instance == null){
            instance = new UserDAOImpl();
        }
        return instance;
    }
    public UserDAOImpl(){
        super();
    }

    // US - 10
    public Map<User,Integer> getUsersAndHobbyCountByAddress(Address address){
        try(EntityManager em = emf.createEntityManager()){
            TypedQuery<Object[]>  mapQuery = em.createNamedQuery("User.usersAndHobbyCountByAddress",Object[].class)
                    .setParameter(1,address.getStreet());
            if(mapQuery != null){
                // Solution without using a stream
                /*Map<User,Integer> userHobbyCountMap = new HashMap<>();
                for (Object[] result : mapQuery.getResultList()){
                    User user = (User) result[0];
                    Integer hobbyCount = (Integer) result[1];
                    userHobbyCountMap.put(user,hobbyCount);
                }*/
                // solution with a steam as required.
                List<Object[]> objectList = mapQuery.getResultList();
                Map<User, Integer> userHobbyCountMap = objectList.stream()
                        .collect(Collectors.toMap(
                                x -> (User) x[0],
                                x -> (Integer) x[1])
                                );
                return userHobbyCountMap;
            }else {
                Logger.exceptionLog(new NoResultException("No results found").toString());
                return null;
            }
        }
    }

    @Override
    public List<UserUserDetailsDTO> getUsersByHobby(Hobby hobby) {
        if(hobby != null){
            try(var em = emf.createEntityManager()){
                TypedQuery<UserUserDetailsDTO> query = em.createNamedQuery("User.getUsersByHobby", UserUserDetailsDTO.class);
                query.setParameter("value",hobby);
                return query.getResultList();
            }
        }
        return null;
    }

    public List<User> usersFromGivenCity(String city){
        try(EntityManager entityManager = HobbyConfig.getInstance().createEntityManager()){
            Query query = entityManager.createQuery("select u from users u where u.userDetails.address.zip.city_name = :city");
            query.setParameter("city",city);
            List<User> users = query.getResultList();
            return users;
        }
    }



}
