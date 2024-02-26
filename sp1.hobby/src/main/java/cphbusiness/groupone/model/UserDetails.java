package cphbusiness.groupone.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "user_details")
public class UserDetails {
    @Id
    private String id;

    private int age;
    private Gender gender;
    private int phone_number;


    @OneToOne
    @MapsId
    private User user;
    @OneToOne(mappedBy = "userDetails")
    Address address;



    User addUser(User user){
        if(user != null){
            this.user = user;
            user.addUserDetails(this);
        }
        return user;
    }

    Address addAddress(Address address) {
        if (address != null) {
            this.address = address;
            address.addUserDetails(this);
        }
        return address;
    }
}