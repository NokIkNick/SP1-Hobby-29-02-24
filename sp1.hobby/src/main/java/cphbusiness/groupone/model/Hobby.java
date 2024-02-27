package cphbusiness.groupone.model;

import jakarta.persistence.*;
import jakarta.transaction.Transactional;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.Hibernate;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Entity(name = "hobby")
public class Hobby {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @ManyToMany(mappedBy = "hobbies", fetch = FetchType.EAGER)
    private Set<User> usersSet = new HashSet<>();

    @ManyToMany(mappedBy = "hobbyInterests",fetch = FetchType.EAGER)
    private Set<User> interestedUsers = new HashSet<>();

    @Column
    private String name;
    private String wiki_link;
    private String category;

    @Enumerated(EnumType.ORDINAL)
    private Type type;

    @Transactional
    User addUser(User user){
        if(user != null && !usersSet.contains(user)){
            this.usersSet.add(user);
            user.addHobby(this);
        }
        return user;
    }

    User addInterestedUser(User user){
        if(user != null){
            if(!Hibernate.isInitialized(this.interestedUsers))
                Hibernate.initialize(this.interestedUsers);
            this.interestedUsers.add(user);
            user.addHobbyToInterests(this);
        }
        return user;
    }

}