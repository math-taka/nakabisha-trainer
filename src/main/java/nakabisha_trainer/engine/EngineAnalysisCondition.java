package nakabisha_trainer.engine;

public record EngineAnalysisCondition(int depth) {

    public EngineAnalysisCondition {
        if (depth <= 0) {
            throw new IllegalArgumentException("depth must be positive");
        }
    }
}
