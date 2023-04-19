package memory_sequence.view;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

import memory_sequence.ControllerInterface;
import memory_sequence.model.*;
import memory_sequence.model.MemorySequence;


public class HomeScreenGUI implements ActionListener {

    private ControllerInterface controller;

    public HomeScreenGUI(ControllerInterface controller)
    {
        JFrame mainFrame = new JFrame("Memory Sequence");
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setSize(500, 500);

        JPanel mainPanel = new JPanel(new BorderLayout());

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
        question.setAlignmentY(Component.BOTTOM_ALIGNMENT);;
        question.setFont(new Font("Arial", Font.BOLD, 20));
        question.setForeground(new Color(255, 255, 255));
        boardPanel.add(question);

        JButton regular = new JButton("basic");
        regular.setAlignmentX(Component.CENTER_ALIGNMENT);
        regular.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                controller.userChoose(regular.getText());
            }
        });

        JButton expand = new JButton("expand");
        expand.setAlignmentX(Component.CENTER_ALIGNMENT);
        expand.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                controller.userChoose("expand");
            }
        });

        JButton advanced = new JButton("advanced");
        advanced.setAlignmentX(Component.CENTER_ALIGNMENT);
        advanced.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e){
                controller.userChoose("advanced");
            }
        });

        boardPanel.add(regular);
        boardPanel.add(expand);
        boardPanel.add(advanced);

        mainPanel.add(boardPanel);
        mainFrame.add(mainPanel);
        mainFrame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        //work with this later
    }
}
