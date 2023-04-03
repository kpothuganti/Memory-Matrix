package memory_sequence.view;

import java.awt.*;
import javax.swing.*;

public class HomeScreenGUI {
    public HomeScreenGUI()
    {
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
        title.setAlignmentX(Component.CENTER_ALIGNMENT);
        title.setFont(new Font("Arial", Font.BOLD, 20));
        title.setForeground(new Color(255, 255, 255));
        boardPanel.add(title);

        JLabel question = new JLabel("Choose a Game Mode!");
        question.setAlignmentX(Component.CENTER_ALIGNMENT);
        question.setAlignmentY(Component.BOTTOM_ALIGNMENT);;
        question.setFont(new Font("Arial", Font.BOLD, 20));
        question.setForeground(new Color(255, 255, 255));


        boardPanel.add(question);

        JButton regular = new JButton("Regular");
        regular.setAlignmentX(Component.CENTER_ALIGNMENT);

        JButton expand = new JButton("Expand");
        expand.setAlignmentX(Component.CENTER_ALIGNMENT);

        JButton expert = new JButton("Expand");
        expert.setAlignmentX(Component.CENTER_ALIGNMENT);

        boardPanel.add(regular);
        boardPanel.add(expand);
        boardPanel.add(expert);

        mainPanel.add(boardPanel);
        mainFrame.add(mainPanel);
        mainFrame.setVisible(true);
    }
}
