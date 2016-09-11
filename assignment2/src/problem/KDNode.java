/**
 * Represents a node in the KD Tree
 */
package problem;

import java.util.TreeSet;
import java.util.LinkedList;
import java.awt.geom.*;

public class KDNode {
    private TreeSet<ArmConfig> points;

    public KDNode() {
        points = new TreeSet<ArmConfig>();
    }

    public int size() {
        return points.size();
    }

    public void insert(ArmConfig config) {
        points.add(config);
    }

    public boolean contains(ArmConfig config) {
        return points.contains(config);
    }

    public Iterable<ArmConfig> range(Rectangle2D rect) {
        LinkedList<ArmConfig> subPoints = new LinkedList<ArmConfig>();
        for (ArmConfig ac : points) {
            if (rect.contains(ac.getBaseCenter())) {
                subPoints.add(ac);
            }
        }

        return subPoints;
    }

    public ArmConfig nearest(ArmConfig ac) {
        double minDist = Double.MAX_VALUE;
        ArmConfig best = null;

        for (ArmConfig temp : points) {
            double dist = temp.getDistanceTo(ac);
            
            if (dist < minDist && temp.visited == false) {
                minDist = dist;
                best = temp;
            }
        }

        return best;
    }
}
