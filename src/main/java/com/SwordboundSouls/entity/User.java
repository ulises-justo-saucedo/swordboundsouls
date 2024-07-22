package com.SwordboundSouls.entity;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Entity
@Table(name = "users")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(unique = true, nullable = false)
    private String username;

    @Column(nullable = false)
    private String password;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "user_role",
            joinColumns = @JoinColumn(name = "id_user", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "id_role", referencedColumnName = "idRole")
    )
    private List<Role> userRoles;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_character", referencedColumnName = "idCharacter")
    private Character character;

    public User(String username, String password, Character character) {
        super();
        this.username = username;
        this.password = password;
        this.character = character;
        this.userRoles = new ArrayList<>();
    }

    public User(String username, String password) {
        super();
        this.username = username;
        this.password = password;
        this.userRoles = new ArrayList<>();
    }
}
