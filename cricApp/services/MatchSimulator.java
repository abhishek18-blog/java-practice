package services;

// Runnable is an interface used to define a task that can run in a separate thread
public class MatchSimulator implements Runnable {

    // Name of the match being simulated
    private final String matchName;

    // Static variable shared by all MatchSimulator objects
    // Stores total runs scored across all simulated matches
    private static int totalTournamentRuns = 0;

    // Constructor to initialize match name
    public MatchSimulator(String matchName) {
        this.matchName = matchName;
    }

    /*
     * synchronized:
     * Only one thread can execute this method at a time.
     * This prevents multiple threads from modifying
     * totalTournamentRuns simultaneously.
     *
     * static:
     * Method belongs to the class, not an object.
     */
    private static synchronized void addTournamentRuns(int runs) {

        // Add runs scored in a match to tournament total
        totalTournamentRuns += runs;

        // Display updated total
        System.out.println(
            "Tournament Total Runs updated to: " + totalTournamentRuns
        );
    }

    /*
     * run() contains the task that each thread performs.
     * When thread.start() is called, JVM executes this method.
     */
    @Override
    public void run() {

        // Display start message
        System.out.println(matchName + " has started.");

        try {

            /*
             * Pause current thread for 1000 milliseconds (1 second)
             * Simulates match processing time.
             */
            Thread.sleep(1000);

            /*
             * Generate random runs between 100 and 299
             *
             * Math.random() -> 0.0 to 0.999...
             * * 200 -> 0 to 199.999...
             * (int) -> 0 to 199
             * +100 -> 100 to 299
             */
            int runs = (int)(Math.random() * 200) + 100;

            // Update tournament total safely
            addTournamentRuns(runs);

        }
        catch (InterruptedException e) {

            /*
             * This exception occurs if another thread interrupts
             * the sleeping thread.
             */
            System.out.println(matchName + " was interrupted.");
        }

        // Display end message
        System.out.println(matchName + " has ended.");
    }
}