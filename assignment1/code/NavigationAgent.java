/**
 * The Navigvation Agent
 * @author Roy Portas
 */

import java.util.PriorityQueue;
import java.util.Comparator;
import java.lang.StringBuffer;
import java.util.List;
import java.util.ArrayList;

public class NavigationAgent {

    private EnvironmentMap envMap;

    private class Node {
        public int pos;
        public float cost;
        public Node parent;

        public Node(int pos, float cost) {
            this.pos = pos;
            this.cost = cost;
        }

        public void addParent(Node parent) {
            this.parent = parent;
        }
    }

    private class NodeComparator implements Comparator<Node> {
        @Override
        public int compare(Node x, Node y) {
            // Normally it is x - y, however we want to invert, to sort by lowest first
            if (x.cost < y.cost) {
                return 1;
            } else if (x.cost > y.cost) {
                return -1;
            } else {
                return 0;
            }
        }
    }

    public NavigationAgent(EnvironmentMap envMap) {
        this.envMap = envMap;

    }

    private String getPath(Node n) {

        String result = "";
        result += n.pos;

        Node parent = n.parent;
        while (parent != null) {
            
            result += "-";
            result += parent.pos;

            parent = parent.parent;
        }

        return new StringBuffer(result).reverse().toString() + "  (" + n.cost + ")";
    }

    private String runAStarSearch(int start, int end) {
        Comparator<Node> comp = new NodeComparator();
        PriorityQueue<Node> queue = new PriorityQueue<Node>(10, comp);
        ArrayList<Integer> expanded = new ArrayList<Integer>();
       
        // First create the start node
        Node temp = new Node(start, 0);
        queue.add(temp);

        while (true) {
            if (queue.size() == 0) {
                return "No matches found";
            }
            temp = queue.poll();
            expanded.add(temp.pos);


            if (temp.pos == end) {
                // Returns the path
                return getPath(temp);
            }
            List<Integer> connected = envMap.getConnected(temp.pos);

            // Add the new nodes
            for (Integer dest : connected) {
                // Create a new node
                if (!expanded.contains(dest)) {
                    Node child = new Node(dest, envMap.getEdgeCost(temp.pos, dest) + temp.cost);
                    child.parent = temp;
                    queue.add(child);
                }
            }

            // We don't add the old one back, since its not exactly required
            
        }
        
    }

    private String runUCSearch(int start, int end) {
        Comparator<Node> comp = new NodeComparator();
        PriorityQueue<Node> queue = new PriorityQueue<Node>(10, comp);
        ArrayList<Integer> expanded = new ArrayList<Integer>();
       
        // First create the start node
        Node temp = new Node(start, 0);
        queue.add(temp);

        while (true) {
            if (queue.size() == 0) {
                return "No matches found";
            }
            temp = queue.poll();
            expanded.add(temp.pos);

            System.out.println(temp.pos);

            if (temp.pos == end) {
                // Returns the path
                return getPath(temp);
            }
            List<Integer> connected = envMap.getConnected(temp.pos);

            // Add the new nodes
            for (Integer dest : connected) {
                // Create a new node
                if (!expanded.contains(dest)) {
                    Node child = new Node(dest, envMap.getEdgeCost(temp.pos, dest) + temp.cost);
                    child.parent = temp;
                    queue.add(child);
                }
            }

            // We don't add the old one back, since its not exactly required
            
        }

    }


    public String runSearch(String searchType, int start, int end) {
        if (searchType.equals("Uniform")) {
            return runUCSearch(start, end);
        }

        if (searchType.equals("A*")) {
            return runAStarSearch(start, end);
        }

        return "Invalid";
    }
}
