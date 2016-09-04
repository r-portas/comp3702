package problem;

import java.util.*;

public class EnvironmentMap {
    private HashMap<GraphNode, HashSet<GraphNode>> edges;

    public EnvironmentMap() {
        edges = new HashMap<GraphNode, HashSet<GraphNode>>();
    }

    /**
     * Takes in the samples from the Sampler class
     */
    public void generateMap(ArrayList<double[]> samples) {

        // Clear the old edgemap
        edges = new HashMap<GraphNode, HashSet<GraphNode>>();

        ArrayList<GraphNode> nodes = new ArrayList<GraphNode>();
        GraphNode temp;

        // Create the GraphNode objects
        for (double[] sample : samples) {
            temp = new GraphNode(sample);
            nodes.add(temp);
        }

        calculateEdges(nodes);
    }

    private void addToEdges(GraphNode n1, GraphNode n2) {
        HashSet<GraphNode> innerNodes;

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
            innerNodes = new HashSet<GraphNode>();
            innerNodes.add(n2);
            edges.put(n1, innerNodes);
        }
    }

    private void calculateEdges(ArrayList<GraphNode> nodes) {

        //HashSet<GraphNode> indexed = new HashSet<GraphNode>();
        System.out.println("Starting edge calculation");

        for (GraphNode node : nodes) {
            for (GraphNode other : nodes) {
                if (! node.equals(other)) {
                    if (node.isNearby(other)) {
                        addToEdges(node, other); 
                    }
                }
            }
        }
    
        System.out.println("Edge calculation finished");
        System.out.println("EnvMap size: " + edges.size());
    }

}
