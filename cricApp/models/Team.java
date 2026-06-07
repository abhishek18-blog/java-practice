package models;

import exceptions.InvalidPlayerException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Team implements Serializable{
    private static final long serialVersionUID=1L;
    private final String teamName;
    private final List<Player> players;//interface ref. pointing to implementation

    public Team(String teamName){
        this.teamName=teamName;
        this.players=new ArrayList<>();


    }

    //exception handeling with throws keyword
    public void addPlayer(Player p) throws InvalidPlayerException{
        if(p.getName() == null || p.getName().trim().isEmpty()){
            throw new InvalidPlayerException("Player Name cannot be empty");

        }
        players.add(p);
    }



    public List<Player> getPlayers(){
        return players;
    }
    public String getTeamName(){
        return teamName;
    }

    // Inner class for team statistics
    public class TeamStats {
        public void displayStats() {
            System.out.println("Team: " + teamName + " has " + players.size() + " players");
        }

        public void printTeamSize(){
            System.out.println(teamName + " has " + players.size() + " players");
        }
    }
}