/**
 * The search algorithm
 * @author Roy Portas
 */

package problem;

import java.util.*;
import tester.Tester;

public class Search {

    private ProblemSpec ps;
    private Tester tester;

    public Search(ProblemSpec ps) {
        this.ps = ps;
        this.tester = new Tester();
    }

    private class ArmComparator implements Comparator<ArmConfig> {
        @Override
        public int compare(ArmConfig x, ArmConfig y) {
            // Normally it is x - y, however we want to invert, to sort by lowest first
            if (x.getDistanceToGoal() > y.getDistanceToGoal()) {
                return 1;
            } else if (x.getDistanceToGoal() < y.getDistanceToGoal()) {
                return -1;
            } else {
                return 0;
            }
        }
    }

    public ArmConfig checkValidPath(ArmConfig start, ArmConfig end) {
        
        ArmConfig temp = start;
        ArmConfig last = start;

        while (!temp.getBaseCenter().equals(end.getBaseCenter())) {
            last = temp;
            temp = new ArmConfig(last);
            temp.moveTowards(end);

            if (!tester.isValidStep(temp, last)) {
                return null;
            }

            if (tester.hasCollision(temp, ps.getObstacles())) {
                return null;
            }

            temp.parent = last;
        }

        if (!tester.isValidStep(temp, end)) {
            return null;
        }

        if (tester.hasCollision(temp, ps.getObstacles())) {
            return null;
        }

        return temp;

    }

    public ArmConfig runUCSearch(ArrayList<ArmConfig> configs, ArmConfig start, ArmConfig end) {
        Comparator<ArmConfig> comp = new ArmComparator();
        PriorityQueue<ArmConfig> queue = new PriorityQueue<ArmConfig>(10, comp);
       
        configs.add(end);
        configs.add(start);

        ArrayList<ArmConfig> possibleSols = new ArrayList<ArmConfig>();
        for (ArmConfig config : configs) {
            possibleSols.add(config);
        }
       
        // First create the start node
        ArmConfig temp = ps.getInitialState();
        temp.visited = true;
        temp.expanded = true;
        queue.add(temp);

        while (true) {
            System.out.println("Queue size: " + queue.size());
            System.out.println("Nodes remaining: " + possibleSols.size());
            if (queue.size() == 0) {
                return null;
            }

            temp = queue.poll();

            if (!possibleSols.contains(temp)) {
                continue;
            }

            temp.visited = true;

            if (temp.getBaseCenter().equals(end.getBaseCenter())) {
                // Returns the path
                System.out.println("Found path!");
                return temp;
            }

            // Remove the current point from the possible solutions
            possibleSols.remove(temp);

            // Add the new nodes
            for (ArmConfig dest : possibleSols) {
                if (dest == null) {
                    continue;
                }


                ArmConfig valid = checkValidPath(temp, dest);
                if (valid != null) {
                    dest.parent = valid;
                    queue.add(dest);
                }
                
            }

            // We don't add the old one back, since its not exactly required
            
        }

    }

    public void BFS(KDTree tree, ArmConfig start, ArmConfig end) {
        LinkedList<ArmConfig> queue = new LinkedList<ArmConfig>();
        ArmConfig current = null;
        ArmConfig nearby = null;

        queue.add(start);

        while (!queue.isEmpty()) {
             current = queue.removeFirst();

             if (current == end) {
                // We found the goal, so quit
                 
                System.out.println("!!! Found solution");
                
                while ((current = current.parent) != null) {
                    System.out.println(current);
                }
             }

             while ((nearby = tree.nearest(current)) != null) {
                nearby.visited = true;
                nearby.parent = current;
                queue.add(nearby);
             }
            
        }
    }
}
