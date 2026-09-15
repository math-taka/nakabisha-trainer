package nakabisha_trainer.model;

public final class NakabishaDetector {

    private NakabishaDetector() {
    }

    public static boolean isNakabisha(
            PositionFeatures features,
            Side side) {

        return features.hisha().stream()
                .anyMatch(feature ->
                        feature.side() == side
                                && feature.isOnFile(5));
    }
}
