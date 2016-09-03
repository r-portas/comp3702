import problem.*;
import java.lang.Exception;

/**
 * Main entrypoint of program
 * @author Roy Portas
 */
public class Main {

    static Sampler sampler;

    public static void main(String[] args) {
        ProblemSpec ps = new ProblemSpec();

        try {
            ps.loadProblem("inputEx/4_joints.txt");

            sampler = new Sampler(ps);
            sampler.sampleNearObstacles(50);

            ps.assumeDirectSolution();
            ps.saveSolution("inputEx/1_sol.txt");

        } catch (Exception e) {
            System.out.println("Something bad happened :(");
        }
    }
}
