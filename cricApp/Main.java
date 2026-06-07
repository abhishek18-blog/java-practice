import exceptions.InvalidPlayerException;
import models.Batsman;
import models.Player;
import models.Team;
import services.DataManager;
import services.MatchSimulator;

import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;

public class Main {
    
    private static Team goaStrikers;
    private static Scanner scanner;
    
    // Static block gets executed exactly once when the class is loaded [cite: 22]
    static {
        System.out.println("=== INITIALIZING CRICKET TOURNAMENT RULES ===");
    }

    public static void main(String[] args) {
        scanner = new Scanner(System.in);
        goaStrikers = new Team("Goa Strikers");
        
        boolean running = true;
        
        while(running) {
            displayMenu();
            int choice = getUserInput();
            
            switch(choice) {
                case 1:
                    addPlayer();
                    break;
                case 2:
                    viewTeamMembers();
                    break;
                case 3:
                    sortAndDisplayPlayers();
                    break;
                case 4:
                    simulateMatches();
                    break;
                case 5:
                    saveTeamState();
                    break;
                case 6:
                    loadTeamState();
                    break;
                case 7:
                    displayTeamStats();
                    break;
                case 8:
                    System.out.println("\n=== EXITING CRICKET APPLICATION ===");
                    running = false;
                    break;
                default:
                    System.out.println("❌ Invalid choice! Please try again.\n");
            }
        }
        
        scanner.close();
    }
    
    private static void displayMenu() {
        System.out.println("\n╔════════════════════════════════════════╗");
        System.out.println("║     🏏 CRICKET TOURNAMENT MANAGER 🏏    ║");
        System.out.println("╚════════════════════════════════════════╝");
        System.out.println("1. Add Player");
        System.out.println("2. View Team Members");
        System.out.println("3. Sort & Display Players by Runs");
        System.out.println("4. Simulate Matches");
        System.out.println("5. Save Team State");
        System.out.println("6. Load Team State");
        System.out.println("7. Display Team Statistics");
        System.out.println("8. Exit");
        System.out.print("Enter your choice (1-8): ");
    }
    
    private static int getUserInput() {
        try {
            return Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            return -1;
        }
    }
    
    private static void addPlayer() {
        System.out.print("\nEnter player name: ");
        String name = scanner.nextLine().trim();
        
        if(name.isEmpty()) {
            System.out.println("❌ Error: Player name cannot be empty!\n");
            return;
        }
        
        System.out.print("Enter runs scored: ");
        int runs = 0;
        try {
            runs = Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("❌ Invalid input! Setting runs to 0.\n");
        }
        
        try {
            Batsman player = new Batsman(name);
            player.addRuns(runs);
            goaStrikers.addPlayer(player);
            System.out.println("✅ Player '" + name + "' added successfully with " + runs + " runs!\n");
        } catch (InvalidPlayerException e) {
            System.out.println("❌ Error: " + e.getMessage() + "\n");
        }
    }
    
    private static void viewTeamMembers() {
        if(goaStrikers.getPlayers().isEmpty()) {
            System.out.println("\n❌ No players in the team yet!\n");
            return;
        }
        
        System.out.println("\n╔════════════════════════════╗");
        System.out.println("║    TEAM MEMBERS - " + goaStrikers.getTeamName() + "    ║");
        System.out.println("╚════════════════════════════╝");
        
        int count = 1;
        for (Player p : goaStrikers.getPlayers()) {
            if (p instanceof Batsman) {
                System.out.println(count + ". " + p.toString());
                count++;
            }
        }
        System.out.println();
    }
    
    private static void sortAndDisplayPlayers() {
        if(goaStrikers.getPlayers().isEmpty()) {
            System.out.println("\n❌ No players in the team yet!\n");
            return;
        }
        
        // Sort by runs in descending order
        Collections.sort(goaStrikers.getPlayers(), new Comparator<Player>() {
            @Override
            public int compare(Player p1, Player p2) {
                return ((Batsman) p2).getRunsScored() - ((Batsman) p1).getRunsScored();
            }
        });
        
        System.out.println("\n╔════════════════════════════════════════╗");
        System.out.println("║  PLAYERS SORTED BY RUNS (DESCENDING)   ║");
        System.out.println("╚════════════════════════════════════════╝");
        
        int rank = 1;
        for (Player p : goaStrikers.getPlayers()) {
            p.play();
            System.out.println(rank + ". " + p.toString());
            rank++;
        }
        System.out.println();
    }
    
    private static void simulateMatches() {
        System.out.print("\nEnter number of matches to simulate (1-5): ");
        int numMatches = 1;
        try {
            numMatches = Integer.parseInt(scanner.nextLine());
            if(numMatches < 1 || numMatches > 5) {
                System.out.println("⚠️  Invalid number! Defaulting to 1 match.\n");
                numMatches = 1;
            }
        } catch (NumberFormatException e) {
            System.out.println("⚠️  Invalid input! Defaulting to 1 match.\n");
        }
        
        System.out.println("\n🎬 Starting match simulation...\n");
        Thread[] matches = new Thread[numMatches];
        
        for (int i = 0; i < numMatches; i++) {
            matches[i] = new Thread(new MatchSimulator("Match " + (i + 1)));
            matches[i].start();
        }
        
        try {
            for (int i = 0; i < numMatches; i++) {
                matches[i].join();
            }
        } catch (InterruptedException e) {
            System.out.println("❌ Match simulation interrupted: " + e.getMessage());
        }
        
        System.out.println("\n✅ All matches completed!\n");
    }
    
    private static void saveTeamState() {
        String filename = "team_state.ser";
        DataManager.saveTeam(goaStrikers, filename);
        System.out.println("✅ Team state saved to '" + filename + "'!\n");
    }
    
    private static void loadTeamState() {
        String filename = "team_state.ser";
        Team loadedTeam = DataManager.loadTeam(filename);
        
        if(loadedTeam != null) {
            goaStrikers = loadedTeam;
            System.out.println("✅ Team state loaded successfully!");
            System.out.println("   Team: " + goaStrikers.getTeamName() + " with " + goaStrikers.getPlayers().size() + " players.\n");
        } else {
            System.out.println("❌ Could not load team state.\n");
        }
    }
    
    private static void displayTeamStats() {
        if(goaStrikers.getPlayers().isEmpty()) {
            System.out.println("\n❌ No players in the team yet!\n");
            return;
        }
        
        Team.TeamStats stats = goaStrikers.new TeamStats();
        System.out.println();
        stats.displayStats();
        System.out.println();
    }
}