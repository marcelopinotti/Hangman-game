package hangman;

import hangman.model.HangmanChar;
import hangman.model.HangmanGame;

import java.util.stream.Stream;

public class Main {
    public static void main(String... args) {
        var letter = Stream.of(args).map(a -> a.toLowerCase().charAt(0))
                .map(HangmanChar::new).toList();
        System.out.println(letter);
        var hangmanGame = new HangmanGame(letter);
        System.out.println(hangmanGame);
        hangmanGame.inputCharacter('t');
        System.out.println(hangmanGame);
        hangmanGame.inputCharacter('e');
        System.out.println(hangmanGame);
        hangmanGame.inputCharacter('d');
        System.out.println(hangmanGame);
        hangmanGame.inputCharacter('d');
        System.out.println(hangmanGame);
        hangmanGame.inputCharacter('e');
    }
}
