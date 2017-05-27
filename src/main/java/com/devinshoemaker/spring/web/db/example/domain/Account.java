package com.devinshoemaker.spring.web.db.example.domain;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

/**
 * Data access object for the account table.
 *
 * @author Devin Shoemaker (devinshoe@gmail.com)
 */
@Entity
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    @NotNull
    private String name;
    private boolean active = true;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

}
