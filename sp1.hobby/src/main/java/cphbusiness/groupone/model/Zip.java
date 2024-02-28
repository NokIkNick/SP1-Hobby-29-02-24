package cphbusiness.groupone.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@jakarta.persistence.Entity
@Table(name = "zip")
public class Zip implements Entity<Integer> {

    @Id
    //@GeneratedValue(strategy = GenerationType.IDENTITY) //// We need to be able to set the zipcode, not autogenerate it, we don't have that luxury
    @Column(name = "id", nullable = false)
    private Integer zip;

    private String city_name;
    private String region_name;
    private String municipality_name;

    @Override
    public Integer getID() {
        return zip;
    }
}