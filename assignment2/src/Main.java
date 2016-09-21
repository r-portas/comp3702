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

        if (args.length != 2) {
            System.out.println("Invalid arguments, expected <in-file> <out-file>");
            return;
        }


        try {
            ps.loadProblem(args[0]);

            long start = System.currentTimeMillis();

            solver.loadProblemSpec(ps);
            solver.setFilename(args[1]);

            solver.sampleCustom(2000);

            long end = System.currentTimeMillis();
            long diff = end - start;

            System.out.println("Elapsed time: " + (diff / 1000));

        } catch (Exception e) {
            System.out.println("Something bad happened :(");
            System.out.println(e);
        }
    }
}
