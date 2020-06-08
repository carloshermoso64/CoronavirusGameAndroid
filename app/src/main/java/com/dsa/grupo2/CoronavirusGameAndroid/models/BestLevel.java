package com.dsa.grupo2.CoronavirusGameAndroid.models;

import java.util.Date;

public class BestLevel {
    private String id;
    private int levelNumber;
    private int bestScore;
    private int bestTime;
    private Date startDate;
    private String idUser;

    public BestLevel(){}
    public BestLevel(String id){
        this.id=id;
    }
    public BestLevel(String id, int levelNumber,String idUser){
        this.id=id;
        this.levelNumber=levelNumber;
        this.idUser=idUser;
    }
    public BestLevel(int levelNumber,int bestScore){
        this.levelNumber=levelNumber;
        this.bestScore=bestScore;
    }

    public String getId(){return this.id;}
    public void setId(String id){this.id=id;}

    public int getLevelNumber(){return this.levelNumber;}
    public void setLevelNumber(int LevelNumber){this.levelNumber=levelNumber;}

    public int getBestScore(){return this.bestScore;}
    public void setBestScore(int bestScore){this.bestScore=bestScore;}

    public int getBestTime(){return this.bestTime;}
    public void setBestTime(int bestTime){this.bestTime=bestTime;}

    public Date getStartDate(){return this.startDate;}
    public void setStartDate(Date startDate){this.startDate=startDate;}

    public String getIdUser(){return this.idUser;}
    public void setIdUser(String idUser){this.idUser=idUser;}
}
