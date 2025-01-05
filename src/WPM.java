import java.time.LocalTime;
import java.util.Random;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

/**
 * The WPM (Words Per Minute) program is a simple typing test application that calculates the user's typing speed
 * based on the number of correctly typed words within a given period. The program generates random words for the user to type,
 * records the time taken, and calculates the Words Per Minute (WPM) based on the accuracy of the typed words.
 */
public class WPM {

    static String[] words = {"horizon", "whistle", "galaxy", "ripple", "ember", "velvet",
            "breeze", "canvas", "serendipity", "quirk", "wander", "prism", "echo", "luminary",
            "harbor", "mirage", "zenith", "glimmer", "solace", "cipher", "fragment", "anchor",
            "radiance", "whimsy", "lantern", "nebula", "aurora", "twilight", "shimmer",
            "voyage", "orbit"};

    /**
     * Main method that controls the flow of the typing test program.
     * - Displays a countdown to start the test.
     * - Generates random words and displays them to the user.
     * - Records the time taken for the user to type the words.
     * - Calculates WPM based on correct words typed.
     * - Displays WPM results and allows the user to retry or quit.
     *
     * @throws InterruptedException If the thread sleep is interrupted.
     */
    public static void main(String[] args) throws InterruptedException {

        Scanner scanner = new Scanner(System.in);
        boolean continuePlaying = true;

        // Game loop
        while (continuePlaying) {

            System.out.println("Ready");
            TimeUnit.SECONDS.sleep(1);
            System.out.println("Set");
            TimeUnit.SECONDS.sleep(1);
            System.out.println("Go!");
            TimeUnit.SECONDS.sleep(1);

            // Generate 10 random words
            Random random = new Random();
            StringBuilder generatedWords = new StringBuilder();
            for (int i = 0; i < 10; i++) {
                generatedWords.append(words[random.nextInt(30)]).append(" ");
            }
            String originalWords = generatedWords.toString().trim();
            System.out.println(originalWords);

            // Record start time and user input
            double start = LocalTime.now().toNanoOfDay();
            String typedWords = scanner.nextLine().trim();
            double end = LocalTime.now().toNanoOfDay();

            // Calculate elapsed time
            double elapsedTime = end - start;
            double seconds = elapsedTime / 1000000000.0;

            // Split the original and typed words for comparison
            String[] originalArray = originalWords.split(" ");
            String[] typedArray = typedWords.split(" ");

            // Count correctly typed words
            int correctWords = 0;
            for (int i = 0; i < Math.min(originalArray.length, typedArray.length); i++) {
                if (originalArray[i].equals(typedArray[i])) {
                    correctWords++;
                }
            }

            // Calculate WPM based on correct words
            int wpm = (int) (((double) correctWords / seconds) * 60);
            System.out.println("Your WPM is " + wpm + "!");

            // Display feedback
            int totalWords = originalArray.length;
            int mistakes = totalWords - correctWords;
            System.out.println("You typed " + correctWords + " words correctly out of " + totalWords + ".");
            System.out.println("You made " + mistakes + " mistakes.");

            System.out.println("\nWould you like to try again? (y/n): ");
            String userChoice = scanner.nextLine().trim().toLowerCase();

            if (userChoice.equals("n")) {
                continuePlaying = false;
                System.out.println("Thank you for playing!");
            }
        }
    }
}
