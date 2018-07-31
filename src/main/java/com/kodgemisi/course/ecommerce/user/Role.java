package com.kodgemisi.course.ecommerce.user;

import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;

@Getter
@Entity
@NoArgsConstructor
public class Role implements GrantedAuthority {

    public static final Role ADMIN = new Role(RoleName.ROLE_ADMIN);
    public static final Role USER = new Role(RoleName.ROLE_USER);

    @Id
    @Enumerated(EnumType.STRING)
    @Column(unique = true)
    private RoleName roleName;



    @Override
    public String getAuthority() {
        return this.roleName.name();
    }

    public Role(RoleName roleName) {
        this.roleName = roleName;
    }

    public  enum RoleName {
        ROLE_ADMIN, ROLE_USER
    }
}
