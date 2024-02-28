package cphbusiness.groupone.model;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.Hibernate;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Getter
@Setter
@jakarta.persistence.Entity(name = "users")
@NoArgsConstructor

@NamedQueries({
        //US: 3
        @NamedQuery(name="User.getUsersByHobby", query = "select new cphbusiness.groupone.dto.UserUserDetailsDTO(u.username, us) from cphbusiness.groupone.model.User u join cphbusiness.groupone.model.UserDetails us on us.user.id = u.id where :value member of u.hobbies")
})

public class User implements Entity<String> {

    @Id
    @Column(name = "username", nullable = false)
    private String username;

    private String password;
    private boolean is_admin;

    @Setter(AccessLevel.NONE)
    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    private UserDetails userDetails;
    public UserDetails getUserDetails(){
        if(userDetails==null)
            return new UserDetails();
        return userDetails;
    }
  
    @ManyToMany(cascade = {CascadeType.DETACH},fetch = FetchType.EAGER)
    @JoinTable(name = "hobbys", joinColumns = @JoinColumn(name = "username"), inverseJoinColumns = @JoinColumn(name = "hobby_id"))
    private Set<Hobby> hobbies = new HashSet<>();

    @ManyToMany(cascade = {CascadeType.DETACH},fetch = FetchType.EAGER)
    @JoinTable(name = "hobbyInts", joinColumns = @JoinColumn(name = "username"), inverseJoinColumns = @JoinColumn(name = "hobby_id"))
    private Set<Hobby> hobbyInterests = new HashSet<>();

    public User(String username, String password, boolean is_admin) {
        this.username = username;
        this.password = password;
        this.is_admin = is_admin;
    }

    @SuppressWarnings("UnusedReturnValue")
    public UserDetails setUserDetails(UserDetails userDetails){
        if(userDetails != null && !Objects.equals(this.userDetails,userDetails)){
            this.userDetails = userDetails;
            userDetails.addUser(this);
        }
        return userDetails;
    }

    @SuppressWarnings("UnusedReturnValue")
    public Hobby addHobby(Hobby hobby){
        if(hobby != null && !hobbies.contains(hobby)){
            Hibernate.initialize(this.hobbies);
            this.hobbies.add(hobby);
            hobby.addUser(this);
        }
        return hobby;
    }
    @SuppressWarnings("UnusedReturnValue")
    public Hobby addHobbyToInterests(Hobby hobby){
        if(hobby != null){
            Hibernate.initialize(this.hobbyInterests);
            this.hobbyInterests.add(hobby);
            hobby.addInterestedUser(this);
        }
        return hobby;
    }

    @Override
    public String getID() {
        return username;
    }
}