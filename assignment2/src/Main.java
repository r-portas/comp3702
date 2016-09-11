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
            ps.loadProblem("inputEx/4_joints.txt");

            long start = System.currentTimeMillis();

            System.out.println(

            solver.loadProblemSpec(ps);

            solver.sampleCustom(1400000);

            long end = System.currentTimeMillis();
            long diff = end - start;

            System.out.println("Elapsed time: " + (diff / 1000));

            //ps.assumeDirectSolution();
            //ps.saveSolution("inputEx/1_sol.txt");

        } catch (Exception e) {
            System.out.println("Something bad happened :(");
            System.out.println(e);
        }
    }
}
