package cphbusiness.groupone.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@Entity
@Table(name = "zip")
@ToString
public class Zip {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer zip;

    private String city_name;
    private String region_name;
    private String municipality_name;


}