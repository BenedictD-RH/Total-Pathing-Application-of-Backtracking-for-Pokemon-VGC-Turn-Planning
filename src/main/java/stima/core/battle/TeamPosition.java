package stima.core.battle;

import java.util.ArrayList;
import java.util.List;

public class TeamPosition extends FieldState {
    private final List<BattlePosition> positions;
    private final Battlefield battlefield;

    public TeamPosition(Battlefield battlefield) {
        positions = new ArrayList<>();
        positions.add(new BattlePosition(this));
        positions.add(new BattlePosition(this));
        this.battlefield = battlefield;
    }

    public TeamPosition(TeamPosition other, Battlefield otherField) {
        this.positions = new ArrayList<>();
        this.positions.add(new BattlePosition(other.getBattlePosition(0), this));
        this.positions.add(new BattlePosition(other.getBattlePosition(1), this));
        this.battlefield = otherField;
    }

    public Battlefield getBattlefield() {
        return battlefield;
    }

    public BattlePosition getBattlePosition(int id) {
        return positions.get(id);
    }
}
