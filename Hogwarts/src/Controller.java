import model.*;

import java.io.BufferedWriter;
import java.io.IOException;
import java.util.*;
import java.io.FileWriter;
import java.util.stream.Collectors;

/**
 * The Controller manages the application logic.
 */
public class Controller {
    private final List<Log> logEntries;

    /**
     * Constructs a Controller with the specified list of log entries.
     *
     * @param logEntries the list of log entries to be managed by the controller
     */
    public Controller(List<Log> logEntries) {
        this.logEntries = logEntries;
    }

    /**
     * Retrieves a list of student names that start with the specified capital letter.
     *
     * @param letter the capital letter to filter student names by
     * @return a list of unique student names starting with the specified letter
     */
    public List<String> getStudentsByCapitalLetter(char letter) {
        return logEntries.stream()
                .filter(log -> log.getStudentName().startsWith(String.valueOf(letter)))
                .map(Log::getStudentName)
                .distinct()
                .collect(Collectors.toList());
    }

    /**
     * Retrieves a list of unique student names belonging to Gryffindor house, sorted alphabetically.
     *
     * @return a sorted list of unique Gryffindor student names
     */
    public List<String> handleGryffindorStudents(){
        return logEntries.stream()
                .filter(log -> log.getHouse() == House.GRYFFINDOR)
                .map(Log::getStudentName)
                .distinct()
                .sorted()
                .collect(Collectors.toList());
    }

    /**
     * Calculates the scores for each house and writes the sorted results to a file.
     *
     * @param filePath the path to the file where the results will be saved
     */
    public void getHouseResults(String filePath){
        Map<House, Integer> houseScores = new HashMap<>();

        // 1. Calculate the scores for each house
        for(Log entry : logEntries){
            House house = entry.getHouse();
            int points = entry.getPoints();

            // Add points to the corresponding house
            houseScores.put(house, houseScores.getOrDefault(house, 0) + points);
        }

        // 2. Sort the houses by their scores in descending order
        List<Map.Entry<House, Integer>> sortedHouseScores = new ArrayList<>(houseScores.entrySet());
        sortedHouseScores.sort((e1,e2)->e2.getValue().compareTo(e1.getValue())); // Descending order

        // 3. Write the sorted results to the file
        try(BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))){
            for(Map.Entry<House, Integer> entry : sortedHouseScores) {
                writer.write(entry.getKey() + "#" + entry.getValue());
                writer.newLine();
            }
            System.out.println("Results have been successfully saved to " + filePath);
        } catch (IOException e) {
            System.err.println("An error occurred while writing to the file: " + e.getMessage());
        }
    }
}








