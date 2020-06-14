package com.dsa.grupo2.CoronavirusGameAndroid.models;

import org.apache.commons.lang3.RandomStringUtils;

public class Token {
    String id;
    String idUser;
    String admin;

    public Token(String idUser, String id) {
        this.id = id;
        this.idUser = idUser;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIdUser() {
        return idUser;
    }

    public void setIdUser(String idUser) {
        this.idUser = idUser;
    }

    public String getAdmin() {
        return admin;
    }

    public void setAdmin(String admin) {
        this.admin = admin;
    }
}

