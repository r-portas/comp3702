package problem;

import java.util.*;
import java.awt.geom.Point2D;
import tester.Tester;

public class EnvironmentMap {
    private HashMap<ArmConfig, HashSet<ArmConfig>> edges;
    private Tester tester;

    public EnvironmentMap() {
        edges = new HashMap<ArmConfig, HashSet<ArmConfig>>();
        tester = new Tester();
    }

    /**
     * Takes in the samples from the Sampler class
     */
    public void generateMap(ArrayList<ArmConfig> samples) {

        // Clear the old edgemap
        edges = new HashMap<ArmConfig, HashSet<ArmConfig>>();
        calculateEdges(samples);
    }

    private void addToEdges(ArmConfig n1, ArmConfig n2) {
        HashSet<ArmConfig> innerNodes;

        if (edges.containsKey(n1)) {
            innerNodes = edges.get(n1);
            
            if (innerNodes.contains(n2)) {
                return;
            }

            // Its not in the list, so add it
            innerNodes.add(n2);
            edges.put(n1, innerNodes);

        } else {
            // The key isn't in the map
            innerNodes = new HashSet<ArmConfig>();
            innerNodes.add(n2);
            edges.put(n1, innerNodes);
        }
    }

    private void calculateEdges(ArrayList<ArmConfig> nodes) {

        //HashSet<GraphNode> indexed = new HashSet<GraphNode>();
        System.out.println("Starting edge calculation");

        for (ArmConfig node : nodes) {
            for (ArmConfig other : nodes) {
                if (! node.equals(other)) {
                    if (tester.isValidStep(node, other)) {
                        addToEdges(node, other); 
                        System.out.println("Found valid edge");
                    }
                }
            }
        }
    
        System.out.println("Edge calculation finished");
        System.out.println("EnvMap size: " + edges.size());
    }

}
