package com.example.demo.ch02;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

/**
 * Created by frank on 2018-09-29.
 */
public class RolePrivilege {
    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    Role role;

    @ManyToOne
    Privilege privilege;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public Privilege getPrivilege() {
        return privilege;
    }

    public void setPrivilege(Privilege privilege) {
        this.privilege = privilege;
    }

    @Override
    public String toString() {
        return "RolePrivilege{" +
                "id=" + id +
                ", role=" + role +
                ", privilege=" + privilege +
                '}';
    }
}
