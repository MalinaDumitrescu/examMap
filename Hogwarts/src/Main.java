import model.Log;
import model.LogParser;
import model.LogParserFactory;

import java.util.List;

/**
 * The Main class is the entry point of the application.
 */
public class Main {
    /**
     * The main method that runs the application.
     *
     * @param args command-line arguments
     */
    public static void main(String[] args) {
        try {
            // Get a parser for XML files
            LogParser parser = LogParserFactory.getParser("xml");

            // Parse the log entries from the specified XML file
            List<Log> logEntries = parser.parse("src/logs/punkte.xml");

            // Create a controller with the parsed log entries
            Controller controller = new Controller(logEntries);

            // Create a console view with the controller and display the menu
            ConsoleView view = new ConsoleView(controller);
            view.menu();
        } catch (Exception e) {
            // Print the stack trace if an exception occurs
            e.printStackTrace();
        }
    }
}