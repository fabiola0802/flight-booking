package com.falterziu.flightdata.entity;

import com.falterziu.flightdata.enumeration.Role;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Where;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "customer", schema = "public", uniqueConstraints = @UniqueConstraint(columnNames = { "email" }))
@Where(clause = "validity=true")
public class UserEntity extends BaseEntity {

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "password")
    private String password;

    @Column(name = "email")
    private String email;

    @Enumerated(EnumType.STRING)
    @Column(name = "role")
    private Role role;

    @Column(name = "validity")
    private Boolean validity;

}
