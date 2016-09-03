package problem;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Samples a given configuration
 *
 * @author Roy Portas
 */
public class Sampler {

    private ProblemSpec ps;

    private double width;
    private double height;
    private Random rand;

    private ArrayList<double[]> sampleList;

    // The range near a sample
    private double nearbyDistance = 0.01;

    public ArrayList<double[]> getSampleList() {
        return sampleList;
    }
    
    private double[] createPoint(double x, double y) {
        double[] point = new double[2];
        point[0] = x;
        point[1] = y;

        return point;
    }

    /**
     * Samples a random point in the workspace
     */
    private double[] sampleRandomPoint() {
        double x = rand.nextDouble() * width;
        double y = rand.nextDouble() * height;

        return createPoint(x, y);
    }

    /**
     * Samples a nearby point from a given point
     *
     * The point will be between -nearbyDistance to nearbyDistance of the given point
     */
    private double[] sampleNearbyPoint(double[] point) {
        double x = (rand.nextDouble() * 2 * nearbyDistance) - nearbyDistance;
        double y = (rand.nextDouble() * 2 * nearbyDistance) - nearbyDistance;

        // Get the point near the desired point
        x += point[0];
        y += point[1];

        return createPoint(x, y);
    }

    public static enum Strategy {
        NEAR_OBSTACLES,
        INSIDE_PASSAGE
    }

    public Sampler(double width, double height, ProblemSpec ps) {
        this.width = width;
        this.height = height;

        this.ps = ps;

        this.rand = new Random();
        sampleList = new ArrayList<double[]>();
    }

    public Sampler(ProblemSpec ps) {
        this.width = 1;
        this.height = 1;

        this.ps = ps;

        this.rand = new Random();
        sampleList = new ArrayList<double[]>();
    }

    public void sampleNearObstacles(int samples) {
        int complete = 0;
        sampleList = new ArrayList<double[]>();

        while (complete < samples) {
            double[] q1 = sampleRandomPoint();
            double[] q2 = sampleNearbyPoint(q1);

            // Check if q1 is colliding
            if (ps.checkCollision(q1[0], q1[1]) == false || ps.checkCollision(q2[0], q2[1]) == false) {
                if (ps.checkCollision(q1[0], q1[1])) {
                    // q1 is colliding
                    sampleList.add(q2);
                    complete += 1;
                    System.out.println("Position (" + q2[0] + ", " + q2[1] + ")");

                } else if (ps.checkCollision(q2[0], q2[1])) {
                    // q2 is colliding
                    sampleList.add(q1);
                    complete += 1;

                    System.out.println("Position (" + q1[0] + ", " + q1[1] + ")");
                }
            }

        }
    }

    public void sampleWorkspace(int samples, Strategy strat) {
        switch (strat) {
            case NEAR_OBSTACLES:

                  break;

            case INSIDE_PASSAGE:

                  break;
        }
    }

}
