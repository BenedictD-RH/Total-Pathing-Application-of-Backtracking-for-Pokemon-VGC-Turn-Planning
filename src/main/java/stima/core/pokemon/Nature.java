package stima.core.pokemon;

public enum Nature {
    HARDY(Stat.ATK, Stat.ATK),
    LONELY(Stat.ATK, Stat.DEF),
    ADAMANT(Stat.ATK, Stat.SPA),
    NAUGHTY(Stat.ATK, Stat.SPD),
    BRAVE(Stat.ATK, Stat.SPE),
    BOLD(Stat.DEF, Stat.ATK),
    DOCILE(Stat.DEF, Stat.DEF),
    IMPISH(Stat.DEF, Stat.SPA),
    LAX(Stat.DEF, Stat.SPD),
    RELAXED(Stat.DEF, Stat.SPE),
    MODEST(Stat.SPA, Stat.ATK),
    MILD(Stat.SPA, Stat.DEF),
    BASHFUL(Stat.SPA, Stat.SPA),
    RASH(Stat.SPA, Stat.SPD),
    QUIET(Stat.SPA, Stat.SPE),
    CALM(Stat.SPD, Stat.ATK),
    GENTLE(Stat.SPD, Stat.DEF),
    CAREFUL(Stat.SPD, Stat.SPA),
    QUIRKY(Stat.SPD, Stat.SPD),
    SASSY(Stat.SPD, Stat.SPE),
    TIMID(Stat.SPE, Stat.ATK),
    HASTY(Stat.SPE, Stat.DEF),
    JOLLY(Stat.SPE, Stat.SPA),
    NAIVE(Stat.SPE, Stat.SPD),
    SERIOUS(Stat.SPE, Stat.SPE);

    private Stat raised;
    private Stat lowered;
    private Nature(Stat raised, Stat lowered) {
        this.raised = raised;
        this.lowered = lowered;
    }

    public float getAligmentMultiplier(Stat stat) {
        if (raised == lowered) {
            return 1;
        } else if (stat == raised) {
            return 1.1f;
        } else if (stat == lowered) {
            return 0.9f;
        } else {
            return 1;
        }
    }
}
