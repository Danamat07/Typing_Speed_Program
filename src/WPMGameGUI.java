import java.awt.*;
import java.awt.event.*;
import java.time.LocalTime;
import java.util.Random;
import javax.swing.*;

/**
 * The WPMGameGUI class is a typing test that calculates the user's words per minute (WPM)
 * based on random words. It tracks typing speed and accuracy, providing feedback.
 */
public class WPMGameGUI extends JFrame implements ActionListener {

    /**
     * An array of words that will be randomly selected for the typing test.
     */
    private static final String[] WORDS = {
            "horizon", "whistle", "galaxy", "ripple", "ember", "velvet",
            "breeze", "canvas", "serendipity", "quirk", "wander", "prism", "echo", "luminary",
            "harbor", "mirage", "zenith", "glimmer", "solace", "cipher", "fragment", "anchor",
            "radiance", "whimsy", "lantern", "nebula", "aurora", "twilight", "shimmer",
            "voyage", "orbit", "sapphire", "melody", "drift", "serenity", "cascade", "whisper",
            "spectra", "harmony", "phantom", "lullaby", "flicker", "pulse", "vivid", "flame",
            "dawn", "reverie", "luminous", "mystic", "echoes", "wisp", "vanguard"
    };

    private JLabel wordsLabel;
    private JTextField inputField;
    private JButton startButton;
    private JLabel resultLabel;
    private JLabel feedbackLabel;

    private String originalWords;
    private double startTime;

    /**
     * Constructs a new WPMGameGUI object, initializing the GUI components
     * and setting up the layout for the window.
     */
    public WPMGameGUI() {
        setTitle("WPM Typing Test");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 400);
        setLocationRelativeTo(null); // Center the window on the screen
        setLayout(new BorderLayout());

        // Top panel for displaying words
        JPanel topPanel = new JPanel(new GridBagLayout()); // Use GridBagLayout to center the text
        wordsLabel = new JLabel("Click Start to Begin");
        wordsLabel.setFont(new Font("SansSerif", Font.BOLD, 16));
        topPanel.add(wordsLabel);
        add(topPanel, BorderLayout.NORTH);

        // Center panel for user input
        JPanel centerPanel = new JPanel(new GridBagLayout()); // Use GridBagLayout to center the input field
        inputField = new JTextField(30);
        inputField.setFont(new Font("SansSerif", Font.PLAIN, 14));
        inputField.setEnabled(false);
        centerPanel.add(inputField);
        add(centerPanel, BorderLayout.CENTER);

        // Bottom panel for controls and feedback
        JPanel bottomPanel = new JPanel();
        startButton = new JButton("Start");
        startButton.addActionListener(this);
        bottomPanel.add(startButton);

        resultLabel = new JLabel(" ");
        feedbackLabel = new JLabel(" ");
        bottomPanel.add(resultLabel);
        bottomPanel.add(feedbackLabel);
        add(bottomPanel, BorderLayout.SOUTH);
    }

    /**
     * Handles button actions such as starting, submitting, and retrying the typing test.
     *
     * @param e The ActionEvent triggered by the button.
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == startButton) {
            if (startButton.getText().equals("Start") || startButton.getText().equals("Try Again")) {
                // Start the test
                generateWords();
                inputField.setEnabled(true);
                inputField.setText("");
                inputField.requestFocus();
                startTime = LocalTime.now().toNanoOfDay();
                startButton.setText("Submit");
                resultLabel.setText(" ");
                feedbackLabel.setText(" ");
            } else if (startButton.getText().equals("Submit")) {
                // End the test
                inputField.setEnabled(false);
                calculateResults();
                startButton.setText("Try Again");
            }
        }
    }

    /**
     * Generates a random set of words for the user to type.
     */
    private void generateWords() {
        Random random = new Random();
        StringBuilder row1 = new StringBuilder();
        for (int i = 0; i < 10; i++) {
            row1.append(WORDS[random.nextInt(WORDS.length)]).append(" ");
        }
        originalWords = row1.toString().trim();
        wordsLabel.setText(originalWords);
    }

    /**
     * Calculates and displays the results, including the words per minute (WPM),
     * the number of correct words, and the number of mistakes.
     */
    private void calculateResults() {
        double endTime = LocalTime.now().toNanoOfDay();
        double elapsedTime = endTime - startTime;
        double seconds = elapsedTime / 1_000_000_000.0;

        String typedWords = inputField.getText().trim();
        String[] originalArray = originalWords.replace("\n", " ").split(" ");
        String[] typedArray = typedWords.split(" ");

        int correctWords = 0;
        for (int i = 0; i < Math.min(originalArray.length, typedArray.length); i++) {
            if (originalArray[i].equals(typedArray[i])) {
                correctWords++;
            }
        }
        int wpm = (int) (((double) correctWords / seconds) * 60);
        resultLabel.setText("WPM: " + wpm);
        feedbackLabel.setText("Correct: " + correctWords + " | Mistakes: " + (originalArray.length - correctWords));
    }

    /**
     * The main method to launch the WPMGameGUI application.
     *
     * @param args Command line arguments (not used).
     */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            WPMGameGUI frame = new WPMGameGUI();
            frame.setVisible(true);
        });
    }
}