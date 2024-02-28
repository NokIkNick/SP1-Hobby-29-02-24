package cphbusiness.groupone.dao.implementations;

import cphbusiness.groupone.dao.abstractDAOs.UserDAO;
import cphbusiness.groupone.model.Address;
import cphbusiness.groupone.model.User;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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
        try(EntityManager em = super.emf.createEntityManager()){
            TypedQuery<Object[]>  mapQuery = em.createNamedQuery("User.usersAndHobbyCountByAddress",Object[].class)
                    .setParameter(1,address.getStreet());
            if(mapQuery != null){
                // Solution without using a stream
                /*Map<User,Integer> userIntegerMap = new HashMap<>();
                for (Object[] result : mapQuery.getResultList()){
                    User user = (User) result[0];
                    Integer hobbyCount = (Integer) result[1];
                    userIntegerMap.put(user,hobbyCount);
                }*/
                // solution with a steam as required.
                List<Object[]> objectList = mapQuery.getResultList();
                Map<User, Integer> userHobbyCountMap = objectList.stream()
                        .collect(Collectors.toMap(
                                x -> (User) x[0],
                                x -> (Integer) x[1])
                                );
                return /*userIntegerMap*/ userHobbyCountMap;
            }else {
                // add logic to handle no information found. TODO
                return null;
            }
        }
    }
}
