package hangman.model;

import hangman.exception.GameIsFinishedException;
import hangman.exception.LetterAlreadyInputtedException;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import static hangman.model.HangmanGameStatus.LOSE;
import static hangman.model.HangmanGameStatus.PENDING;
import static hangman.model.HangmanGameStatus.WIN;

public class HangmanGame {

    private final static int HANGMAN_INITAL_LENGTH = 9;
    private final static int HANGMAN_INITAL_LINE_SEPARATOR = 10;

    private final int lineSize;
    private final int hangInitialSize;
    private final List<Character> failAttempts = new ArrayList<>();
    private final List<HangmanChar> hangmanPaths;
    private final List<HangmanChar> characters;

    private String hangman;
    private HangmanGameStatus status;

    public HangmanGameStatus getStatus() {
        return status;
    }

    public HangmanGame(List<HangmanChar> letters) {
        var whiteSpaces = " ".repeat(letters.size());
        var letterSpace = "-".repeat(letters.size());
        this.lineSize =HANGMAN_INITAL_LINE_SEPARATOR + whiteSpaces.length();
        this.status = PENDING;
        this.hangmanPaths = buildHangmanPathsPosition();
        buildHangmanDesign(whiteSpaces, letterSpace);
        this.characters = setLetterSpacesPositionInGame(letters, whiteSpaces.length());
        this.hangInitialSize = hangman.length();

    }

    public void inputCharacter(final char letter) {
        if (this.status != PENDING) {
            var message = this.status == WIN ?
                    "Você ganhou!" :
                    "Você perdeu!, tente novamente!";
            throw new GameIsFinishedException(message);
        }
        var found = this.characters.stream()
                .filter(c -> c.getLetter() == letter).toList();

        if (this.failAttempts.contains(letter)) {
            throw new LetterAlreadyInputtedException("A letra '"+ letter + "' já foi digitada!");

        }


        if (found.isEmpty()) {
            failAttempts.add(letter);
            if(failAttempts.size() <= 6){
                this.status = LOSE;
            }
            rebuildHangman(this.hangmanPaths.removeFirst());
            return;
        }

        if (found.getFirst().isVisible()) {
            throw new LetterAlreadyInputtedException("A letra '"+ letter + "' já foi digitada!");

        }
        if (this.characters.stream().noneMatch(HangmanChar::isInvisible)) {
            this.status = WIN;
        }
        rebuildHangman(found.toArray(HangmanChar[]::new));

    }
    @Override
    public String toString() {
        return this.hangman;
    }

    private List<HangmanChar> buildHangmanPathsPosition() {
        final var HEAD_LINE = 3;
        final var BODY_LINE = 4;
        final var LEGS_LINE = 5;
        return new ArrayList<>(List.of(
                new HangmanChar('O', this.lineSize * HEAD_LINE + 6),
                new HangmanChar('|',this.lineSize*BODY_LINE +6),
                new HangmanChar('/',this.lineSize*BODY_LINE+5),
                new HangmanChar('\\',this.lineSize*BODY_LINE+7),
                new HangmanChar('/',this.lineSize*LEGS_LINE+5),
                new HangmanChar('\\',this.lineSize*LEGS_LINE+7)));
    }

    private List<HangmanChar> setLetterSpacesPositionInGame(List<HangmanChar> letters, final int whiteSpacesAmount){
        final var LINE_LETTER = 6;
        for (int i = 0; i < letters.size(); i++) {
            letters.get(i).setPosition(this.lineSize * LINE_LETTER+HANGMAN_INITAL_LENGTH+i);
        }
        return letters;
    }

    private void rebuildHangman(final HangmanChar... hangmanChars){
        var hangmanBuilder = new StringBuilder(this.hangman);
        Stream.of(hangmanChars).forEach(
                h -> hangmanBuilder.setCharAt(h.getPosition(), h.getLetter()));
        var failMessage =  this.failAttempts.isEmpty() ? "" :
                "Tentativas falhas: " + this.failAttempts;
        this.hangman = hangmanBuilder.substring(0, hangInitialSize) + failMessage;
        }

    private void buildHangmanDesign(final String whiteSpaces, final String letterSpace){
        this.hangman = "  -----  " + whiteSpaces + System.lineSeparator() +
        "  |   |  " + whiteSpaces + System.lineSeparator() +
        "  |   |  " + whiteSpaces + System.lineSeparator() +
        "  |      " + whiteSpaces + System.lineSeparator() +
        "  |      " + whiteSpaces + System.lineSeparator() +
        "  |      " + whiteSpaces + System.lineSeparator() +
        "  |      " + whiteSpaces + System.lineSeparator() +
        "+=======+" + letterSpace + System.lineSeparator();



    }

}

