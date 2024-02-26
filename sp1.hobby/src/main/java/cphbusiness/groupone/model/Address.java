package cphbusiness.groupone.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "address")
public class Address {

    @Id
    private String id;
    private String street;

    @OneToOne
    @MapsId
    private UserDetails userDetails;

    @ManyToOne
    private Zip zip;


    UserDetails addUserDetails(UserDetails userDetails){
        if(userDetails != null){
            this.userDetails = userDetails;
            userDetails.addAddress(this);
        }
        return userDetails;
    }

}