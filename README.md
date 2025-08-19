# Hangman-game

Jogo de forca (Hangman) em modo console. O objetivo é adivinhar a palavra secreta informando letras, enquanto acompanha o status do jogo e o progresso da palavra.

## Requisitos

- JDK 24 instalado e configurado no PATH
- Terminal/console

## Como funciona

- A palavra secreta é passada na linha de comando, letra por letra, como argumentos do programa.
- O jogo mostra um menu com opções para:
    1. Informar uma letra
    2. Verificar status do jogo
    3. Sair
- Ao informar uma letra:
    - Se a letra já tiver sido informada, o jogo avisa.
    - Se o jogo já estiver encerrado (vitória ou derrota), o jogo avisa e termina.
- A cada jogada, o estado atual da palavra e o status geral do jogo são exibidos.

## Exemplos de execução

- Rodando com a palavra “casa”:
  ```bash
  java -cp out hangman.Main c a s a
  ```

- Rodando com a palavra “java”:
  ```bash
  java -cp out hangman.Main j a v a
  ```

Durante a execução, você verá algo como:# Hangman-game
