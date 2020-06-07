package com.dsa.grupo2.CoronavirusGameAndroid.models;

public class Game {
    private String id;
    private String idUser;
    private int cash;
    private int neurons;
    private String mask;
    private int lifes;
    private int completedLevels;

    public Game() {
    }

    public Game(String idUser) {
        this.idUser = idUser;
        this.cash = 500;
        this.mask = "FALSE";
        this.lifes = 1;
        this.neurons = 0;
        this.completedLevels = 0;
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

    public int getCash() {
        return cash;
    }

    public void setCash(int cash) {
        this.cash = cash;
    }

    public int getNeurons() {
        return neurons;
    }

    public void setNeurons(int neurons) {
        this.neurons = neurons;
    }

    public String getMask() {
        return mask;
    }

    public void setMask(String mask) {
        this.mask = mask;
    }

    public int getLifes() {
        return lifes;
    }

    public void setLifes(int lifes) {
        this.lifes = lifes;
    }

    public int getCompletedLevels() {
        return completedLevels;
    }

    public void setCompletedLevels(int completedLevels) {
        this.completedLevels = completedLevels;
    }
}
