package model;

import lombok.Getter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Getter
public class SweepLine {
    private final List<Line> lines;

    public SweepLine() {
        this.lines = new ArrayList<>();
    }

    public void add(final Line line) {
        for (final Line l : lines) {
            l.calculateValue(line.getLeft().getX());
        }

        lines.add(line);
        Collections.sort(lines);
    }

    public void remove(final Line line) {
        lines.remove(line);
    }

    public Line lower(final Line line) {
        int index = getLineIndex(line);
        index--;
        return index < 0 ? null : lines.get(index);
    }

    public Line higher(final Line line) {
        int index = getLineIndex(line);
        index++;
        return index >= lines.size() ? null : lines.get(index);
    }

    public int getLineIndex(final Line line) {
        int i = Collections.binarySearch(this.lines, line);
        int right = i;

        if (this.lines.get(i) == line) {
            return i;
        }

        while (right < this.lines.size() && this.lines.get(right) != line && this.lines.get(i).getValue() == line.getValue()) {
            right++;
        }

        if (right < this.lines.size() && line == this.lines.get(right)) {
            return right;
        }

        int left = i;
        while (0 <= left && this.lines.get(left) != line && this.lines.get(i).getValue() == line.getValue()) {
            left--;
        }

        return left;
    }

    public void swap(final Line line1, final Line line2) {
        int indexLine1 = getLineIndex(line1);
        int indexLine2 = getLineIndex(line2);
        final double temp = this.lines.get(indexLine1).getValue();
        this.lines.get(indexLine1).setValue(this.lines.get(indexLine2).getValue());
        this.lines.get(indexLine2).setValue(temp);
        Collections.swap(lines, indexLine1, indexLine2);
    }

    public List<Line> lowerLines(final Line line) {
        int startIndex = getLineIndex(line);
        int index = startIndex - 1;
        final List<Line> higherLines = new ArrayList<>();

        while (index >= 0) {
            if (index + 1 != startIndex && line.getValue() != lines.get(index).getValue()) {
                break;
            }
            higherLines.add(lines.get(index));
            index--;
        }

        return higherLines;
    }

    public List<Line> higherLines(final Line line) {
        int startIndex = getLineIndex(line);
        int index = startIndex + 1;
        final List<Line> higherLines = new ArrayList<>();

        while (index < lines.size()) {
            if (index - 1 != startIndex && line.getValue() != lines.get(index).getValue()) {
                break;
            }
            higherLines.add(lines.get(index));
            index++;
        }

        return higherLines;
    }
}
