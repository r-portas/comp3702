package problem;

import java.lang.Exception;
import java.util.ArrayList;
import java.awt.geom.Point2D;

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

    public Solver() {
    }

    /**
     * Loads a problem spec file
     */
    public void loadProblemSpec(ProblemSpec ps) {
        this.ps = ps;
        
        // Load up the sampler
        sampler = new Sampler(this.ps);
    }

    public void kdTest() {
         kdTree = new KDTree();

         ArmConfig start = new ArmConfig("0.1 0.1 0.2");
         kdTree.insert(start);
         kdTree.insert(new ArmConfig("0.101 0.101 0.3"));
         kdTree.insert(new ArmConfig("0.0999 0.0999 0.3"));
         kdTree.insert(new ArmConfig("0.1 0.0999 0.3"));

         System.out.println(kdTree.nearestList(start));

    }

    public void sampleCustom(int samples) {
        sampler.sampleCustomMethod(samples, ps.getObstacles(), ps.getJointCount(), false);

        ArrayList<ArmConfig> configList = sampler.getConfigList();
        kdTree = new KDTree();

        System.out.println("Adding configs to KDTree");

        // Insert the start and end states
        kdTree.insert(ps.getInitialState());

        ArmConfig goal = ps.getGoalState();
        goal.setDistanceToGoal(goal);
        kdTree.insert(goal);
        
        for (ArmConfig ac : configList) {
            kdTree.insert(ac);
        }

        System.out.println("Tree building complete");
        System.out.println("KDTree size: " + kdTree.size());
        System.out.println("Starting point: " + ps.getInitialState());

        Search search = new Search(ps);
        search.runUCSearch(kdTree, ps.getInitialState(), ps.getGoalState());
    }

    public void sampleNearObstacles(int samples) {
        sampler.sampleNearObstacles(samples);
    }

    public ArrayList<Point2D> getSampleList() {
        return sampler.getSampleList();
    }

}
