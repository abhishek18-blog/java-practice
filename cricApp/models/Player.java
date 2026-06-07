package models;
import java.io.Serializable;
/*
An abstract class is used when you want to 
create a common blueprint for other classes but
 do not want objects of that class to be created directly.

An abstract class can have:
Abstract methods
Normal methods
Variables
Constructors

*/

public  abstract class Player implements Serializable{

    private static final long serialVersionUID = 1L; 

    private final String name;
    private int matchesPlayed;

    public Player(String name){
        this.name=name;
        this.matchesPlayed=0;
    }

    public String getName(){
        return name;
    }

    public abstract void play();

    public int getMatchesPlayed(){
        return matchesPlayed;
    }

    public void incrementMatches(){
        this.matchesPlayed = this.matchesPlayed+1;
    }
}
