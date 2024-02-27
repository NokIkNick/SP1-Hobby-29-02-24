package cphbusiness.groupone.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
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

    @ManyToMany
    private Set<Hobby> hobbies = new HashSet<>();

    @ManyToMany
    private Set<Hobby> hobbyInterests = new HashSet<>();

    public User(String username, String password, boolean is_admin) {
        this.username = username;
        this.password = password;
        this.is_admin = is_admin;
    }

    UserDetails addUserDetails(UserDetails userDetails){
        if(userDetails != null){
            this.userDetails = userDetails;
            userDetails.addUser(this);

        }
        return userDetails;
    }

    Hobby addHobby(Hobby hobby){
        if(hobby != null){
            this.hobbies.add(hobby);
            hobby.addUser(this);
        }
        return hobby;
    }
    Hobby addHobbyToInterests(Hobby hobby){
        if(hobby != null){
            this.hobbyInterests.add(hobby);
            hobby.addInterestedUser(this);
        }
        return hobby;
    }
}