package com.dsa.grupo2.CoronavirusGameAndroid.models;

import java.util.Date;

public class BestLevelTO {
    private String id;
    private int levelNumber;
    private int bestScore;
    private int bestTime;
    private Date startDate;
    private String username;

    public BestLevelTO(){}
    public BestLevelTO(String id){
        this.id=id;
    }
    public BestLevelTO(String id, int levelNumber,String username){
        this.id=id;
        this.levelNumber=levelNumber;
        this.username=username;
    }
    public BestLevelTO(int levelNumber,int bestScore){
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

    public String getUsername(){return this.username;}
    public void setUsername(String username){this.username=username;}
}
