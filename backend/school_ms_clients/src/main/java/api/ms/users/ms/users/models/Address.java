package api.ms.users.ms.users.models;

import lombok.Data;

import javax.persistence.*;

@Data
@Embeddable
public class Address {
    private int zipCode;
    private String addressLine;
    private String city;
    private String country;
}
