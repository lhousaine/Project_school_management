package com.isma.school_ms_schools.core.helpers;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Email;

@Getter
@Setter
@Embeddable
public class Contact {
    @Column(unique = true,nullable = false)
    private String phone;
    @Email
    @Column(unique = true,nullable = false)
    private String email;

    public Contact() {
    }

    public Contact(String phone, @Email String email) {
        this.phone = phone;
        this.email = email;
    }
}
