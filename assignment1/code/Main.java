/**
 * Main entrypoint for program
 * @author Roy Portas
 */

import java.util.ArrayList;
import java.util.List;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.io.IOException;

public class Main {

    static String envFilename;
    static String queryFilename;
    static String outputFilename;
    static List<String> data;

    static NavigationAgent navAgent;

    public static void main(String[] args) {

        if (args.length == 3) {
            // Start the algorithm
            envFilename = args[0];
            queryFilename = args[1];
            outputFilename = args[2];
        
            // Read in the map file
            try {
                data = Files.readAllLines(Paths.get(envFilename));
                
                int dimension = Integer.parseInt(data.get(0));
                data.remove(0);

                EnvironmentMap envMap = new EnvironmentMap(dimension, data);
                System.out.println(envMap);
                navAgent = new NavigationAgent(envMap);
                //System.out.println(navAgent.runSearch("A*", 1, 8));
                
            } catch (IOException e) {
                System.out.println("Could not read environment file");
            }

            // Read in the query file
            try {
                data = Files.readAllLines(Paths.get(queryFilename));
                data.remove(0);

                for (String line : data) {
                    String[] lineData = line.split(" ");
                    System.out.println(navAgent.runSearch(lineData[0],
                                Integer.parseInt(lineData[1]),
                                Integer.parseInt(lineData[2])));

                }
            } catch (IOException e) {
                System.out.println("Could not read query file");
            }

        } else {
            System.out.println("Incorrect number of arguments");
        }
    }

}
