package problem;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.awt.geom.Point2D;
import util.Util;

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

    private ArrayList<Point2D> sampleList;

    // The range near a sample
    private double nearbyDistance = 0.01;

    public ArrayList<Point2D> getSampleList() {
        return sampleList;
    }
    
    private Point2D createPoint(double x, double y) {

        Point2D point = new Point2D.Double(x, y);
        return point;
    }

    /**
     * Samples a random point in the workspace
     */
    private Point2D sampleRandomPoint() {
        double x = rand.nextDouble() * width;
        double y = rand.nextDouble() * height;

        return createPoint(x, y);
    }

    /**
     * Samples a nearby point from a given point
     *
     * The point will be between -nearbyDistance to nearbyDistance of the given point
     */
    private Point2D sampleNearbyPoint(Point2D point) {
        double x = (rand.nextDouble() * 2 * nearbyDistance) - nearbyDistance;
        double y = (rand.nextDouble() * 2 * nearbyDistance) - nearbyDistance;

        // Get the point near the desired point
        x += point.getX();
        y += point.getY();

        return createPoint(x, y);
    }

    public Sampler(double width, double height, ProblemSpec ps) {
        this.width = width;
        this.height = height;

        this.ps = ps;

        this.rand = new Random();
        sampleList = new ArrayList<Point2D>();
    }

    public Sampler(ProblemSpec ps) {
        this.width = 1;
        this.height = 1;

        this.ps = ps;

        this.rand = new Random();
        sampleList = new ArrayList<Point2D>();
    }

    public void sampleCustomMethod(int samples) {
        int complete = 0;
        sampleList = new ArrayList<Point2D>();

        while (complete < samples) {
            Point2D q1 = sampleRandomPoint();

            if (ps.checkCollision(q1) == false) {
                // q1 is colliding
                sampleList.add(q1);
                complete += 1;
                System.out.println("Position (" + q1.getX() + ", " + q1.getY() + ")");
            }
                
        }

    }

    public void sampleNearObstacles(int samples) {
        int complete = 0;
        sampleList = new ArrayList<Point2D>();

        while (complete < samples) {
            Point2D q1 = sampleRandomPoint();
            Point2D q2 = sampleNearbyPoint(q1);

            // Check if q1 is colliding
            if (ps.checkCollision(q1) == false || ps.checkCollision(q2) == false) {
                if (ps.checkCollision(q1)) {
                    // q1 is colliding
                    sampleList.add(q2);
                    complete += 1;
                    System.out.println("Position (" + q2.getX() + ", " + q2.getY() + ")");

                } else if (ps.checkCollision(q2)) {
                    // q2 is colliding
                    sampleList.add(q1);
                    complete += 1;

                    System.out.println("Position (" + q1.getX() + ", " + q1.getY() + ")");
                }

            }

        }
    }

}
