import model.Level;
import org.junit.jupiter.api.Test;
import processors.OrderConsumer;
import processors.OrderProducer;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.stream.Collectors;

public class IntegrationTest {
    @Test
    void IntegrationTest() throws IOException {
        var expectedResults=generateExpectedResults();
        var orderQueue = new LinkedBlockingQueue<>();
        var orderProducer = new OrderProducer(orderQueue);
        var orderConsumer = new OrderConsumer(orderQueue);

        Files.walk(Path.of("src/test/resources")).
                filter(Files::isRegularFile).
                collect(Collectors.toList()).parallelStream().forEach(filePath -> {
            try {
                Files.readAllLines(filePath).forEach(line -> {
                    try {
                        orderProducer.parseOrder(line);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                });
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        orderConsumer.processOrders();
        var report = orderConsumer.report(5);
        report.forEach((k,v) -> {
            assert expectedResults.get(k).size() == v.size();
            var expectedLvlIterator = expectedResults.get(k).iterator();
            var actualLvlIterator = v.iterator();
            while (expectedLvlIterator.hasNext() && actualLvlIterator.hasNext()) {
                var expectedItem = expectedLvlIterator.next();
                var actualItem = actualLvlIterator.next();
                assert expectedItem.equals(actualItem);
            }
        });
    }

    Map<String, Set<Level>> generateExpectedResults() {
        var expectedResults = new LinkedHashMap<String, Set<Level>>();
        Set<Level> levelABC = new LinkedHashSet<Level>();
        levelABC.add(new Level(96.0, 45, 102.5, 65));
        levelABC.add(new Level(98.0, 45, 101.8, 25));
        levelABC.add(new Level(98.3, 50, 101.5, 25));
        levelABC.add(new Level(98.8, 45, 101.4, 25));
        levelABC.add(new Level(99.0, 50, 101.1, 25));
        expectedResults.put("ABC", levelABC);

        Set<Level> levelDEF = new LinkedHashSet<Level>();
        levelDEF.add(new Level(48.0, 45, 51.6, 35));
        levelDEF.add(new Level(48.1, 65, 51.5, 35));
        levelDEF.add(new Level(48.3, 45, 51.4, 55));
        levelDEF.add(new Level(48.8, 45, 51.0, 50));
        expectedResults.put("DEF", levelDEF);

        Set<Level> levelXYZ = new LinkedHashSet<Level>();
        levelXYZ.add(new Level(198.0, 45, 201.7, 55));
        levelXYZ.add(new Level(198.1, 65, 201.5, 65));
        levelXYZ.add(new Level(198.2, 45, 201.4, 25));
        levelXYZ.add(new Level(198.5, 55, 201.0, 50));
        levelXYZ.add(new Level(198.6, 55, 200.6, 25));
        expectedResults.put("XYZ", levelXYZ);

        Set<Level> levelLMN = new LinkedHashSet<Level>();
        levelLMN.add(new Level(298.0, 45, 301.7, 55));
        levelLMN.add(new Level(298.1, 65, 301.0, 50));
        levelLMN.add(new Level(298.2, 45, 300.6, 25));
        levelLMN.add(new Level(298.5, 55, 300.5, 25));
        levelLMN.add(new Level(298.6, 55, 300.0, 45));
        expectedResults.put("LMN", levelLMN);

        return expectedResults;
    }
}
