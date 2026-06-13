package stima.core.pokemon;

public enum Stat {
    HP {
        @Override
        public int calculateStat(int base, int sp, Nature alignment) {
            return base + sp + 75;
        }
    },
    ATK,
    DEF,
    SPA,
    SPD,
    SPE;

    public int calculateStat(int base, int sp, Nature alignment) {
        return (int) Math.floor((base + sp + 20) * alignment.getAligmentMultiplier(this));
    }
}
