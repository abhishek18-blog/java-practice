package models;
/*
What it is: A concrete class extending Player.
Why we use it: 
   Demonstrates the 'IS-A' relationship (Inheritance).
   It overrides the abstract method from the
   parent class, showcasing dynamic polymorphism.
*/

public class Batsman extends Player{
    private int runsScored;

    public Batsman(String name){
        super(name); //calls super class constructor
        this.runsScored=0;
    }

    public void addRuns(int runs){
        this.runsScored=runsScored+runs;
    }

    public int getRunsScored(){
        return runsScored;
    }

    @Override
    public void play(){
        System.out.println(getName()+" is batting!");
    }
    
    @Override
    public  String toString(){
        return getName() + "(Runs: " + runsScored + ")";
    }
}
