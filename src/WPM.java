import java.time.LocalTime;
import java.util.Random;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class WPM {

    static String[] words = {"horizon", "whistle", "galaxy", "ripple", "ember", "velvet",
            "breeze", "canvas", "serendipity", "quirk", "wander", "prism", "echo", "luminary",
            "harbor", "mirage", "zenith", "glimmer", "solace", "cipher", "fragment", "anchor",
            "radiance", "whimsy", "lantern", "nebula", "aurora", "twilight", "shimmer",
            "voyage", "orbit"};

    public static void main (String[] args) throws InterruptedException {

        System.out.println("Ready");
        TimeUnit.SECONDS.sleep(1);
        System.out.println("Set");
        TimeUnit.SECONDS.sleep(1);
        System.out.println("Go!");
        TimeUnit.SECONDS.sleep(1);

        Random random = new Random();
        for (int i = 0; i < 10; i++) {
            System.out.print(words[random.nextInt(29)] + " ");
        }
        System.out.println();

        double start = LocalTime.now().toNanoOfDay();
        Scanner scanner = new Scanner(System.in);
        String typedWords = scanner.nextLine();
        double end = LocalTime.now().toNanoOfDay();
        double elapsedTime = end - start;
        double seconds = elapsedTime / 1000000000.0;
        int numChars = typedWords.length();
        int wpm = (int) ((((double) numChars / 5) / seconds) * 60);
        System.out.println("Your wpm is " + wpm + "!");
    }
}
