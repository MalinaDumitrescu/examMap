import java.io.IOException;
import java.util.List;
import java.util.Scanner;

public class ConsoleView {
    private final Controller controller;
    private final Scanner scanner;

    public ConsoleView(Controller controller) {
        this.controller = controller;
        this.scanner = new Scanner(System.in);
    }

    public void menu() throws IOException{
        while(true){
            System.out.println();
            System.out.println("1. Show all students whose names start with a specific letter");
            System.out.println("2. Show all students from Gryffindor");
            System.out.println("3. Show the results of the houses");
            System.out.println("4. Exit");
            System.out.print("Please enter your choice: ");
            String input = scanner.nextLine();

            switch (input){
                case "1":
                    handleStudentsByCapitalLetter();
                    break;
                case "2":
                    handleGryffindorStudents();
                    break;
                case "3":
                    printHouseResults();
                    break;
                case "4":
                    System.out.println("Exiting the application. Goodbye!");
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private void handleStudentsByCapitalLetter(){
        System.out.print("Please enter a capital letter: ");
        String letter = scanner.nextLine();
        if(letter.length() == 1 && Character.isUpperCase(letter.charAt(0))){
            List<String> students = controller.getStudentsByCapitalLetter(letter.charAt(0));
            students.forEach(System.out::println);
        } else {
            System.out.println("Invalid input. Please enter a single capital letter.");
        }
    }

    private void handleGryffindorStudents(){
        List<String> students = controller.handleGryffindorStudents();
        students.forEach(System.out::println);
    }

    private void printHouseResults() {
        controller.getHouseResults("src/logs/ergebnis.txt");
    }

}
