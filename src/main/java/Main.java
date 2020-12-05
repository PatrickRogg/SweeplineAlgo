import algorithm.BruteForceIntersectionAlgorithm;
import algorithm.IntersectionAlgorithm;
import algorithm.LineSweepIntersectionAlgorithm;
import model.Line;
import reader.Reader;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException, URISyntaxException {
        final String fileName = "test.dat";

        final List<Line> lines = new Reader().getLines(fileName);
        final List<Line> lines1 = new ArrayList<>(lines);
        final IntersectionAlgorithm intersectionAlgorithm = new BruteForceIntersectionAlgorithm();
        final IntersectionAlgorithm intersectionAlgorithm1 = new LineSweepIntersectionAlgorithm();
        long startTime = System.currentTimeMillis();
        final int bruteForce = intersectionAlgorithm.getIntersectionCountOf(lines);
        System.out.println("Intersections Brute Force: " + bruteForce);
        System.out.println("Time needed: " + (System.currentTimeMillis() - startTime) + "ms");
        System.out.println("------------------------------------");
        startTime = System.currentTimeMillis();
        final int sweepLine = intersectionAlgorithm1.getIntersectionCountOf(lines1);
        System.out.println("Intersections Line Sweep: " + sweepLine);
        System.out.println("Time needed: " + (System.currentTimeMillis() - startTime) + "ms");
    }
}
