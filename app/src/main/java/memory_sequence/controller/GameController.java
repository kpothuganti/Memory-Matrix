package memory_sequence.controller;

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

        game = new MemorySequence();
        
        if (mode.equals("expand")) {
            game.setGridSize(4);
            game.setMode("basic");
        } else if (mode.equals("advanced")) {
            game.setGridSize(3);
            game.setMode(mode);
        } else {
            game.setGridSize(3);
            game.setMode("basic");
        }
        this.gui = new MemorySequenceGUI(this, game);
    }

    public void returnHome() {
        hsgui.makeVisible();
    }
}