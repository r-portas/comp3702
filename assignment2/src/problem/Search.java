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

    /**
     * Gets the path between two points
     */
    public ArmConfig getValidPath(ArmConfig start, ArmConfig end) {
        
        ArmConfig temp = start;
        ArmConfig last = start;

        while (!temp.getBaseCenter().equals(end.getBaseCenter())) {
            last = temp;
            temp = new ArmConfig(last);
            temp.moveTowards(end);

            if (!tester.isValidStep(temp, last)) {
                return last;
            }

            if (tester.hasCollision(temp, ps.getObstacles())) {
                return last;
            }

            if (!tester.fitsBounds(temp)) {
                return last;
            }

            if (tester.hasSelfCollision(temp)) {
                return last;
            }

            temp.parent = last;
        }

        if (!tester.isValidStep(temp, end)) {
            return temp;
        }

        if (tester.hasCollision(temp, ps.getObstacles())) {
            return temp;
        }

        return temp;

    }

    public ArmConfig checkValidPath(ArmConfig start, ArmConfig end) {
        
        ArmConfig temp = start;
        ArmConfig last = start;

        while (!temp.getBaseCenter().equals(end.getBaseCenter())) {
            last = temp;
            temp = new ArmConfig(last);
            temp.moveTowards(end);

            if (!tester.isValidStep(temp, last)) {
                return last;
            }

            if (tester.hasCollision(temp, ps.getObstacles())) {
                return last;
            }

            if (!tester.fitsBounds(temp)) {
                return last;
            }

            if (tester.hasSelfCollision(temp)) {
                return last;
            }

            temp.parent = last;
        }

        if (!tester.isValidStep(temp, end)) {
            return temp;
        }

        if (tester.hasCollision(temp, ps.getObstacles())) {
            return temp;
        }

        return temp;

    }

    public ArmConfig moveJointsToDest(ArmConfig start, ArmConfig dest) {
        ArmConfig temp = new ArmConfig(start);
        ArmConfig last = start;
        temp.parent = last;

        if (dest.getJointAngles().size() == 0) {
            return temp;
        }

        while (!temp.getJointAngles().equals(dest.getJointAngles())) {
            // Move the config to the destination
            last = temp;
            temp = new ArmConfig(last);
            temp.parent = last;
            temp.moveTowards(dest);
            
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
            config.setDistanceToGoal(ps.getGoalState());
            possibleSols.add(config);
        }

        // Sort possible solutions by distance from goal
        Collections.sort(possibleSols, comp);
       
        // First create the start node
        ArmConfig temp = ps.getInitialState();
        temp.visited = true;
        temp.expanded = true;
        queue.add(temp);

        ArmConfig bestAttempt = ps.getInitialState();

        while (true) {
            System.out.println("Queue size: " + queue.size());
            System.out.println("Nodes remaining: " + possibleSols.size());
            if (queue.size() == 0) {
                System.out.println("Failed, using best attempt");
                System.out.println("Best attempt is " + bestAttempt.getDistanceToGoal() + " from the goal");
                return bestAttempt;
            }

            temp = queue.poll();
            System.out.println("Distance from goal: " + temp.getDistanceToGoal());
            if (bestAttempt.getDistanceToGoal() > temp.getDistanceToGoal()) {
                bestAttempt = temp;
            }

            if (!possibleSols.contains(temp)) {
                continue;
            }

            temp.visited = true;

            if (temp.getBaseCenter().equals(end.getBaseCenter())) {
                // Returns the path
                System.out.println("Found path!");

                // Rotate the joints and grippers to be correct positions
                return moveJointsToDest(temp, end);
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
                    valid.setDistanceToGoal(ps.getGoalState());
                    queue.add(valid);
                }
                
            }

        }

    }

}
