import problem.*;
import java.lang.Exception;

/**
 * Main entrypoint of program
 * @author Roy Portas
 */
public class Main {

    static Solver solver;

    public static void main(String[] args) {
        ProblemSpec ps = new ProblemSpec();
        solver = new Solver();

        try {
            ps.loadProblem("inputEx/joint_3-obs_15.txt");

            long start = System.currentTimeMillis();

            solver.loadProblemSpec(ps);

            solver.sampleCustom(600);

            long end = System.currentTimeMillis();
            long diff = end - start;

            System.out.println("Elapsed time: " + (diff / 1000));

        } catch (Exception e) {
            System.out.println("Something bad happened :(");
            System.out.println(e);
        }
    }
}
