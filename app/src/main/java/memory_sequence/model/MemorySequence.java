package memory_sequence.model;

import java.io.Serializable;
import java.util.ArrayList;

import memory_sequence.GameObserver;

public class MemorySequence implements Serializable {
    private transient int min;
    private transient int max;
    private transient String mode;
    private transient ArrayList<Integer> pattern = new ArrayList<Integer>();
    private transient ArrayList<Integer> userPattern = new ArrayList<Integer>();
    private transient ArrayList<GameObserver> observers = new ArrayList<GameObserver>();
    private transient int score;
    private transient boolean guessCheck;
    private transient int dimension;

    private int highScore;

    public MemorySequence() {
        this.min = 1;
        this.max = 9;
        this.score = 0;
        this.dimension = 3;
        this.mode = "basic";
        this.generateStep();
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
                return;
            }
        }

        if (this.userPattern.size() == this.pattern.size()) {
            this.generateStep();
            this.userPattern.clear();
            this.score += 1;

            if (this.score > this.highScore) {
                this.highScore = this.score;
            }
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

    public void setHighScore(int score) {
        this.highScore = score;
    }

    public int getHighScore() {
        return this.highScore;
    }
    
    public void reset() {
        this.userPattern.clear();
        this.pattern.clear();
        this.generateStep();
        this.score = 0;
    }
}