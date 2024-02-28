package cphbusiness.groupone.model;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Objects;

@Getter
@Setter
@jakarta.persistence.Entity
@Table(name = "address")
@NoArgsConstructor
public class Address implements Entity<String> {

    @Id
    @Setter(AccessLevel.NONE)
    private String id;
    private String street;

    @OneToOne
    @MapsId
    private UserDetails userDetails;

    @ManyToOne
    private Zip zip;


    public Address(String street, Zip zip) {
        this.street = street;
        this.zip = zip;
    }

    public UserDetails setUserDetails(UserDetails userDetails){
        if(userDetails != null && !Objects.equals(this.userDetails,userDetails)){
            this.userDetails = userDetails;
            userDetails.setAddress(this);
        }
        return userDetails;
    }

    @Override
    public String getID() {
        return id;
    }
}