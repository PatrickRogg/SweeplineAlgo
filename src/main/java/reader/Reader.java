package reader;

import model.Line;
import model.Point;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Reader {
    public List<Line> getLines(final String fileName) throws URISyntaxException, IOException {
        final Path path = Paths.get(getClass().getClassLoader().getResource(fileName).toURI());

        final Stream<String> lines = Files.lines(path);
        final AtomicInteger id = new AtomicInteger();
        final List<Line> result = lines
                .map(s -> s.split(" "))
                .map(v -> createLine(v, id.getAndIncrement()))
                .collect(Collectors.toList());

        lines.close();

        return result;
    }

    private Line createLine(final String[] v, final int id) {
        return new Line(id, new Point(Double.parseDouble(v[0]), Double.parseDouble(v[1])), new Point(Double.parseDouble(v[2]), Double.parseDouble(v[3])));
    }
}
