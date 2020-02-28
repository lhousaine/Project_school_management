package api.ms.users.ms.users.models;

import lombok.Data;

import javax.persistence.*;

@Data
@Embeddable
public class Contact {
    private String phone;
    private String email;
}
