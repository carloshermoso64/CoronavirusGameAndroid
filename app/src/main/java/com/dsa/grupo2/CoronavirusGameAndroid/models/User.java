package com.dsa.grupo2.CoronavirusGameAndroid.models;


import com.google.gson.annotations.SerializedName;

import org.apache.commons.lang3.RandomStringUtils;

public class User {
    private String id;
    private String name;
    private String email;
    private String password;
    private int exp;
    private int level;
    private String adminRights;

    public User() {
    }

    public User(String name, String email, String password) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.level = 1;
        this.adminRights = "FALSE";
    }

    public String toStringLevel() {
        return "level = " + level;
    }

    public String toStringExp() {
        return "exp = " + exp;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setExp(int exp) {
        this.exp = exp;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public String getAdminRights() {
        return adminRights;
    }

    public void setAdminRights(String adminRights) {
        this.adminRights = adminRights;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public int getExp() {
        return exp;
    }

    public String getPassword() {
        return password;
    }
}