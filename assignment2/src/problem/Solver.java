package problem;

import java.lang.Exception;

public class Solver {

    private ProblemSpec ps;
    private String solFilename;

    public Solver(String problemFilename, String solutionFilename) {
        ps = new ProblemSpec();

        solFilename = solutionFilename;

        try {
            // Load in the problem file
            ps.loadProblem(problemFilename);

        } catch (Exception e) {
            System.out.println(e.toString());
        }
    }

}
