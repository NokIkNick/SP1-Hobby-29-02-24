package cphbusiness.groupone.dto;

import cphbusiness.groupone.model.UserDetails;

public class UserUserDetailsDTO {

    private String username;
    private UserDetails userDetails;

    public UserUserDetailsDTO(String username, UserDetails userDetails) {
        this.username = username;
        this.userDetails = userDetails;
    }

    @Override
    public String toString() {
        return "name: " + this.username + ". address:" + userDetails.getAddress().getStreet() +". zip:"+userDetails.getAddress().getZip().getID()+ ". age:" + userDetails.getAge() + ". phone:" + userDetails.getPhone_number() + ". gender:" + userDetails.getGender();
    }
}
