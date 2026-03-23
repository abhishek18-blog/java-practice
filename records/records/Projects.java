package records;

/* 
1. The "Shortcut"
Normally, in Java, even a simple class needs a lot of "extra" code to work properly (getters, constructors, etc.). A Record tells Java: "I just want to store these 2 or 3 things. You handle the rest."

Class: You have to build the whole car yourself (the engine, the seats, the wheels).

Record: You just say "I want a car with 4 seats," and Java hands you a finished one.

2. The Rules
To keep things simple, Records have two main rules:

Read-Only: Once you create a Record, you cannot change the data inside it (no setters).

No Secrets: Everything inside a Record is public and visible.

*/

// class Alien {
//   private final int id;
//   private final String name; // as we dont want to change it

//   public Alien(int id, String name) {
//     this.id = id;
//     this.name = name;

//   }

//   public int getId() {
//     return id;
//   }

//   public String getName() {
//     return name;
//   }

//   @Override
//   public String toString() {
//     return "Alien [id=" + id + ", name=" + name + "]";
//   }

// }

record Alien(int id, String name) {
}

public class Projects {

  public static void main(String[] args) {
    Alien a1 = new Alien(1, "abhi");
    Alien a2 = new Alien(0, "hb");
    System.out.println(a1);
    System.out.println(a2);

    // records only used for carrying data
    // records are class cannot extend other class
    // records can implement interface

  }
}