package com.isma.school_ms_schools.core.helpers;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@NoArgsConstructor
@Embeddable
public class Address{
    @Size(max = 10)
    private String zipCode;

    @NotNull
    @Size(max = 100)
    private String addressLine;

    @NotNull
    @Size(max = 100)
    private String city;

    @NotNull
    @Size(max = 100)
    private String country;

    public Address(@Size(max = 10) String zipCode, @NotNull @Size(max = 100) String addressLine, @NotNull @Size(max = 100) String city, @NotNull @Size(max = 100) String country) {
        this.zipCode = zipCode;
        this.addressLine = addressLine;
        this.city = city;
        this.country = country;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public void setAddressLine(String addressLine) {
        this.addressLine = addressLine;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getZipCode() {
        return zipCode;
    }

    public String getAddressLine() {
        return addressLine;
    }

    public String getCity() {
        return city;
    }

    public String getCountry() {
        return country;
    }
}
