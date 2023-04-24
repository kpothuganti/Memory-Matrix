package memory_sequence.view;

import java.awt.*;
import java.awt.event.*;
import java.lang.reflect.Array;

import javax.swing.*;
import javax.swing.text.html.parser.ContentModel;

import memory_sequence.ControllerInterface;
import memory_sequence.model.*;
import memory_sequence.GameObserver;
import memory_sequence.model.MemorySequence;

public class MemorySequenceGUI implements GameObserver {
    private MemorySequence game;
    private ControllerInterface controller;

    // All components
    JFrame mainFrame;
    JPanel mainPanel;
    JPanel boardPanel;
    JLabel title;
    JPanel controlPanel;
    GamePanel gamePanel;
    JButton startButton;
    JButton resetButton;
    JLabel highScoreLabel;
    JPanel scorePanel;
    JLabel scoreLabel;
    JLabel lost;
    JLabel score;

    public MemorySequenceGUI(ControllerInterface controller, MemorySequence game) {

        this.controller = controller;
        this.game = game;

        this.game.register(this);

        mainFrame = new JFrame("Memory Sequence");
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setSize(500, 600);

        mainPanel = new JPanel(new BorderLayout());

        // Board Panel will hold the game title and game board
        boardPanel = new JPanel();
        boardPanel.setLayout(new BoxLayout(boardPanel, BoxLayout.Y_AXIS));
        boardPanel.setBackground(new Color(0, 0, 139));
        boardPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // Title label for the game
        title = new JLabel("Memory Sequence Game", SwingConstants.CENTER);
        title.setAlignmentX(Component.CENTER_ALIGNMENT);
        title.setFont(new Font("Arial", Font.BOLD, 20));
        title.setForeground(new Color(255, 255, 255));
        boardPanel.add(title);

        gamePanel = new GamePanel(game, controller);
        boardPanel.add(gamePanel);

        // Control panel will alow the user to control game state with buttons
        controlPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        controlPanel.setBackground(new Color(192, 192, 192));

        startButton = new JButton("Start");
        startButton.setEnabled(true); // To represent that Start is disabled once the game starts

        startButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                gamePanel.flashPattern();
                startButton.setEnabled(false);
            }
        });

        resetButton = new JButton("Reset");

        controlPanel.add(startButton);
        controlPanel.add(resetButton);

        // To keep track of user score
        scoreLabel = new JLabel("Score: " + game.getScore());
        scoreLabel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        highScoreLabel = new JLabel("High Score: 101");
        highScoreLabel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Create a JPanel to hold both labels horizontally
        scorePanel = new JPanel();
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

    @Override
    public void update() {
        if (game.isGameOver()) {
            boardPanel.setVisible(false);

            lost = new JLabel("YOU LOST THE GAME!");
            lost.setFont(new Font("Arial", Font.BOLD, 20));
            lost.setAlignmentX(Component.CENTER_ALIGNMENT);
            lost.setForeground(Color.WHITE);

            int value = game.getScore();
            score = new JLabel("Score: " + value);
            score.setFont(new Font("Arial", Font.BOLD, 20));
            score.setAlignmentX(Component.CENTER_ALIGNMENT);
            score.setForeground(Color.WHITE);

            JPanel lscreen = new JPanel(new BorderLayout());
            lscreen.setLayout(new BoxLayout(lscreen, BoxLayout.Y_AXIS));
            lscreen.setBorder(BorderFactory.createEmptyBorder(150, 0, 0, 0));
            lscreen.setBackground(new Color(0, 0, 139));
            lscreen.setAlignmentY(Component.CENTER_ALIGNMENT);

            lscreen.add(lost);
            lscreen.add(score);
            mainPanel.add(lscreen);

            Timer timer = new Timer(3000, null);
            timer.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    mainFrame.dispose();
                    controller.returnHome();
                    timer.stop();
                }
            });
            timer.start();

        }

        else if (game.getUserPattern().size() == 0) {
            this.scoreLabel.setText("Score: " + game.getScore());

            // Adding a slight delay before showing the pattern of the next round
            Timer timer = new Timer(400, null);
            timer.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    gamePanel.flashPattern();
                    timer.stop();
                }
            });
            timer.start();
        }
    }
}