package cphbusiness.groupone.model;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.Hibernate;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Entity(name = "users")
@NoArgsConstructor
@NamedQueries(
        {
                // US - 10
                @NamedQuery(name = "User.usersAndHobbyCountByAddress", query = "select u, size(u.hobbies) from users u where u.userDetails.address.street = ?1"),
                //US: 3
                @NamedQuery(name="User.getUsersByHobby", query = "select new cphbusiness.groupone.dto.UserUserDetailsDTO(u.username, us) from cphbusiness.groupone.model.User u join cphbusiness.groupone.model.UserDetails us on us.user.id = u.id where :value member of u.hobbies")
        }
)

public class User implements SuperEntity<String> {

    @Id
    @Column(name = "username", nullable = false)
    private String username;

    private String password;
    private boolean is_admin;

    @Setter(AccessLevel.NONE)
    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    private UserDetails userDetails;
    public UserDetails getUserDetails(){
        if(userDetails==null) {
            userDetails = new UserDetails();
            userDetails.setUser(this);
        }
        return userDetails;
    }
  
    @ManyToMany(cascade = {CascadeType.DETACH},fetch = FetchType.EAGER)
    @JoinTable(name = "hobbys", joinColumns = @JoinColumn(name = "username"), inverseJoinColumns = @JoinColumn(name = "hobby_id"))
    private Set<Hobby> hobbies = new HashSet<>();

    @ManyToMany(cascade = {CascadeType.DETACH},fetch = FetchType.EAGER)
    @JoinTable(name = "hobbyInts", joinColumns = @JoinColumn(name = "username"), inverseJoinColumns = @JoinColumn(name = "hobby_id"))
    private Set<Hobby> hobbyInterests = new HashSet<>();

    @Column(name ="date_created")
    @Temporal(TemporalType.DATE)
    private LocalDate dateCreated;

    @Column(name="last_updated")
    @Temporal(TemporalType.DATE)
    private LocalDate lastUpdated;


    @PrePersist
    private void PrePersist(){
        dateCreated = LocalDate.now();
        lastUpdated = dateCreated;
    }

    @PreUpdate
    private void PreUpdate(){
        lastUpdated = LocalDate.now();
    }

    public User(String username, String password, boolean is_admin) {
        this.username = username;
        this.password = password;
        this.is_admin = is_admin;
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
            if(!hobby.getInterestedUsers().contains(this))
                hobby.addInterestedUser(this);
        }
        return hobby;
    }

    @Override
    public String getID() {
        return username;
    }
}