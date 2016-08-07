/**
 * Represents a node in the 8 puzzle problem
 * @author Roy Portas
 */

import java.lang.String;
import java.util.ArrayList;

public class Node {
    
    String layout;
    String goalLayout;
    int blankIndex;
    int depth;

    public Node(String layout, String goalLayout) {
        this.layout = layout;
        this.goalLayout = goalLayout;

        this.blankIndex = layout.indexOf('_');
        this.depth = 0;
    }

    public Node(String layout, String goalLayout, int depth) {
        this.layout = layout;
        this.goalLayout = goalLayout;

        this.blankIndex = layout.indexOf('_');
        this.depth = depth;
    }

    public boolean isFinished() {
        if (layout.equals(goalLayout)) {
            return true;
        } else {
            return false;
        }
    }

    public String getLayout() {
        return layout;
    }

    /**
     * Get all the neighbors
     */
    public ArrayList<Node> getNeighbors() {
        ArrayList<Node> neighbors = new ArrayList<Node>();
        
        // Do the horizontal calculation
        int modVal = blankIndex % 3;
        if (modVal == 0) {
            neighbors.add(createNode(blankIndex, blankIndex + 1));

        } else if (modVal == 1) {
            neighbors.add(createNode(blankIndex - 1, blankIndex));
            neighbors.add(createNode(blankIndex, blankIndex + 1));

        } else if (modVal == 2) {
            neighbors.add(createNode(blankIndex - 1, blankIndex));
        }

        if (blankIndex >= 0 && blankIndex <= 2) {
            // Its the first line
            neighbors.add(createNode(blankIndex, blankIndex + 3)); 
        } else if (blankIndex >= 3 && blankIndex <= 5) {
            // Its the second line
            neighbors.add(createNode(blankIndex - 3, blankIndex));
            neighbors.add(createNode(blankIndex, blankIndex + 3));
        } else {
            // Its the last line
            neighbors.add(createNode(blankIndex - 3, blankIndex));
        }

        return neighbors;
    }

    /**
     * Create a new node with two of the items swapped
     */
    public Node createNode(int pos1, int pos2) {
        
        char layoutArr[] = layout.toCharArray();
        char temp = layoutArr[pos1];
        layoutArr[pos1] = layoutArr[pos2];
        layoutArr[pos2] = temp;
        
        String newLayout = new String(layoutArr);
        Node newNode = new Node(newLayout, goalLayout, depth + 1);
        return newNode;
    }

    public String toString() {
        return layout + " [Depth: " + depth + "]";
    }

    public boolean equals(Object other) {
        Node otherNode = (Node) other;

        if (otherNode.getLayout().equals(this.getLayout())) {
            return true;
        } else {
            return false;
        }
    }
}
