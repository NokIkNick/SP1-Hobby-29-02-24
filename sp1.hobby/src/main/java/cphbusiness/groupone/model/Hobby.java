package cphbusiness.groupone.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

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
    Set<User> usersSet = new HashSet<>();

    @Column
    private String name;
    private String wiki_link;
    private String category;

    @Enumerated(EnumType.ORDINAL)
    private Type type;


    User addUser(User user){
        if(user != null){
            this.usersSet.add(user);
            user.addHobby(this);
        }
        return user;
    }


}