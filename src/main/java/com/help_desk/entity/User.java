package com.help_desk.entity;

import javax.persistence.*;
import java.util.Collection;
/**
 * Table of user
 */
@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String name;
    private String address;
    private int id_auth;

    public User(String name, String address, int id_auth) {
        this.name = name;
        this.address = address;
        this.id_auth = id_auth;
    }

    public int getId_auth() {

        return id_auth;
    }

    public void setId_auth(int id_auth) {
        this.id_auth = id_auth;
    }

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
