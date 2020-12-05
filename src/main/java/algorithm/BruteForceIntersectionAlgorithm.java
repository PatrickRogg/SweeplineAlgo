package algorithm;

import model.Line;

import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static algorithm.IntersectionHelper.doIntersect;

public class BruteForceIntersectionAlgorithm implements IntersectionAlgorithm {
    public int getIntersectionCountOf(final List<Line> lines) {
        int intersectionCount = 0;
        lines.sort(Comparator.comparingDouble(a -> a.getLeft().getX()));

        for (int i = 0; i < lines.size(); i++) {
            final Line line1 = lines.get(i);

            for (int j = i + 1; j < lines.size(); j++) {
                final Line line2 = lines.get(j);

                if (isLine2NotInXRange(line1, line2)) {
                    break;
                }

                if (doIntersect(line1, line2)) {
                    intersectionCount++;
                }
            }
        }

        return intersectionCount;
    }

    private boolean isLine2NotInXRange(final Line line1, final Line line2) {
        return line1.getRight().getX() < line2.getLeft().getX();
    }
}
