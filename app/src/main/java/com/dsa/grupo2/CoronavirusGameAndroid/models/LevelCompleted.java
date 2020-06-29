package com.dsa.grupo2.CoronavirusGameAndroid.models;

import java.util.Date;

public class LevelCompleted {
    private int levelNumber;
    private int score;
    private int time;
    private Date startTime;
    private String idUser;
    private boolean keepsMask;

    public LevelCompleted() {

    }

    public int getLevelNumber() {
        return levelNumber;
    }

    public void setLevelNumber(int levelNumber) {
        this.levelNumber = levelNumber;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public String getIdUser() {
        return idUser;
    }

    public void setIdUser(String idUser) {
        this.idUser = idUser;
    }

    public boolean getKeepsMask() {
        return keepsMask;
    }

    public void setKeepsMask(boolean keepsMask) {
        this.keepsMask = keepsMask;
    }
}
