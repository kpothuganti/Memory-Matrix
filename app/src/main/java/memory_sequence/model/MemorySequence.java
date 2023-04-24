package memory_sequence.model;

import java.util.ArrayList;

import memory_sequence.GameObserver;

public class MemorySequence {
    private int min;
    private int max;
    private String mode;
    private ArrayList<Integer> pattern = new ArrayList<Integer>();
    private ArrayList<Integer> userPattern = new ArrayList<Integer>();
    private ArrayList<GameObserver> observers = new ArrayList<GameObserver>();
    private int score;
    private boolean guessCheck;
    private int dimension;

    public MemorySequence() {
        this.min = 1;
        this.max = 9;
        this.score = 0;
        this.dimension = 3;
        this.mode = "basic";
        this.generateStep(); // Sets up the initial step in the pattern for when the game starts
    }

    public void register(GameObserver observer) {
        observers.add(observer);
    }

    public void notifyObservers() {
        for (GameObserver observer : observers) {
            observer.update();
        }
    }

    public void generateStep() {
        int step = (int) (Math.random() * this.max) + min;
        this.pattern.add(step);
    }

    public void getClick(int click) {
        this.userPattern.add(click);
        this.validateGuess();
        this.notifyObservers();

    }

    public void validateGuess() {
        for (int i = 0; i < this.userPattern.size(); i++) {
            if (this.userPattern.get(i) != this.pattern.get(i)) {
                this.guessCheck = false;
                this.pattern.clear();
                this.userPattern.clear();
                this.generateStep();
                return;
            }
        }

        // Once the round is over and each guess is correct, reset the userPattern,
        // update score, make next step
        if (this.userPattern.size() == this.pattern.size()) {
            this.generateStep();
            this.userPattern.clear();
            this.score += 1;
        }

        this.guessCheck = true;
    }

    public boolean isGameOver() {
        return !this.guessCheck;
    }

    public void setGridSize(int size) {
        this.max = size * size;
        this.dimension = size;
    }

    public ArrayList<Integer> getPattern() {
        return this.pattern;
    }

    public ArrayList<Integer> getUserPattern() {
        return this.userPattern;
    }

    public String getMode() {
        return this.mode;
    }

    public void setMode(String mode) {
        this.mode = mode;
    }

    public int getScore() {
        return this.score;
    }

    public int getSize() {
        return this.max;
    }

    public int getGridDimension() {
        return this.dimension;
    }
}