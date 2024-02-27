package cphbusiness.groupone.model;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

import java.util.Objects;

@Getter
@Setter
@Entity(name = "user_details")
public class UserDetails implements DTO<String>{
    @Override
    public String getID() {
        return id;
    }

    @Id
    @Setter(AccessLevel.NONE)
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


    /**
     * Backup function which shouldn't be needed to be called ever.
     * instead use getAddress and change things there.
     * This was created due to how working with databases you CANNOT just create a new object with the same
     * @return The Address associated with UserDetails
     */
    public Address setAddress(Address address) {
        if (address != null && !Objects.equals(this.address,address)) {
            this.address.setStreet(address.getStreet());
            this.address.setZip(address.getZip());
            address = this.address;
            address.setUserDetails(this);
        }
        if(this.address == null && address != null) {
            this.address = address;
        }
        return this.address;
    }
    public Address getAddress() {
        if (this.address == null) {
            this.address = new Address();
        }
        return this.address;
    }
}