package memory_sequence.controller;

import memory_sequence.ControllerInterface;
import memory_sequence.model.*;
import memory_sequence.view.*;

public class GameController implements ControllerInterface {
    MemorySequenceGUI gui;
    MemorySequence game;

    public GameController(MemorySequence game) {
        this.game = game;
        this.gui = new MemorySequenceGUI(this, game);
    }

    public void userPressed(char buttonvalue) {
        game.getClick(Character.getNumericValue(buttonvalue));

    }
}