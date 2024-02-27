package cphbusiness.groupone.model;

import cphbusiness.groupone.config.HibernateConfig;
import cphbusiness.groupone.config.HobbyConfig;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.Hibernate;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "hobby")
public class Hobby {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @ManyToMany(mappedBy = "hobbies")
    private Set<User> usersSet = new HashSet<>();

    @ManyToMany(mappedBy = "hobbyInterests")
    private Set<User> interestedUsers = new HashSet<>();

    @Column
    private String name;
    private String wiki_link;
    private String category;

    @Enumerated(EnumType.ORDINAL)
    private Type type;


    User addUser(User user){
        if(user != null){
           /* if(!Hibernate.isInitialized(this.usersSet)) {
                try(EntityManager em = HobbyConfig.getInstance().createEntityManager()) {
                    em.getTransaction().begin();
                    Hibernate.initialize(this.usersSet);
                    em.getTransaction().commit();
                }
            }*/
            this.usersSet.add(user);
            user.addHobby(this);
        }
        return user;
    }

    User addInterestedUser(User user){
        if(user != null){
            Hibernate.initialize(this.interestedUsers);
            this.interestedUsers.add(user);
            user.addHobbyToInterests(this);
        }
        return user;
    }

}