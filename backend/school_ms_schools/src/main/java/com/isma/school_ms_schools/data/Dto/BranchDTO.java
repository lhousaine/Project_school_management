package com.isma.school_ms_schools.data.Dto;

import com.isma.school_ms_schools.core.helpers.Address;
import com.isma.school_ms_schools.core.helpers.ContactSchool;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BranchDTO {
    private long id;
    private String name;
    private String description;
    private ContactSchool contact;
    private Address address;
    private String schoolName;

    public BranchDTO(String name, String description, ContactSchool contact, Address address) {
        this.name = name;
        this.description = description;
        this.contact = contact;
        this.address = address;
    }

}
