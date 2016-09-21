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
    private Search search;
    private String filename;
    ArrayList<ArmConfig> configs;
    
    public Solver() {
        filename = "test_sol.txt";
    }

    public void setFilename(String fname) {
        filename = fname;
    }

    /**
     * Loads a problem spec file
     */
    public void loadProblemSpec(ProblemSpec ps) {
        this.ps = ps;

        // Load up the sampler
        sampler = new Sampler(this.ps);
        configs = new ArrayList<ArmConfig>();
        search = new Search(ps);
    }

    public void saveSolution(ArmConfig end, String filename) {
        List<ArmConfig> path = new ArrayList<ArmConfig>();
        // TODO: Fix this
        ArmConfig node = end;

        while (node.parent != null) {
            ArmConfig valid = search.getValidPath(node.parent, node);
            while (valid != null) {
                path.add(valid);

                if (valid.equals(node.parent)) {
                    break;
                }
                if (valid != null) {
                    if (valid.parent == null) {
                        System.out.println(valid);
                    }
                }
                valid = valid.parent;

            }

            node = node.parent;
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

    public void sampleCustom(int samples) {
        boolean gripper = ps.hasGripper();
        //sampler.sampleCustomMethod(samples, ps.getObstacles(), ps.getJointCount(), gripper);
        sampler.sampleNearObstacles(samples, ps.getObstacles(), ps.getJointCount(), gripper);

        //sampler.sampleFixedAngleMethod(samples / 10, 0, ps.getObstacles(), ps.getJointCount(), gripper);
        //sampler.sampleFixedAngleMethod(samples / 10, 90, ps.getObstacles(), ps.getJointCount(), gripper);
        //sampler.sampleFixedAngleMethod(samples / 10, -90, ps.getObstacles(), ps.getJointCount(), gripper);

        ArrayList<ArmConfig> configList = sampler.getConfigList();
        configs = configList;

        ArmConfig goal = ps.getGoalState();
        goal.setDistanceToGoal(goal);

        Search search = new Search(ps);

        ArmConfig endPoint = search.runUCSearch(configList, ps.getInitialState(), goal);

        saveSolution(endPoint, filename);
    }

    public void sampleNearObstacles(int samples) {
        sampler.sampleCustomMethod(samples / 2, ps.getObstacles(), ps.getJointCount(), false);
        sampler.sampleNearObstacles(samples / 2, ps.getObstacles(), ps.getJointCount(), false);

        ArrayList<ArmConfig> configList = sampler.getConfigList();
        configs = configList;

        ArmConfig goal = ps.getGoalState();
        goal.setDistanceToGoal(goal);


        ArmConfig endPoint = search.runUCSearch(configList, ps.getInitialState(), goal);

        saveSolution(endPoint, filename);

    }

    public ArrayList<Point2D> getSampleList() {
        return sampler.getSampleList();
    }

    public ArrayList<ArmConfig> getConfigList() {
        return configs;
    }

}
