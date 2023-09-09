import java.io.*;
import java.util.*;

public class Task2 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Word Count Program");
        System.out.println("1. Enter text manually");
        System.out.println("2. Provide a file");
        System.out.print("Choose an option (1/2): ");
        int choice;
        try {
            choice = scanner.nextInt();
            scanner.nextLine();
        } catch (InputMismatchException e) {
            System.err.println("Invalid input. Please enter 1 or 2.");
            scanner.close();
            return;
        }
        String text = "";
        switch (choice) {
            case 1:
                System.out.print("Enter your text: ");
                text = scanner.nextLine();
                break;
            case 2:
                System.out.print("Enter the file path: ");
                String filePath = scanner.nextLine();
                try {
                    text = readFile(filePath);
                } catch (IOException e) {
                    System.err.println("Error reading the file: " + e.getMessage());
                    scanner.close();
                    System.exit(1);
                }
                break;
            default:
                System.err.println("Invalid choice. Exiting.");
                scanner.close();
                System.exit(1);
        }
        String[] words = text.split("[\\s\\p{Punct}]+");
        int wordCount = words.length;

        System.out.println("Total word count: " + wordCount);

        scanner.close();
    }
    private static String readFile(String filePath) throws IOException {
        StringBuilder content = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                content.append(line).append(" ");
            }
        }
        return content.toString();
    }
}
