/**
 * Main entrypoint for program
 * @author Roy Portas
 */

import java.util.ArrayList;
import java.util.List;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.io.IOException;

public class Main {

    static String envFilename;
    static String queryFilename;
    static String outputFilename;
    static List<String> data;

    static private long startTime;
    static private double endTime;

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
                navAgent = new NavigationAgent(envMap);
                
            } catch (IOException e) {
                System.out.println("Could not read environment file");
            }

            // Read in the query file
            try {
                data = Files.readAllLines(Paths.get(queryFilename));
                int count = Integer.parseInt(data.remove(0));

                for (int i = 0; i < count; i++) {
                    String line = data.get(i);
                    startTime = System.nanoTime();

                    String[] lineData = line.split(" ");
                    String output = navAgent.runSearch(lineData[0],
                                Integer.parseInt(lineData[1]),
                                Integer.parseInt(lineData[2]));
                    endTime = (double) System.nanoTime() - (double) startTime; 

                    System.out.println(output + " Searched in " + endTime / 1000000 + "ms using " + lineData[0]);

                    // Add a newline character
                    output += "\n";

                    // Create the file
                    if (!Files.exists(Paths.get(outputFilename))) {
                        Files.createFile(Paths.get(outputFilename)); 
                    }
                    
                    try {
                        Files.write(Paths.get(outputFilename), output.getBytes(), StandardOpenOption.APPEND);
                    } catch (IOException e) {
                        System.out.println("Could not write file");
                        System.out.println(e.toString());
                    }
                

                }
            } catch (IOException e) {
                System.out.println("Could not read query file");
            }

        } else {
            System.out.println("Incorrect number of arguments");
        }
    }

}
