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
    JLabel highScore;

    public MemorySequenceGUI(ControllerInterface controller, MemorySequence game) {

        this.controller = controller;
        this.game = game;

        this.game.register(this);

        mainFrame = new JFrame("Memory Matrix");
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setSize(500, 500);

        mainPanel = new JPanel(new BorderLayout());

        boardPanel = new JPanel();
        boardPanel.setLayout(new BoxLayout(boardPanel, BoxLayout.Y_AXIS));
        boardPanel.setBackground(new Color(0, 0, 139));
        boardPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        title = new JLabel("Memory Matrix", SwingConstants.CENTER);
        title.setAlignmentX(Component.CENTER_ALIGNMENT);
        title.setFont(new Font("Arial", Font.BOLD, 20));
        title.setForeground(new Color(255, 255, 255));
        boardPanel.add(title);

        gamePanel = new GamePanel(game, controller);
        boardPanel.add(gamePanel);

        controlPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        controlPanel.setBackground(new Color(192, 192, 192));

        startButton = new JButton("Start");
        resetButton = new JButton("Reset");
        resetButton.setEnabled(false);

        startButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                gamePanel.flashPattern();
                startButton.setEnabled(false);
                if (game.getResets() == 0){
                    resetButton.setEnabled(true);
                }
            }
        });
        
        controlPanel.add(startButton);
        controlPanel.add(resetButton);

        scoreLabel = new JLabel("Score: " + game.getScore());
        scoreLabel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        resetButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                startButton.setEnabled(true);
                resetButton.setEnabled(false);
                game.reset();
                scoreLabel.setText("Score: " + game.getScore());
            }
        });

        highScoreLabel = new JLabel("High Score: " + game.getHighScore());
        highScoreLabel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        scorePanel = new JPanel();
        scorePanel.setLayout(new BoxLayout(scorePanel, BoxLayout.X_AXIS));
        scorePanel.add(scoreLabel);
        scorePanel.add(Box.createHorizontalStrut(300));
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

            int scoreValue = game.getScore();
            score = new JLabel("Score: " + scoreValue);
            score.setFont(new Font("Arial", Font.BOLD, 20));
            score.setAlignmentX(Component.CENTER_ALIGNMENT);
            score.setForeground(Color.WHITE);

            int highScoreValue = game.getHighScore();
            highScore = new JLabel("High Score: " + highScoreValue);
            highScore.setFont(new Font("Arial", Font.BOLD, 20));
            highScore.setAlignmentX(Component.CENTER_ALIGNMENT);
            highScore.setForeground(Color.WHITE);

            JPanel lscreen = new JPanel(new BorderLayout());
            lscreen.setLayout(new BoxLayout(lscreen, BoxLayout.Y_AXIS));
            lscreen.setBorder(BorderFactory.createEmptyBorder(150, 0, 0, 0));
            lscreen.setBackground(new Color(0, 0, 139));
            lscreen.setAlignmentY(Component.CENTER_ALIGNMENT);

            lscreen.add(lost);
            lscreen.add(score);
            lscreen.add(highScore);
            mainPanel.add(lscreen);

            Timer timer = new Timer(2000, null);
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
            this.highScoreLabel.setText("High Score: " + game.getHighScore());
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