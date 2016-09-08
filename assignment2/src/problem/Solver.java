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

    public void sampleCustom(int samples) {
        sampler.sampleCustomMethod(samples, ps.getObstacles(), ps.getJointCount(), false);

        ArrayList<ArmConfig> configList = sampler.getConfigList();
        kdTree = new KDTree();

        System.out.println("Adding configs to KDTree");
        
        for (ArmConfig ac : configList) {
            kdTree.insert(ac);
        }

        System.out.println("Tree building complete");

        ArmConfig initial = ps.getInitialState();
        System.out.println(kdTree.nearest(initial));
        System.out.println("KDTree size: " + kdTree.size());
    }

    public void sampleNearObstacles(int samples) {
        sampler.sampleNearObstacles(samples);
    }

    public ArrayList<Point2D> getSampleList() {
        return sampler.getSampleList();
    }

}
