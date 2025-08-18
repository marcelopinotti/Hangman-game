package hangman.model;

import java.util.Objects;

public class HangmanChar {
    private final char letter;
    private boolean isVisible;
    private int position;


    public HangmanChar(char letter) {
        this.letter = letter;
        this.isVisible = false;
    }

    public HangmanChar(char letter, int position) {
        this.letter = letter;
        this.position = position;
        this.isVisible = true;
    }

    public char getLetter() {
        return letter;
    }

    public boolean isVisible() {
        return isVisible;
    }

    public boolean isInvisible() {
        return !isVisible;
    }

    public void enableVisibility() {
        isVisible = true;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof HangmanChar that)) return false;
        return letter == that.letter && isVisible == that.isVisible && position == that.position;
    }

    @Override
    public int hashCode() {
        return Objects.hash(letter, isVisible, position);
    }

    @Override
    public String toString() {
        return "HangmanChar{" +
                "letter=" + letter +
                ", isVisible=" + isVisible +
                ", position=" + position +
                '}';
    }
}
