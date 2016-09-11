/**
 * The search algorithm
 * @author Roy Portas
 */

package problem;

import java.util.*;

public class Search {

    private ProblemSpec ps;

    public Search(ProblemSpec ps) {
        this.ps = ps;
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

    public String runUCSearch(KDTree tree, ArmConfig start, ArmConfig end) {
        Comparator<ArmConfig> comp = new ArmComparator();
        PriorityQueue<ArmConfig> queue = new PriorityQueue<ArmConfig>(10, comp);
       
        // First create the start node
        ArmConfig temp = ps.getInitialState();
        temp.visited = true;
        temp.expanded = true;
        queue.add(temp);

        while (true) {
            System.out.println("Queue size: " + queue.size());
            if (queue.size() == 0) {
                return "No matches found";
            }
            temp = queue.poll();
            temp.expanded = true;
            System.out.println(temp);

            //System.out.println(temp.pos);

            if (temp == end) {
                // Returns the path
                System.out.println("Found path!!!");
                return "Found path";
            }

            List<ArmConfig> connected = tree.nearestList(temp);

            // Add the new nodes
            for (ArmConfig dest : connected) {
                
                if (dest.expanded == false) {
                    dest.parent = temp;
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
