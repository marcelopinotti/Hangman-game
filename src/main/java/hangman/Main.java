package hangman;

import hangman.exception.GameIsFinishedException;
import hangman.exception.LetterAlreadyInputtedException;
import hangman.model.HangmanChar;
import hangman.model.HangmanGame;

import java.util.Scanner;
import java.util.stream.Stream;

public class Main {
    private final static Scanner scanner = new Scanner(System.in);

    public static void main(String... args) {
        var letter = Stream.of(args).map(a -> a.toLowerCase().charAt(0))
                .map(HangmanChar::new).toList();
        System.out.println(letter);
        var hangmanGame = new HangmanGame(letter);
        System.out.println("Bem vindo ao jogo Hangman! Tente adivinhar a palavra.");
        System.out.println(hangmanGame);

        var option = -1;
        while (true) {
            System.out.println("Selecione uma das opções abaixo:");
            System.out.println("1- Informar uma letra.");
            System.out.println("2 - Verificar status do jogo.");
            System.out.println("3 - Sair do jogo.");
            option = scanner.nextInt();
            switch (option){
                case 1 -> inputCharacter(hangmanGame);
                case 2 -> showGameStatus(hangmanGame);
                case 3 -> System.exit(0);
                default -> System.out.println("Opção inválida! Tente novamente.");
            }

        }

    }

    private static void showGameStatus(HangmanGame hangmanGame) {
        System.out.println(hangmanGame.getStatus());
        System.out.println(hangmanGame);
    }

    private static void inputCharacter(HangmanGame hangmanGame) {
        System.out.println("Digite uma letra:");
        var character = scanner.next().charAt(0);
        try{
            hangmanGame.inputCharacter(character);

        } catch (LetterAlreadyInputtedException ex){
            System.out.println(ex.getMessage());
        }
        catch (GameIsFinishedException ex) {
            System.out.println(ex.getMessage());
            System.exit(0);
        }
        System.out.println(hangmanGame);
    }
}
