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
    private double nearbyDistance = 0.05;

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

    private List<Double> getRandomGripper() {
        double max = Tester.MAX_GRIPPER_LENGTH;
        double min = Tester.MIN_GRIPPER_LENGTH;

        List<Double> joints = new ArrayList<Double>();
        for (int i = 0; i < 4; i++) {
            //joints.add((max - min) * rand.nextDouble() + min);
            joints.add(min);
        }
        return joints;
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

    public ArmConfig getNearbyArmConfig(ArmConfig nearby, int joints, boolean gripper) {
        ArmConfig result = getRandomArmConfig(joints, gripper);

        Point2D dest = sampleNearbyPoint(nearby.getBaseCenter());
        result.setBaseCenter(dest);
        return result;
    }

    /**
     * Gets a nearby config to a obstacle with a given angle of joints
     */
    public ArmConfig getNearbyArmConfigWithAngle(ArmConfig nearby, int angle, int joints, boolean gripper) {
        ArmConfig result = getStandardArmConfig(angle, joints, gripper);

        Point2D dest = sampleNearbyPoint(nearby.getBaseCenter());
        result.setBaseCenter(dest);
        return result;
    }

    public ArmConfig getRandomArmConfig(int joints, boolean gripper) {
        double x = rand.nextDouble();
        double y = rand.nextDouble();
        Point2D base = new Point2D.Double(x, y);
       
        List<Double> armJoints = new ArrayList<Double>();

        for (int i = 0; i < joints; i++) {
            double angle = rand.nextDouble() * (Tester.MAX_JOINT_ANGLE - Tester.MIN_JOINT_ANGLE) + Tester.MIN_JOINT_ANGLE;
            armJoints.add(angle);
        }
    
        ArmConfig arm;
        if (gripper) {
            arm = new ArmConfig(base, armJoints, getRandomGripper());
        } else {
            arm = new ArmConfig(base, armJoints);
        }
        arm.setDistanceToGoal(ps.getGoalState());


        return arm;
    }

    public ArmConfig getStandardArmConfig(int angle, int joints, boolean gripper) {
        double x = rand.nextDouble();
        double y = rand.nextDouble();
        Point2D base = new Point2D.Double(x, y);

        List<Double> armJoints = new ArrayList<Double>();
        if (joints > 0) {
            armJoints.add(angle * Math.PI / 180.0);

            for (int i = 1; i < joints; i++) {
                armJoints.add((double) 0);
            }
        }

        ArmConfig arm;
        if (gripper) {
            arm = new ArmConfig(base, armJoints, getRandomGripper());
        } else {
            arm = new ArmConfig(base, armJoints);
        }
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
        
        armList = new ArrayList<ArmConfig>();
        sampleList = new ArrayList<Point2D>();

        this.rand = new Random();
        sampleList = new ArrayList<Point2D>();

        tester = new Tester();
    }

    public void sampleFixedAngleMethod(int samples, int angle, List<Obstacle> obstacles, int joints, boolean gripper) {
        int complete = 0;

        while (complete < samples) {
            ArmConfig temp = getStandardArmConfig(angle, joints, gripper);

            if (tester.hasCollision(temp, obstacles) == false && tester.fitsBounds(temp)) {

                // For visual debugging
                sampleList.add(temp.getBaseCenter());

                armList.add(temp);
                complete += 1;
            }
                
        }

    }

    
    public void sampleCustomMethod(int samples, List<Obstacle> obstacles, int joints, boolean gripper) {
        int complete = 0;

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

    public void sampledFixedNearObstacles(int samples, int angle, List<Obstacle> obstacles, int joints, boolean gripper) {
        int complete = 0;

        if (obstacles.size() == 0) {
            return;
        }

        while (complete < samples) {
            ArmConfig q1 = getStandardArmConfig(angle, joints, gripper);
            ArmConfig q2 = getNearbyArmConfigWithAngle(q1, angle, joints, gripper);

            // Check if q1 is colliding
            if (tester.hasCollision(q1, obstacles) == false || tester.hasCollision(q2, obstacles) == false) {
                if (tester.hasCollision(q1, obstacles)) {
                    // q1 is colliding
                    armList.add(q2);
                    complete += 1;

                } else if (tester.hasCollision(q2, obstacles)) {
                    // q2 is colliding
                    armList.add(q1);
                    complete += 1;

                }

            }

        }
    }

    public void sampleNearObstacles(int samples, List<Obstacle> obstacles, int joints, boolean gripper) {
        int complete = 0;

        if (obstacles.size() == 0) {
            return;
        }

        while (complete < samples) {
            ArmConfig q1 = getRandomArmConfig(joints, gripper);
            ArmConfig q2 = getNearbyArmConfig(q1, joints, gripper);

            // Check if q1 is colliding
            if (tester.hasCollision(q1, obstacles) == false || tester.hasCollision(q2, obstacles) == false) {
                if (tester.hasCollision(q1, obstacles)) {
                    // q1 is colliding
                    armList.add(q2);
                    complete += 1;

                } else if (tester.hasCollision(q2, obstacles)) {
                    // q2 is colliding
                    armList.add(q1);
                    complete += 1;

                }

            }

        }
    }

}
