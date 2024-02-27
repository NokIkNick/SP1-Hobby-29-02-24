package cphbusiness.groupone.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Objects;

@Getter
@Setter
@Entity(name = "user_details")
@ToString
public class UserDetails {
    @Id
    private String id;

    private int age;
    private Gender gender;
    private int phone_number;


    @OneToOne(cascade = CascadeType.REMOVE)
    @MapsId
    private User user;
    @OneToOne(mappedBy = "userDetails",cascade = CascadeType.ALL)
    Address address;



    public User addUser(User user){
        if(user != null){
            this.user = user;
            user.setUserDetails(this);
        }
        return user;
    }

    public Address addAddress(Address address) {
        if (address != null && !Objects.equals(this.address,address)) {
            this.address = address;
            address.addUserDetails(this);
        }
        return address;
    }
}