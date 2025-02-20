package memory_sequence;

import org.junit.Test;

import memory_sequence.model.MemorySequence;

import static org.junit.Assert.*;

import java.util.ArrayList;

public class MemorySequenceTest{
     @Test public void intialPatternSize() {
        MemorySequence game = new MemorySequence();
        game.setMode("basic");
        ArrayList<Integer> pattern = game.getPattern();
        
        assertEquals( 1, pattern.size());
    }

    @Test public void patternAfterGuesses() {
        MemorySequence game = new MemorySequence();
        game.setMode("basic");
        game.getClick(game.getPattern().get(0));
        game.getClick(game.getPattern().get(0));
        game.getClick(game.getPattern().get(1));
        ArrayList<Integer> pattern = game.getPattern();


        assertEquals( 3, pattern.size());        
    }

    @Test public void isGameOver(){
        MemorySequence game = new MemorySequence();
        game.setMode("basic");
        game.getClick(game.getPattern().get(0));
        game.getClick(10);
        

        assertEquals( true, game.isGameOver());

    }

    @Test public void notGameOver(){
        MemorySequence game = new MemorySequence();
        game.setMode("basic");
        game.getClick(game.getPattern().get(0));
        game.getClick(game.getPattern().get(0));
        game.getClick(game.getPattern().get(1));

        assertEquals( false, game.isGameOver());
    }  

    @Test public void getHighScore(){
        MemorySequence game = new MemorySequence();
        game.setMode("basic");
        game.getClick(game.getPattern().get(0));
        game.getClick(game.getPattern().get(0));
        game.getClick(game.getPattern().get(1));
        game.reset();
        game.getClick(game.getPattern().get(0));

        assertEquals( 2, game.getHighScore());

    }

    @Test public void getScore(){
        MemorySequence game = new MemorySequence();
        game.setMode("basic");
        game.getClick(game.getPattern().get(0));
        game.getClick(game.getPattern().get(0));
        game.getClick(game.getPattern().get(1));
        game.getClick(game.getPattern().get(0));
        game.getClick(game.getPattern().get(1));
        game.getClick(game.getPattern().get(2));

        assertEquals( 3 , game.getScore());
    }

    @Test public void setMode(){
        MemorySequence game = new MemorySequence();
        game.setMode("advanced");

        assertEquals( "advanced", game.getMode());
    }

    @Test public void testReset(){
        MemorySequence game = new MemorySequence();
        game.setMode("basic");
        game.getClick(game.getPattern().get(0));
        game.getClick(game.getPattern().get(0));
        game.getClick(game.getPattern().get(1));
        game.reset();

        assertEquals( 0, game.getScore());
        assertEquals( 0, game.getUserPattern().size());
    }
}