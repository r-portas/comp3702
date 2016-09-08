package problem;

import java.util.*;
import java.awt.geom.*;

/**
 * Stores the tree
 */
public class KDTree {

    private ArmConfig point;
    private KDTree left;
    private KDTree right;
    private boolean compareX;
    private int num;

    public KDTree() {
        num = 0;
    }

    public int size() {
        return num;
    }

    public boolean insert(ArmConfig ac) {
        
        if (point == null) {
            point = ac;
            compareX = true;
            num = 1;
        }

        if (point.equals(ac)) {
            // Don't compare it, just return
            return false;
        }

        num++;
        boolean inserted = true;

        if ((compareX && (point.getBaseCenter().getX() <= point.getBaseCenter().getX()) ||
                    (!compareX && (ac.getBaseCenter().getY() <= point.getBaseCenter().getY())))) {
            if (left == null) {
                left = new KDTree();
                left.point = ac;
                left.compareX = !compareX;
                left.num = 1;
            } else {
                inserted = left.insert(ac);
                if (!inserted) {
                    num--;
                }
            }
        } else {
            if (right == null) {
                right  = new KDTree();
                right.point = ac;
                right.compareX = !compareX;
                right.num = 1;
            } else {
                inserted = right.insert(ac);
                if (!inserted) {
                    num--;
                }
            }
        }

        return inserted;
    }

    public boolean contains(ArmConfig ac) {
        if (point == null) {
            return false;
        }

        if (point.equals(ac)) {
            return true;
        }

        if (left != null && (compareX && (ac.getBaseCenter().getX() <= point.getBaseCenter().getX()))
                || (!compareX && (ac.getBaseCenter().getY() <= point.getBaseCenter().getY()))) {
            return left.contains(ac);

        } else {
            if (right == null) {
                return false;
            } else {
                return right.contains(ac);
            }
        }
    }

    public Iterable<ArmConfig> range(Rectangle2D rect) {
        LinkedList<ArmConfig> subPoints = new LinkedList<ArmConfig>();
        
        if (point == null) {
            // If the point is not set, return
            return subPoints;
        }

        if (rect.contains(point.getBaseCenter())) {
            subPoints.add(point);
        }

        // Right is greater than the current value
        if (right != null && ((compareX && (point.getBaseCenter().getX() <= rect.getMaxX())) || 
                        (!compareX && (point.getBaseCenter().getY() <= rect.getMaxY())))) {
            for (ArmConfig ac : right.range(rect)) {
                subPoints.add(ac);
            }
        }

        // Left is less than
        if (left != null && ((compareX && (point.getBaseCenter().getX() >= rect.getMinX())) || 
                        (!compareX && (point.getBaseCenter().getY() >= rect.getMinY())))) {
            for (ArmConfig ac : left.range(rect)) {
                subPoints.add(ac);
            }
        }

        return subPoints;
    }

    public ArmConfig nearest(ArmConfig ac) {
        return nearest(ac, Double.MAX_VALUE);
    }

    public ArmConfig nearest(ArmConfig ac, double minDist) {
        if (point == null) {
            return null;
        }

        if (point.equals(ac)) {
            return point;
        }

        ArmConfig bestConfig = null;
        double bestDist = minDist;
        double rootDist = point.getDistanceTo(ac);

        if (rootDist < minDist) {
            bestConfig = point;
            bestDist = rootDist;
        }

        KDTree first = right;
        KDTree second = left;

        if (first != null) {
            ArmConfig firstBestConfig = first.nearest(ac, bestDist);

            if (firstBestConfig != null) {
                bestConfig = firstBestConfig;
            }
        }

        if (second == null) {
            return bestConfig;
        }

        if (second == left) {
            if (compareX && (ac.getBaseCenter().getX() - point.getBaseCenter().getX() >= bestDist)) {
                return bestConfig;
            }
            if (!compareX && (ac.getBaseCenter().getY() - point.getBaseCenter().getY() >= bestDist)) {
                return bestConfig;
            }
        } else if (second == right) {
            if (compareX && (point.getBaseCenter().getX() - ac.getBaseCenter().getX() >= bestDist)) {
                return bestConfig;
            }
            if (!compareX && (point.getBaseCenter().getY() - ac.getBaseCenter().getY() >= bestDist)) {
                return bestConfig;
            }
            
        }

        ArmConfig secondBestConfig = second.nearest(ac, bestDist);

        if (secondBestConfig != null) {
            bestConfig = secondBestConfig;
        }

        return bestConfig;

    }
}
