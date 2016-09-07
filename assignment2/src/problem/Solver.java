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
    private EnvironmentMap envMap;

    public Solver() {
        envMap = new EnvironmentMap();
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

        envMap.generateMap(sampler.getConfigList());
    }

    public void sampleNearObstacles(int samples) {
        sampler.sampleNearObstacles(samples);
    }

    public ArrayList<Point2D> getSampleList() {
        return sampler.getSampleList();
    }

}
