package problem;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.awt.geom.*;
import util.Util;
import tester.Tester;

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
    private ArrayList<ArmConfig> armList;
    private Tester tester;

    // The range near a sample
    private double nearbyDistance = 0.01;

    public ArrayList<Point2D> getSampleList() {
        return sampleList;
    }

    public ArrayList<ArmConfig> getConfigList() {
        return armList;
    }
    
    private Point2D createPoint(double x, double y) {

        Point2D point = new Point2D.Double(x, y);
        return point;
    }

    /**
     * Samples a random point in the workspace
     */
    private Point2D sampleRandomPoint() {
        double x = Math.round(rand.nextDouble() * width * 1000)/1000;
        double y = Math.round(rand.nextDouble() * height * 1000)/1000;

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

    public ArmConfig getRandomArmConfig(int joints, boolean gripper) {
        //double x = (double) rand.nextInt(1000) / 1000;
        //double y = (double) rand.nextInt(1000) / 1000;
        double x = rand.nextDouble();
        double y = rand.nextDouble();
        Point2D base = new Point2D.Double(x, y);
       
        List<Double> armJoints = new ArrayList<Double>();

        for (int i = 0; i < joints; i++) {
            double angle = rand.nextDouble() * (Tester.MAX_JOINT_ANGLE - Tester.MIN_JOINT_ANGLE) + Tester.MIN_JOINT_ANGLE;
            armJoints.add(angle);
        }

        ArmConfig arm = new ArmConfig(base, armJoints);
        arm.setDistanceToGoal(ps.getGoalState());
        return arm;
    }

    public Sampler(double width, double height, ProblemSpec ps) {
        this.width = width;
        this.height = height;

        this.ps = ps;

        this.rand = new Random();
        sampleList = new ArrayList<Point2D>();

        tester = new Tester();
    }

    public Sampler(ProblemSpec ps) {
        this.width = 1;
        this.height = 1;

        this.ps = ps;

        this.rand = new Random();
        sampleList = new ArrayList<Point2D>();

        tester = new Tester();
    }
    
    public void sampleCustomMethod(int samples, List<Obstacle> obstacles, int joints, boolean gripper) {
        int complete = 0;
        armList = new ArrayList<ArmConfig>();
        sampleList = new ArrayList<Point2D>();

        while (complete < samples) {
            ArmConfig temp = getRandomArmConfig(joints, gripper);

            if (tester.hasCollision(temp, obstacles) == false && tester.fitsBounds(temp)) {

                // For visual debugging
                sampleList.add(temp.getBaseCenter());

                armList.add(temp);
                complete += 1;
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
