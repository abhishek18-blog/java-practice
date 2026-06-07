package services;

import models.Team;
import java.io.*;

public class DataManager {
    public static void saveTeam(Team team, String filename) {
        ObjectOutputStream out = null;
        try {
            out = new ObjectOutputStream(new FileOutputStream(filename));
            out.writeObject(team);
            System.out.println("Team serialized successfully.");
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            // Finally block used for cleaning up resources 
            try {
                if (out != null) out.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static Team loadTeam(String filename) {
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(filename))) {
            return (Team) in.readObject();
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Could not load team. Starting fresh.");
            return null;
        }
    }
}