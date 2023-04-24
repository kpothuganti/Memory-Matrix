package memory_sequence;

import memory_sequence.model.*;
import memory_sequence.controller.*;;

public class App {
    public static void main(String[] args) {
        // The controller creates the home screen where the user chooses a game mode
        // Based on the game mode chosen, the model object will be created
        // Creating the model object after choosing a mode allows for setting the max pattern and the model mode
        GameController controller = new GameController();
    }
}
