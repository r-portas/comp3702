package problem;

import java.lang.Exception;
import java.util.*;
import java.awt.geom.Point2D;
import java.lang.Exception;

/**
 * The main class which manages the following:
 * - Sampling (Generating nodes)
 * - Connecting (Generating edges)
 *
 * @author Roy Portas
 */
public class Solver {

    private ProblemSpec ps;
    private Sampler sampler;
    private KDTree kdTree;
    ArrayList<ArmConfig> configs;
    
    public Solver() {
    }

    /**
     * Loads a problem spec file
     */
    public void loadProblemSpec(ProblemSpec ps) {
        this.ps = ps;

        // Load up the sampler
        sampler = new Sampler(this.ps);
        configs = new ArrayList<ArmConfig>();
    }

    public void saveSolution(ArmConfig end, String filename) {
        List<ArmConfig> path = new ArrayList<ArmConfig>();
        while (end != null) {
            path.add(end);
            end = end.parent;
        }
      
        Collections.reverse(path);

        ps.setPath(path);
        try {
            ps.saveSolution(filename);
        } catch (Exception e) {
            System.out.println("An error occured saving the file");
            System.out.println(e);
        }
    }

    public void kdTest() {
        kdTree = new KDTree();
        Search search = new Search(ps);

        ArmConfig start = new ArmConfig("0.1 0.1 0.2");
        ArmConfig end = new ArmConfig("0.2 0.2 0.3");

        ArmConfig res = search.checkValidPath(start, end);

        if (res != null) {
            
            List<ArmConfig> path = new ArrayList<ArmConfig>();
            while (res != null) {
                path.add(res);
                res = res.parent;
            }
           
            ps.setPath(path);
            try {
                ps.saveSolution("inputEx/test_movement.txt");
            } catch (Exception e) {
                System.out.println("LOL");
            }
        } else {
            System.out.println("Could not get valid path!");
        }

    }

    public void sampleCustom(int samples) {
        boolean gripper = ps.hasGripper();
        //sampler.sampleCustomMethod(samples / 10, ps.getObstacles(), ps.getJointCount(), gripper);
        sampler.sampleNearObstacles(samples, ps.getObstacles(), ps.getJointCount(), gripper);
        //sampler.sampledFixedNearObstacles(samples / 2, 0, ps.getObstacles(), ps.getJointCount(), gripper);
        //sampler.sampledFixedNearObstacles(samples / 2, 90, ps.getObstacles(), ps.getJointCount(), gripper);
        //sampler.sampledFixedNearObstacles(samples / 2, 45, ps.getObstacles(), ps.getJointCount(), gripper);
        //sampler.sampledFixedNearObstacles(samples / 2, -90, ps.getObstacles(), ps.getJointCount(), gripper);
        //sampler.sampledFixedNearObstacles(samples / 2, -45, ps.getObstacles(), ps.getJointCount(), gripper);

        //sampler.sampleFixedAngleMethod(samples / 10, 0, ps.getObstacles(), ps.getJointCount(), gripper);
        //sampler.sampleFixedAngleMethod(samples / 10, 90, ps.getObstacles(), ps.getJointCount(), gripper);
        //sampler.sampleFixedAngleMethod(samples / 10, -90, ps.getObstacles(), ps.getJointCount(), gripper);

        ArrayList<ArmConfig> configList = sampler.getConfigList();
        configs = configList;

        ArmConfig goal = ps.getGoalState();
        goal.setDistanceToGoal(goal);

        Search search = new Search(ps);

        ArmConfig endPoint = search.runUCSearch(configList, ps.getInitialState(), goal);

        saveSolution(endPoint, "test_sol.txt");
    }

    public void sampleNearObstacles(int samples) {
        sampler.sampleCustomMethod(samples / 2, ps.getObstacles(), ps.getJointCount(), false);
        sampler.sampleNearObstacles(samples / 2, ps.getObstacles(), ps.getJointCount(), false);

        ArrayList<ArmConfig> configList = sampler.getConfigList();
        configs = configList;

        ArmConfig goal = ps.getGoalState();
        goal.setDistanceToGoal(goal);

        Search search = new Search(ps);

        ArmConfig endPoint = search.runUCSearch(configList, ps.getInitialState(), goal);

        saveSolution(endPoint, "test_sol.txt");

    }

    public ArrayList<Point2D> getSampleList() {
        return sampler.getSampleList();
    }

    public ArrayList<ArmConfig> getConfigList() {
        return configs;
    }

}
