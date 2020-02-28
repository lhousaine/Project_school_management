package com.isma.school_ms_schools.core.helpers;

import javax.persistence.*;
import javax.validation.constraints.Email;

@Embeddable
public class ContactSchool extends Contact{
    @Column(unique = true)
    private String facebook;
    @Column(unique = true)
    private String youtube;
    @Column(unique = true)
    private String website;
    @Column(unique = true)
    private String fix;

    public ContactSchool() {
    }

    public ContactSchool(String phone, @Email String email) {
        super(phone, email);
    }

    public void setFacebook(String facebook) {
        this.facebook = facebook;
    }

    public void setYoutube(String youtube) {
        this.youtube = youtube;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public void setFix(String fix) {
        this.fix = fix;
    }


    public String getFacebook() {
        return facebook;
    }

    public String getYoutube() {
        return youtube;
    }

    public String getWebsite() {
        return website;
    }

    public String getFix() {
        return fix;
    }
}
