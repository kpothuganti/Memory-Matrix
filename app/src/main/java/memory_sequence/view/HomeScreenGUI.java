package memory_sequence.view;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

import memory_sequence.ControllerInterface;
import memory_sequence.model.*;
import memory_sequence.model.MemorySequence;

public class HomeScreenGUI {

    private ControllerInterface controller;
    private JFrame mainFrame;

    public HomeScreenGUI(ControllerInterface controller) {
        mainFrame = new JFrame("Memory Sequence");
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setSize(500, 600);

        JPanel mainPanel = new JPanel(new BorderLayout());

        JPanel buttonPanel = new JPanel(new BorderLayout());
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(45, 0, 0, 0));
        buttonPanel.setSize(200, 200);
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.Y_AXIS));
        buttonPanel.setBackground(new Color(0, 0, 139));

        JPanel boardPanel = new JPanel();
        boardPanel.setLayout(new BoxLayout(boardPanel, BoxLayout.Y_AXIS));
        boardPanel.setBackground(new Color(0, 0, 139));
        boardPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JLabel title = new JLabel("Memory Sequence Game", SwingConstants.CENTER);
        title.setAlignmentX(Component.CENTER_ALIGNMENT);
        title.setFont(new Font("Arial", Font.BOLD, 20));
        title.setForeground(new Color(255, 255, 255));
        boardPanel.add(title);

        JLabel question = new JLabel("Choose a Game Mode!");
        question.setAlignmentX(Component.CENTER_ALIGNMENT);
        question.setAlignmentY(Component.BOTTOM_ALIGNMENT);
        question.setFont(new Font("Arial", Font.BOLD, 20));
        question.setForeground(new Color(255, 255, 255));
        boardPanel.add(question);

        JButton regular = new JButton("Basic");
        regular.setAlignmentX(Component.CENTER_ALIGNMENT);
        regular.setPreferredSize(new Dimension(150, 20));
        regular.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                controller.userChoose(regular.getText());
                mainFrame.setVisible(false);
            }
        });

        JButton expand = new JButton("Expanded");
        expand.setAlignmentX(Component.CENTER_ALIGNMENT);
        expand.setPreferredSize(new Dimension(150, 20));
        expand.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                controller.userChoose("expand");
                mainFrame.setVisible(false);
            }
        });

        JButton advanced = new JButton("Advanced");
        advanced.setAlignmentX(Component.CENTER_ALIGNMENT);
        advanced.setPreferredSize(new Dimension(150, 20));
        advanced.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                controller.userChoose("advanced");
                mainFrame.setVisible(false);
            }
        });

        buttonPanel.add(regular);
        buttonPanel.add(Box.createRigidArea(new Dimension(0, 20)));
        buttonPanel.add(expand);
        buttonPanel.add(Box.createRigidArea(new Dimension(0, 20)));
        buttonPanel.add(advanced);

        boardPanel.add(buttonPanel);

        String gameInstructions = "<html><br/><br/><br/><br/>Basic Mode: There will be a 3x3 grid of buttons in which a pattern will be shown. Move forward in the game by memorizing the pattern of buttons and pressing accordingly. You will see the entire pattern for each new step.<br/><br/>Expanded Mode: Same instructions as Basic Mode, except with a larger grid.<br/><br/>Advanced Mode: Same instructions as the Basic Mode, except the entire pattern won't be shown anymore - only each new step.<br/><br/>For all modes, the game ends when the pattern is entered incorrectly.</html>";

        JLabel instructions = new JLabel(gameInstructions);
        instructions.setAlignmentX(Component.CENTER_ALIGNMENT);
        instructions.setAlignmentY(Component.BOTTOM_ALIGNMENT);
    
        instructions.setFont(new Font("Arial", Font.PLAIN, 13));
        instructions.setForeground(new Color(255, 255, 255));
        boardPanel.add(instructions);

        mainPanel.add(boardPanel);
        mainFrame.add(mainPanel);
        mainFrame.setVisible(true);
    }

    public void makeVisible() {
        this.mainFrame.setVisible(true);
    }
}
