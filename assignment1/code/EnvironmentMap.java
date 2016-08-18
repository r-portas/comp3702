/**
 * A class that represents the environment
 *
 * IMPLEMENTATION NOTE:
 *  This was created using a two dimensional hashmap, allow O(1) lookup from a given (i, j) tuple
 *  Additionally since the algorithm only insert non-0 edges, getting the keys for the outer hashmap 
 *  will return a list containing ONLY valid paths, meaning lookup is very fast
 *
 * @author Roy Portas
 */

import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;
import java.lang.String;

public class EnvironmentMap {

    private int dimension;
    private HashMap<Integer, HashMap<Integer, Float>> costMap;

    public EnvironmentMap(int dimension, List<String> matrix) {
        this.dimension = dimension;
        costMap = new HashMap<Integer, HashMap<Integer, Float>>();

        for (int i = 0; i < dimension; i++) {
            String row = matrix.get(i);

            String[] items = row.split(" ");
            for (int j = 0; j < items.length; j++) {
                addToMap(i + 1, j + 1, Float.valueOf(items[j]));
            }
        }

        System.out.println("--- Environment Map ---");
        System.out.println(costMap);
    }

    /**
     * Adds a link between point I and J with associated cost
     */
    private void addToMap(int i, int j, float cost) {

        HashMap<Integer, Float> temp;

        if (cost != 0) {

            // Only add it if there is a valid route between the two points

            // Check if the key exists
            if (costMap.containsKey(i)) {
                // Get the inner hashmap and put the value into it
                temp = costMap.get(i);
                temp.put(j, cost);
            } else {
                // It doesn't exist, so create it
                temp = new HashMap<Integer, Float>();
                temp.put(j, cost);

                costMap.put(i, temp);
            }
        }
    }

    /**
     * Returns the edge cost for a give I and J value
     */
    public float getEdgeCost(int i, int j) {
        if (costMap.containsKey(i)) {
            return costMap.get(i).get(j);   
        }

        return 0;
    }

    /**
     * Gets the connected entries
     */
    public List<Integer> getConnected(int i) {
        List arr;
        if (costMap.containsKey(i)) {
            arr = new ArrayList<Integer>(costMap.get(i).keySet());
        } else {
            arr = new ArrayList<Integer>();
        }
        return arr;
    }

    /**
     * Generates the heuristic by finding the max cost to all children of a single node
     */
    public HashMap<Integer, Float> generateHeuristic() {
        HashMap<Integer, Float> heuristic = new HashMap<Integer, Float>();

        List<Integer> keys = new ArrayList<Integer>(costMap.keySet());
        for (Integer key : keys) {
            
            float min = 1000;
            List<Integer> children = getConnected(key);
            for (Integer child : children) {
                float temp = getEdgeCost(key, child);
                if (temp < min) {
                    min = temp;
                }
            }

            heuristic.put(key, min);

        }

        return heuristic;
    }
}
