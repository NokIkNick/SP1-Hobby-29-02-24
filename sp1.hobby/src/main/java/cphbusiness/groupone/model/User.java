package cphbusiness.groupone.model;

import jakarta.persistence.*;
import jakarta.transaction.Transactional;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.Hibernate;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Getter
@Setter
@Entity
@NoArgsConstructor
@Table(name = "users")
public class User {
    @Id
    @Column(name = "username", nullable = false)
    private String username;

    private String password;
    private boolean is_admin;


    @OneToOne(mappedBy = "user",cascade = CascadeType.ALL)
    private UserDetails userDetails;

    @ManyToMany(cascade =  {CascadeType.DETACH,CascadeType.MERGE},fetch = FetchType.EAGER)
    private Set<Hobby> hobbies = new HashSet<>();

    @ManyToMany(cascade = {CascadeType.DETACH,CascadeType.MERGE},fetch = FetchType.EAGER)
    private Set<Hobby> hobbyInterests = new HashSet<>();

    public User(String username, String password, boolean is_admin) {
        this.username = username;
        this.password = password;
        this.is_admin = is_admin;
    }

    public UserDetails addUserDetails(UserDetails userDetails){
        if(userDetails != null && !Objects.equals(this.userDetails,userDetails)){
            this.userDetails = userDetails;
            userDetails.addUser(this);

        }
        return userDetails;
    }

    @Transactional
    public Hobby addHobby(Hobby hobby){
        if(hobby != null){
            Hibernate.initialize(this.hobbies);
            this.hobbies.add(hobby);
            hobby.addUser(this);
        }
        return hobby;
    }
    @Transactional
    Hobby addHobbyToInterests(Hobby hobby){
        if(hobby != null){
            Hibernate.initialize(this.hobbyInterests);
            this.hobbyInterests.add(hobby);
            hobby.addInterestedUser(this);
        }
        return hobby;
    }
}