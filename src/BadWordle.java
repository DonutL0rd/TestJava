import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;


public class BadWordle {
    public int correctLetters = 0;
    public boolean rightSpot;

    public static void main(String[] args) throws IOException {

        int randint = (int) (Math.random() * 2309);
        String randomLine = Files.readAllLines(Paths.get("wordleWordsList.txt")).get(randint).toLowerCase();

        char[] word = new char[randomLine.length()];

        for (int i = 0; i < 5; i++) {
            word[i] = randomLine.charAt(i);
            }
//            System.out.println(word);
        BadWordle w = new BadWordle();
        w.runit(word);

    }
    public void runit(char[] word) throws IOException {

        while(true){
            correctLetters = 0;
            Scanner scanner = new Scanner(System.in);
            System.out.println("enter your guess");
            String guessStr = scanner.nextLine().toLowerCase();
            char[] userGuess = new char[guessStr.length()];

            for (int i = 0; i < guessStr.length(); i++) {
                userGuess[i] = guessStr.charAt(i);
            }

            if (userGuess.length != 5) {
                System.out.println("Not a valid guess try again");
            }
            else {
                for (int j = 0; j < 5; j++) {
                    rightSpot = false;
                    if (userGuess[j] == word[j]) {
                        correctLetters++;
                        System.out.println("Letter " + (j + 1) + " is in the right spot");
                        rightSpot = true;
                    }
                    for (int k = 0; k < 5 && (!rightSpot) ; k++) {
                        if (userGuess[j] == word[k]){
                            System.out.println("Letter " + (j + 1) + " is in the word but in the wrong spot");
                            break;
                        }
                    }
                }
                if (correctLetters == 5) {
                    System.out.println("\nYou Guessed it!!");
                    break;
                } else {
                    System.out.println("\nWrong\n");
                }
            }
        }

    }
}