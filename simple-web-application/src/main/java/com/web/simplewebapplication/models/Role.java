package com.web.simplewebapplication.models;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Table(name = "roles")
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @ManyToMany(mappedBy = "roles")
    private Collection<User> users;

    @ManyToMany
    @JoinTable (
            name = "privileges",
            joinColumns = @JoinColumn (
                    name = "role_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn (
                    name = "privilege_id", referencedColumnName = "id"))
    private Collection<Privilege> privileges;

    public Role() {

    }

    public Role(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String role) {
        this.name = role;
    }

    public Collection<Privilege> getPrivileges() {
        return privileges;
    }
}
