import java.util.Random;
import java.util.Scanner;

class NumberGuessingGame {
    private int minRange;
    private int maxRange;
    private int maxAttempts;
    private Random random;
    private int targetNumber;

    public NumberGuessingGame(int minRange, int maxRange, int maxAttempts) {
        this.minRange = minRange;
        this.maxRange = maxRange;
        this.maxAttempts = maxAttempts;
        this.random = new Random();
    }

    public void start() {
        Scanner scanner = new Scanner(System.in);
        int rounds = 0;
        int totalAttempts = 0;

        System.out.println("Welcome to the Number Guessing Game!");

        do {
            targetNumber = random.nextInt(maxRange - minRange + 1) + minRange;
            int attempts = playRound(scanner);
            totalAttempts += attempts;
            rounds++;

            System.out.print("Do you want to play another round? (yes/no): ");
            String playAgain = scanner.next();
            if (!playAgain.equalsIgnoreCase("yes")) {
                break;
            }
        } while (true);

        scanner.close();

        if (rounds > 0) {
            double averageAttemptsPerRound = (double) totalAttempts / rounds;
            System.out.println("Game Over! You played " + rounds + " rounds and your average attempts per round were " + averageAttemptsPerRound);
        } else {
            System.out.println("Thanks for playing!");
        }
    }

    private int playRound(Scanner scanner) {
        int attempts = 0;

        System.out.println("\nRound " + (attempts + 1));
        System.out.println("I've selected a number between " + minRange + " and " + maxRange + ". Try to guess it!");

        while (attempts < maxAttempts) {
            System.out.print("Enter your guess: ");
            int userGuess = scanner.nextInt();
            attempts++;

            if (userGuess < minRange || userGuess > maxRange) {
                System.out.println("Your guess is out of the valid range.");
            } else if (userGuess < targetNumber) {
                System.out.println("Too low! Try again.");
            } else if (userGuess > targetNumber) {
                System.out.println("Too high! Try again.");
            } else {
                System.out.println("Congratulations! You guessed the number in " + attempts + " attempts.");
                return attempts;
            }

            if (attempts == maxAttempts) {
                System.out.println("Sorry, you've run out of attempts. The correct number was " + targetNumber + ".");
            }
        }

        return attempts;
    }
}

public class Task1 {
    public static void main(String[] args) {
        int minRange = 1;
        int maxRange = 100;
        int maxAttempts = 10;

        NumberGuessingGame game = new NumberGuessingGame(minRange, maxRange, maxAttempts);
        game.start();
    }
}

