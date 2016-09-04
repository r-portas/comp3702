package problem;

import java.util.ArrayList;

/**
 * Represents a single graph node
 *
 * @author Roy Portas
 */
public class GraphNode {

    private double x;
    private double y;

    private double NEARBY = 0.001;

    public GraphNode(double x, double y) {
        initNode(x, y);
    }

    public GraphNode(double[] point) {
        initNode(point[0], point[1]);
    }

    public void initNode(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    /**
     * Checks if a graph node is nearby
     *
     */
    public boolean isNearby(GraphNode o) {
        double h = y - o.getY();
        double w = x - o.getX();

        double dist = Math.sqrt(h * h + w * w);
    
        if (dist <= NEARBY) {
            return true;
        } else {
            return false;
        }
    }

    public boolean equals(Object o) {
        if (getClass() != o.getClass()) {
            return false;
        }

        GraphNode g = (GraphNode) o;

        if (g.getX() == x && g.getY() == y) {
            return true;
        } else {
            return false;
        }
    }
}
