/**
 * BFS Search
 * @author Roy Portas
 */

import java.util.*;

public class BFSSearch {
    public static void main(String[] args) {
        runBFS("1348627_5", "1238_4765");
    }

    static void runBFS(String initial, String goal) {

        Node node = new Node(initial, goal);
        LinkedList<Node> nodes = new LinkedList<Node>();
        ArrayList<Node> visitedNodes = new ArrayList<Node>();
        
        nodes.push(node);

        while (true) {
            
            Node item = nodes.pop();
            visitedNodes.add(item);

            System.out.println(item);

            if (item.isFinished()) {
                System.out.println("Found solution");
                break;
            }

            ArrayList<Node> neighbors = item.getNeighbors();
            for (Node neighbor : neighbors) {

                if (!visitedNodes.contains(neighbor)) {
                    nodes.push(neighbor);
                }

            }
        }
    }

}
