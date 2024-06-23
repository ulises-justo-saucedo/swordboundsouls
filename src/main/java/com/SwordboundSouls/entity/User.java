package com.SwordboundSouls.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "users")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idUser;

    @Column(unique = true)
    private String username;
    private String password;
    private boolean isAdmin;
    @OneToOne
    @JoinColumn(name = "id_character", referencedColumnName = "idCharacter")
    private Character character;

    public User(String username, String password, boolean isAdmin) {
        super();
        this.username = username;
        this.password = password;
        this.isAdmin = isAdmin;
    }

    public User(String username, String password, boolean isAdmin, Character character) {
        super();
        this.username = username;
        this.password = password;
        this.isAdmin = isAdmin;
        this.character = character;
    }

}
