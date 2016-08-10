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

    static EnvironmentMap envMap;

    public static void main(String[] args) {

        if (args.length == 3) {
            // Start the algorithm
            envFilename = args[0];
            queryFilename = args[1];
            outputFilename = args[2];
        
            // Read in the text file
            try {
                data = Files.readAllLines(Paths.get(envFilename));
                
                int dimension = Integer.parseInt(data.get(0));
                data.remove(0);

                envMap = new EnvironmentMap(dimension, data);
                
            } catch (IOException e) {
                System.out.println("Could not read file: IOException");
            }

        } else {
            System.out.println("Incorrect number of arguments");
        }
    }

}
