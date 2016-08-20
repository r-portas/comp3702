/**
 * The Navigvation Agent
 * @author Roy Portas
 */

import java.util.PriorityQueue;
import java.util.Comparator;
import java.lang.StringBuffer;
import java.util.List;
import java.util.Stack;
import java.util.ArrayList;
import java.util.HashMap;

public class NavigationAgent {

    private EnvironmentMap envMap;

    private class Node {
        public int pos;
        public float cost;
        
        // Used for A*
        public float actCost;

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
            if (x.cost > y.cost) {
                return 1;
            } else if (x.cost < y.cost) {
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

        Stack<Integer> st = new Stack<Integer>();
        float finalCost = n.cost;

        while (n != null) {
            st.push(n.pos);
            
            n = n.parent;
        }

        int depth = st.size();

        String output = "";
        while (!st.empty()) {
            
            output += st.pop();

            if (!st.empty()) {
                output += "-";
            }
        }

        output += String.format(" cost %.2f", finalCost);
        output += " at depth ";
        output += depth - 1;

        return output;
        
    }

    private String runAStarSearch(int start, int end) {
        Comparator<Node> comp = new NodeComparator();
        PriorityQueue<Node> queue = new PriorityQueue<Node>(10, comp);
        ArrayList<Integer> expanded = new ArrayList<Integer>();
        HashMap<Integer, Float> heuristic = envMap.generateHeuristic();

        // First create the start node
        Node temp = new Node(start, 0);
        temp.actCost = 0;
        queue.add(temp);

        while (true) {
            if (queue.size() == 0) {
                return "No matches found";
            }
            temp = queue.poll();
            expanded.add(temp.pos);


            if (temp.pos == end) {
                // Returns the path

                // Set the actual cost
                temp.cost = temp.actCost;
                return getPath(temp);
            }
            List<Integer> connected = envMap.getConnected(temp.pos);

            // Add the new nodes
            for (Integer dest : connected) {
                // Create a new node
                if (!expanded.contains(dest)) {
                    // Add the heuristic
                    Float h = heuristic.get(dest);

                    if (h == null) {
                        h = new Float(0);
                    }

                    Node child = new Node(dest, envMap.getEdgeCost(temp.pos, dest) + temp.cost + h/10);
                    child.actCost = envMap.getEdgeCost(temp.pos, dest) + temp.actCost;
                    child.parent = temp;
                    queue.add(child);
                }
            }

            // We don't add the old one back, since its not exactly required
            //System.out.println("Smallest item is " + queue.peek().pos + " [cost " + queue.peek().cost + "]");
            
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

            //System.out.println(temp.pos);

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
