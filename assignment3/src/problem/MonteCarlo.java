package problem;

import java.util.ArrayList;
import java.util.List;
import java.util.Collections;
import java.util.Stack;

/**
 * Models a Monte Carlo search tree
 */
public class MonteCarlo {
   
    /** Enable to display debugging information **/
    private boolean DEBUGGING = true;

    /** Used to time the execution of the tree search **/
    private long startTime;

    /** The max number of items that can be in a single level of the tree **/
    private int maxItems = 30000;

    /** The starting node of the tree **/
    private Stock start;

    /** The probability matrix **/
    private List<Matrix> probabilities;

    /** Stores all elements in the monte carlo tree **/
    private List<Stock> tree;

    /** Current level **/
    private List<Stock> currentLevel;

    /** The store instance **/
    private Store store;

    /** The current depth of the tree */
    private int currentDepth;

    /** The probability generator instance **/
    private ProbabilityGenerator pg;

    /** The max depth of the tree **/
    private int maxDepth;

    /** Stores a stack containing the stocks **/
    private Stack<Stock> stackItems;

    public MonteCarlo(Stock start, Store store, ProbabilityGenerator pg, List<Matrix> probabilities, int maxDepth) {

        this.store = store;
        this.pg = pg;
        this.probabilities = probabilities;
        this.currentDepth = 1;
        stackItems = new Stack<Stock>();

        start.setParent(null);
        start.setDepth(currentDepth);
        currentDepth++;
        this.start = start;

        tree = new ArrayList<Stock>();
        tree.add(start);

        currentLevel = new ArrayList<Stock>();
        currentLevel.add(start);

        this.maxDepth = maxDepth;

        if (maxDepth != 0) {
            this.maxItems = 100000 / maxDepth;
        } else {
            this.maxItems = 100000;
        }

        log("max depth is " + maxDepth);
    }

    /**
     * Logs to the terminal
     */
    public void log(String message) {
        if (DEBUGGING) {
            System.out.println("[MonteCarlo]: " + message);
        }
    }

    /**
     * Runs the tree generation
     */
    public Stock run() {

        startTime = System.currentTimeMillis();

        while (currentDepth < maxDepth) {
            currentLevel = addTreeLevel(); 

            currentDepth++;

            // Sort the collection
            Collections.sort(currentLevel);
            Collections.reverse(currentLevel);

            // Delete the unlikely values to keep the tree size in check
            while (currentLevel.size() > maxItems) {
                currentLevel.remove(currentLevel.size() - 1);
            }

        }
        

        double seconds = (System.currentTimeMillis() - startTime / 1000.0);
        log("Search finished in " + seconds);

        stackItems = new Stack<Stock>();

        Stock ptr = currentLevel.get(0);

        if (ptr == start) {
            return ptr;
        }
        
        while (ptr.getParent() != start) {
            // Add the item to the stack
            stackItems.push(ptr);
            ptr = ptr.getParent();
        }
        
        return ptr;

    }

    /**
     * Gets the next stock from the stack
     */
    public Stock getStockItem() {
        return stackItems.pop();
    }

    /**
     * Adds a level to the tree
     */
    private List<Stock> addTreeLevel() {

        log("starting generation of tree level " + currentDepth);
        
        // The new level of the tree
        List<Stock> newLevel = new ArrayList<Stock>();

        for (Stock s : currentLevel) {
            // Find the child nodes and add the new nodes to the newLevel list
            newLevel.addAll(addChildNodes(s));
        }

        log("finished generation of tree level " + currentDepth + ", level has " + newLevel.size() + " nodes");

        return newLevel;
    }

    /**
     * Adds a child nodes to the tree
     * @returns A list of new nodes
     */
    private List<Stock> addChildNodes(Stock parent) {

        //TODO: Does the probability change with the weeks?
        // Get the possible solutions
        List<Stock> solutions = pg.getPossibleSolutions(parent, store, probabilities);

        //log(solutions.size() + " child nodes generated");

        for (Stock s : solutions) {
            // Set the parent and add to the tree
            s.setParent(parent);
            parent.addChild(s);
            tree.add(s); 
        }

        return solutions;
         
    }

}
