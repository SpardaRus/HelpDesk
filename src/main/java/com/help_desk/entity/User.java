package com.help_desk.entity;

import javax.persistence.*;
import java.util.Collection;

@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String name;
    private String address;


    public User(int id, String name, String address) {
        this.id = id;
        this.name = name;
        this.address = address;
    }

    public User(String name, String address) {
        this.name = name;
        this.address = address;
    }

    public void setId(int id) {
        this.id = id;
    }

    public User() {

    }

    public int getId() {
        return id;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                '}';
    }



}
