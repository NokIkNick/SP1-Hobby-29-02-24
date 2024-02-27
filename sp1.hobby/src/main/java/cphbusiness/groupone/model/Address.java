package cphbusiness.groupone.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.Objects;

@Getter
@Setter
@Entity
@Table(name = "address")
@NoArgsConstructor

public class Address {

    @Id
    private String id;
    private String street;

    public Address(String street) {
        this.street = street;
    }

    @OneToOne
    @MapsId
    private UserDetails userDetails;

    @ManyToOne
    private Zip zip;


    UserDetails addUserDetails(UserDetails userDetails){
        if(userDetails != null && !Objects.equals(this.userDetails,userDetails)){
            this.userDetails = userDetails;
            userDetails.addAddress(this);
        }
        return userDetails;
    }

}