package nakabisha_trainer.engine;

public record EngineEvaluation(
        Integer value,
        Integer mateDistance) {

    public EngineEvaluation {
        if (value == null && mateDistance == null) {
            throw new IllegalArgumentException(
                    "value and mateDistance cannot both be null");
        }
    }
}
