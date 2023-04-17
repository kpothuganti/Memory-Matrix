package memory_sequence.view;

import java.awt.*;
import java.awt.event.ActionEvent;

import javax.swing.*;

import memory_sequence.model.MemorySequence;

public class MemorySequenceGUI implements ActionListener {
    private MemorySequence game;
    private HomeScreenGUI homeScreenGUI;

    public MemorySequenceGUI() {

        JFrame mainFrame = new JFrame("Memory Sequence");
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setSize(500, 500);

        JPanel mainPanel = new JPanel(new BorderLayout());

        // Board Panel will hold the game title and game board
        JPanel boardPanel = new JPanel();
        boardPanel.setLayout(new BoxLayout(boardPanel, BoxLayout.Y_AXIS));
        boardPanel.setBackground(new Color(0, 0, 139));
        boardPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // Title label for the game
        JLabel title = new JLabel("Memory Sequence Game", SwingConstants.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 20));
        title.setForeground(new Color(255, 255, 255));
        boardPanel.add(title);

        // Actual board with buttons
        JPanel gamePanel = new JPanel(new GridLayout(3, 3));
        gamePanel.setBackground(new Color(0, 0, 139));
        gamePanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        for (int i = 0; i < 9; i++) {
            JButton button = new JButton();
            if (i == 0) {
                button.setOpaque(true);
                button.setBorderPainted(false);
                button.setBackground(new Color(135, 206, 235)); // Hard coding some button colors for pattern
                                                                // demonstration
            } else {
                button.setOpaque(true);
                button.setBorderPainted(false);
                button.setBackground(new Color(0, 0, 0));
            }

            gamePanel.add(button);
        }

        boardPanel.add(gamePanel);

        // Control panel will alow the user to control game state with buttons
        JPanel controlPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        controlPanel.setBackground(new Color(192, 192, 192));

        JButton startButton = new JButton("Start");
        startButton.setEnabled(false); // To represent that Start is disabled once the game starts
        JButton resetButton = new JButton("Reset");

        controlPanel.add(startButton);
        controlPanel.add(resetButton);

        // To keep track of user score
        JLabel scoreLabel = new JLabel("Score: 0");
        scoreLabel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JLabel highScoreLabel = new JLabel("High Score: 101");
        highScoreLabel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Create a JPanel to hold both labels horizontally
        JPanel scorePanel = new JPanel();
        scorePanel.setLayout(new BoxLayout(scorePanel, BoxLayout.X_AXIS));
        scorePanel.add(scoreLabel);
        scorePanel.add(Box.createHorizontalStrut(300)); // Add some spacing between the labels
        scorePanel.add(highScoreLabel);

        mainPanel.add(boardPanel, BorderLayout.CENTER);
        mainPanel.add(controlPanel, BorderLayout.SOUTH);
        mainPanel.add(scorePanel, BorderLayout.NORTH);

        mainFrame.add(mainPanel);
        mainFrame.setVisible(true);
    }

    public void flashPattern() {
        // flash initial step in pattern
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        JButton button = (JButton) event.getSource();
        String text = button.getText();
    }

    @Override
    public void update() {
        if (game.isGameOver()) {
            // have screen say message that game is over
        }

        else if (game.getUserPattern().size() == game.getPattern().size()) {
            // disable all buttons
            // call flashPattern() --> either next step or entire pattern - depending on
            // game.getMode() being regular or advanced
        }
    }
}