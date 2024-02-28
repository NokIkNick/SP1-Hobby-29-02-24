package cphbusiness.groupone.dao.implementations;

import cphbusiness.groupone.dao.abstractDAOs.UserDAO;
import cphbusiness.groupone.dto.UserUserDetailsDTO;
import cphbusiness.groupone.model.Hobby;
import jakarta.persistence.TypedQuery;

import java.util.List;

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

    @Override
    public List<UserUserDetailsDTO> getUsersByHobby(Hobby hobby) {
        if(hobby != null){
            try(var em = emf.createEntityManager()){
                em.getTransaction().begin();
                TypedQuery<UserUserDetailsDTO> query = em.createNamedQuery("User.getUsersByHobby", UserUserDetailsDTO.class);
                query.setParameter("value",hobby);
                em.getTransaction().commit();
                return query.getResultList();
            }
        }
        return null;
    }
}
