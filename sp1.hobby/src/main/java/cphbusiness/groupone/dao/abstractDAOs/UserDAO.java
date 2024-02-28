package cphbusiness.groupone.dao.abstractDAOs;

import cphbusiness.groupone.dto.UserUserDetailsDTO;
import cphbusiness.groupone.model.Hobby;
import cphbusiness.groupone.model.User;

import java.util.List;

public abstract class UserDAO extends DAO<User, String> {
    public User read(String id){
        return super.read(id, User.class);
    }

    public abstract List<UserUserDetailsDTO> getUsersByHobby(Hobby hobby);


}
