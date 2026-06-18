package stima.core.battle;

import java.util.ArrayList;
import java.util.List;

public class RNGSeed {
    private final List<Boolean> probabilities;
    private int i;

    public RNGSeed(String seed) {
        this.probabilities = new ArrayList<>();
        for (char c : seed.toCharArray()) {
            probabilities.add(c == '1');
        }
        this.i = 0;
    }

    public boolean consume() {
        return probabilities.get(i++);
    }
}
