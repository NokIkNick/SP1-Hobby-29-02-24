package cphbusiness.groupone.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.Hibernate;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@EqualsAndHashCode
@Entity(name = "hobby")
@NamedQueries({
        // US - 5
        @NamedQuery(name = "Hobby.countOfPeopleByHobby", query = "select size(h.usersSet) from hobby h where id = ?1"),
        // US - 6
        @NamedQuery(name = "Hobby.findHobbiesWithInterestCounts", query = "SELECT h, size(h.interestedUsers) FROM hobby h"),
        //OPT-3
        @NamedQuery(name="Hobby.findMostPopularHobbies", query = "SELECT h, COUNT(u) from hobby h LEFT JOIN h.usersSet u GROUP BY h ORDER BY COUNT(u) DESC")
    }
)
public class Hobby implements SuperEntity<Integer> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    @Setter(AccessLevel.NONE)
    private Integer id;

    @ManyToMany(mappedBy = "hobbies", cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
    private Set<User> usersSet = new HashSet<>();

    @ManyToMany(mappedBy = "hobbyInterests",fetch = FetchType.EAGER)
    private Set<User> interestedUsers = new HashSet<>();

    @Column
    private String name;
    private String wiki_link;
    private String category;

    @Enumerated(EnumType.ORDINAL)
    private Type type;

    User addUser(User user){
        if(user != null && !usersSet.contains(user)){
            this.usersSet.add(user);
            if(!user.getHobbies().contains(this))
                user.addHobby(this);
        }
        return user;
    }

    User addInterestedUser(User user){
        if(user != null){
            if(!Hibernate.isInitialized(this.interestedUsers))
                Hibernate.initialize(this.interestedUsers);
            this.interestedUsers.add(user);
            if(!user.getHobbyInterests().contains(this))
                user.addHobbyToInterests(this);
        }
        return user;
    }

    @Override
    public Integer getID() {
        return id;
    }
}