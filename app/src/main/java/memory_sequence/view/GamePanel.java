package memory_sequence.view;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

import javax.swing.*;

import memory_sequence.model.MemorySequence;
import memory_sequence.ControllerInterface;

public class GamePanel extends JPanel implements ActionListener {
    private MemorySequence game;
    private ControllerInterface controller;
    private ArrayList<JButton> buttons = new ArrayList<>();

    public GamePanel(MemorySequence game, ControllerInterface controller) {
        this.game = game;
        this.controller = controller;

        this.setLayout(new GridLayout(this.game.getGridDimension(), this.game.getGridDimension()));
        this.setBackground(new Color(0, 0, 139));
        this.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        for (int i = 1; i < this.game.getSize() + 1; i++) {
            JButton button = new JButton(String.valueOf(i));
            button.setOpaque(true);
            button.setBorderPainted(true);
            button.setBackground(new Color(0, 0, 0));
            button.setBorder(BorderFactory.createLineBorder(Color.WHITE));
            button.setEnabled(false);
            button.addActionListener(this);
            buttons.add(button);
            this.add(button);
        }
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        JButton button = (JButton) event.getSource();
        String text = button.getText();
        controller.userPressed(text);
    }

    public void flashPattern() {
        ArrayList<Integer> pattern = game.getPattern();

        for (JButton button : buttons) {
            button.setEnabled(false);
        }

        if (game.getMode().equals("advanced")) {
            if (!pattern.isEmpty()) {
                int recentStep = pattern.get(pattern.size() - 1);
                buttons.get(recentStep - 1).setBackground(new Color(135, 206, 235)); // flash only the most recent step
                Timer timer = new Timer(500, null);
                timer.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        buttons.get(recentStep - 1).setBackground(new Color(0, 0, 0)); // turn off the flash
                        for (JButton button : buttons) {
                            button.setEnabled(true); // enable all buttons
                        }
                        timer.stop();
                    }
                });
                timer.start();
            }
        }

        if (game.getMode().equals("basic")) {
            Timer timer = new Timer(300, null);
            timer.addActionListener(new ActionListener() {
                int index = 0;
                boolean toggle = true;

                @Override
                public void actionPerformed(ActionEvent e) {
                    if (index < pattern.size()) {
                        int buttonIndex = pattern.get(index) - 1;
                        if (toggle) {
                            buttons.get(buttonIndex).setBackground(new Color(135, 206, 235));
                        } else {
                            buttons.get(buttonIndex).setBackground(new Color(0, 0, 0));
                            index++;
                        }
                        toggle = !toggle;
                    } else {
                        for (JButton button : buttons) {
                            button.setEnabled(true);
                        }
                        timer.stop();
                    }
                }
            });
            timer.start();
        }
    }
}
