package memory_sequence.controller;

import java.io.*;

import memory_sequence.ControllerInterface;
import memory_sequence.model.*;
import memory_sequence.view.*;

public class GameController implements ControllerInterface {
    MemorySequenceGUI gui;
    MemorySequence game;
    HomeScreenGUI hsgui;

    public GameController() {
        this.hsgui = new HomeScreenGUI(this);
    }

    public void userPressed(String buttonvalue) {
        game.getClick(Integer.parseInt(buttonvalue));
    }

    public void userChoose(String mode) {

        loadState();
        int highScore = game.getHighScore();

        game = new MemorySequence();
        
        if (mode.equals("expand")) {
            game.setGridSize(4);
        } else if (mode.equals("advanced")) {
            game.setMode(mode);
        }
        
        game.setHighScore(highScore); 
        this.gui = new MemorySequenceGUI(this, game);
    }

    public void returnHome() {
        saveState();
        hsgui.makeVisible();
    }

    public void saveState() {
        try {
            FileOutputStream fileOut = new FileOutputStream("highscore.txt");
            ObjectOutputStream objectOut = new ObjectOutputStream(fileOut);
            objectOut.writeObject(game);
            objectOut.close();
            fileOut.close();
        } catch (IOException error) {
            System.out.println(error.getMessage());
        }
    }

    public void loadState() {
        try {
            File file = new File("highscore.txt");
            if (file.exists()) {
                FileInputStream fileIn = new FileInputStream(file);
                ObjectInputStream objectIn = new ObjectInputStream(fileIn);
                game = (MemorySequence) objectIn.readObject();
                objectIn.close();
                fileIn.close();
            } else {
                game = new MemorySequence();
            }
        } catch (Exception error) {
            System.out.println(error.getMessage());
        }
    }
}
