import problem.*;
import java.lang.Exception;

/**
 * Main entrypoint of program
 * @author Roy Portas
 */
public class Main {
    public static void main(String[] args) {
        ProblemSpec ps = new ProblemSpec();

        try {
            ps.loadProblem("inputEx/1_joint.txt");
            ps.assumeDirectSolution();
            ps.saveSolution("inputEx/1_sol.txt");

        } catch (Exception e) {
            System.out.println("Something bad happened :(");
        }
    }
}
