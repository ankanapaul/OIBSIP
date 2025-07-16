import java.util.Random;
import java.util.Scanner;

public class GuessTheNumber {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random rand = new Random();

        int rounds = 3;
        int totalScore = 0;

        for (int round = 1; round <= rounds; round++) {
            int numberToGuess = rand.nextInt(100) + 1;
            int maxAttempts = 10;
            int attemptsUsed = 0;
            boolean hasGuessedCorrectly = false;

            System.out.println("Round " + round + ":");
            for (int i = 1; i <= maxAttempts; i++) {
                System.out.print("Enter your guess: ");
                int userGuess = scanner.nextInt();
                attemptsUsed++;

                if (userGuess < numberToGuess) {
                    System.out.println("Too low!");
                } else if (userGuess > numberToGuess) {
                    System.out.println("Too high!");
                } else {
                    System.out.println("Correct! You guessed the number in " + attemptsUsed + " attempts.");
                    totalScore += (maxAttempts - attemptsUsed) * 10;
                    hasGuessedCorrectly = true;
                    break;
                }
            }

            if (!hasGuessedCorrectly) {
                System.out.println("Sorry, you've used all attempts! The number was " + numberToGuess);
            }
            System.out.println("Score after round " + round + ": " + totalScore);
            System.out.println();
        }

        System.out.println("Game over! Your final score is: " + totalScore);
        scanner.close();
    }
}

