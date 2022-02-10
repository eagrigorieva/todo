package com.eagrigorieva.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "user_role")
public class UserRole implements GrantedAuthority {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "code")
    private String code;

    @OneToMany(mappedBy = "role")
    private List<User> usersList;

    @Override
    public String getAuthority() {
        return "ROLE_" + code;
    }
}
