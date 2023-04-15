package memory_sequence.model;

import java.util.ArrayList;
import memory_sequence.GameObserver;

public class MemorySequence
{
    private int min;
    private int max;
    private String mode;
    private ArrayList<Integer> pattern = new ArrayList<Integer>();
    private ArrayList<Integer> userPattern = new ArrayList<Integer>();
    private ArrayList<GameObserver> observers = new ArrayList<GameObserver>();
    private int score;
    private boolean guessCheck;

    public MemorySequence(int gridSize, String mode)
    {
        this.min = 1;
        this.max = gridSize * gridSize;
        this.mode = mode;
        this.score = 0;
        this.generateStep(); // Sets up the initial step in the pattern for when the game starts
    }

    public void register(GameObserver observer)
    {
        observers.add(observer);
    }

    public void notifyObservers()
    {
        for (GameObserver observer: observers)
        {
            observer.update();
        }
    }

    public void generateStep()
    {
        int step = (int) (Math.random() * this.max) + min;
        this.pattern.add(step);
    }

    public void getClick(int click)
    {
        this.userPattern.add(click);
        this.validateGuess();
        this.notifyObservers();

    }

    public void validateGuess()
    {
        for (int i = 0; i < this.pattern.size(); i++)
        {
            if (this.userPattern.get(i) != this.pattern.get(i))
            {
                this.guessCheck = false;
                return;
            }
        }

        this.guessCheck = true;
        this.score += 1;
    }

    public boolean isGameOver()
    {
        return !this.guessCheck;
    }

    public ArrayList<Integer> getPattern()
    {
        return this.pattern;
    }

    public ArrayList<Integer> getUserPattern()
    {
        return this.userPattern;
    }

    public String getMode()
    {
        return this.mode;
    }

    public int getScore()
    {
        return this.score;
    }
}