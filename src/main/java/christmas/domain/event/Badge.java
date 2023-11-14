package christmas.domain.event;

import java.util.Arrays;

public enum Badge {
    SANTA("산타", 20000),
    TREE("트리", 10000),
    STAR("별", 5000),
    NONE("없음", 0);

    private final String name;
    private final int thresholdPrice;

    Badge(String name, int thresholdPrice) {
        this.name = name;
        this.thresholdPrice = thresholdPrice;
    }

    public static Badge getBadge(int totalBenefits) {
        return Arrays.stream(values())
                .filter(badge -> badge.thresholdPrice <= totalBenefits)
                .findFirst()
                .orElse(null);
    }

    public String getName() {
        return name;
    }
}
