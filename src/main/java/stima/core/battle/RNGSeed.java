package stima.core.battle;

import java.util.ArrayList;
import java.util.List;

public class RNGSeed {
    private final List<Boolean> probabilities;
    private String seedLog;
    private int i;

    public RNGSeed(String seed, List<String> seedLog) {
        this.probabilities = new ArrayList<>();
        int j = 0;
        this.seedLog = "";
        for (char c : seed.toCharArray()) {
            probabilities.add(c == '1');
            this.seedLog += seedLog.get(j) + (c == '1' ? "[TRUE]" : "[FALSE]") + "\n";
            j++;
        }
        this.i = 0;
    }

    public boolean consume() {
        return probabilities.get(i++);
    }

    public String getSeedLog() {
        return seedLog;
    }
}
