package memory_sequence;

import memory_sequence.model.*;
import memory_sequence.controller.*;;

public class App {
    public static void main(String[] args) {
        MemorySequence game = new MemorySequence();
        GameController controller = new GameController(game);
    }
}
