package test.game;

import java.util.List;

public class ScenarioData {
    public static TestScenario basicScenario1ex1() {
        return new TestScenario(
                "Blue wins basic",
                List.of(12, 12, 7, 8),
                "blue"
        );

    }

    public static TestScenario basicScenario1ex2() {
        return new TestScenario(
                "Red wins basic",
                List.of(12, 12, 6, 6, 2),
                "red"
        );
    }
}
