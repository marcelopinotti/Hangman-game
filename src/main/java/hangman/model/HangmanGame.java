package hangman.model;

import java.util.List;

import static hangman.model.HangmanGameStatus.PENDING;

public class HangmanGame {

    private final static int HANGMAN_INITAL_LENGTH = 9;
    private final static int HANGMAN_INITAL_LINE_SEPARATOR = 10;

    private final int lineSize;
    private final List<HangmanChar> characters;

    private String hangman;
    private HangmanGameStatus status;

    public HangmanGame(List<HangmanChar> letters) {
        var whiteSpaces = " ".repeat(letters.size());
        var letterSpace = "-".repeat(letters.size());
        this.lineSize =HANGMAN_INITAL_LINE_SEPARATOR + whiteSpaces.length();
        this.status = PENDING;
        buildHangmanDesign(whiteSpaces, letterSpace);
        this.characters = setLetterSpacesPositionInGame(letters, whiteSpaces.length());

    }

    @Override
    public String toString() {
        return this.hangman;
    }

    private List<HangmanChar> setLetterSpacesPositionInGame(List<HangmanChar> letters, final int whiteSpacesAmount){
        final var LINE_LETTER = 6;
        for (int i = 0; i < letters.size(); i++) {
            letters.get(i).setPosition(this.lineSize * LINE_LETTER+HANGMAN_INITAL_LENGTH+i);
        }
        return letters;
    }

    private void buildHangmanDesign(final String whiteSpaces, final String letterSpace){
        hangman = "  -----  " + whiteSpaces + System.lineSeparator() +
        "  |   |  " + whiteSpaces + System.lineSeparator() +
        "  |   |  " + whiteSpaces + System.lineSeparator() +
        "  |      " + whiteSpaces + System.lineSeparator() +
        "  |      " + whiteSpaces + System.lineSeparator() +
        "  |      " + whiteSpaces + System.lineSeparator() +
        "  |      " + whiteSpaces + System.lineSeparator() +
        "+=======+" + letterSpace + System.lineSeparator();



    }
}
