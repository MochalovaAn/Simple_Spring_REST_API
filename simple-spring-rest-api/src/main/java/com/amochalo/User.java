package com.amochalo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class User {

    @Id
    @GeneratedValue
    private Long id;
    private Integer value;

    // avoid this "No default constructor for entity"
    public User() {
    }

    public User(Integer value) {
        this.value = value;
    }

    public User(Long id, Integer value) {
        this.id = id;
        this.value = value;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", value='" + value +
                '}';
    }

}
