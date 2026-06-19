package stima.core.pokemon;

public enum Type {
    NORMAL {
        @Override
        public float attackMultiplier(Type defendingType) {
            switch(defendingType) {
                case ROCK:
                case STEEL:
                    return 0.5f;
                case GHOST:
                    return 0.0f;
                default:
                    return 1;
            }
        }
    },
    FIRE {
        @Override
        public float attackMultiplier(Type defendingType) {
            switch(defendingType) {
                case GRASS:
                case ICE:
                case BUG:
                case STEEL:
                    return 2.0f;
                case FIRE:
                case WATER:
                case ROCK:
                case DRAGON:
                    return 0.5f;
                default:
                    return 1;
            }
        }
    },
    WATER {
        @Override
        public float attackMultiplier(Type defendingType) {
            switch(defendingType) {
                case FIRE:
                case GROUND:
                case ROCK:
                    return 2.0f;
                case WATER:
                case GRASS:
                case DRAGON:
                    return 0.5f;
                default:
                    return 1;
            }
        }
    },
    GRASS {
        @Override
        public float attackMultiplier(Type defendingType) {
            switch(defendingType) {
                case WATER:
                case ROCK:
                case GROUND:
                    return 2.0f;
                case FIRE:
                case FLYING:
                case GRASS:
                case STEEL:
                case BUG:
                case POISON:
                case DRAGON:
                    return 0.5f;
                default:
                    return 1;
            }
        }
    },
    ELECTRIC {
        @Override
        public float attackMultiplier(Type defendingType) {
            switch(defendingType) {
                case FLYING:
                case WATER:
                    return 2.0f;
                case ELECTRIC:
                case GRASS:
                case DRAGON:
                    return 0.5f;
                case GROUND:
                    return 0;
                default:
                    return 1;
            }
        }
    },
    BUG {
        @Override
        public float attackMultiplier(Type defendingType) {
            switch(defendingType) {
                case PSYCHIC:
                case DARK:
                case GRASS:
                    return 2.0f;
                case FLYING:
                case FIRE:
                case STEEL:
                case FAIRY:
                case FIGHTING:
                case GHOST:
                case POISON:
                    return 0.5f;
                default:
                    return 1;
            }
        }
    },
    FLYING {
        @Override
        public float attackMultiplier(Type defendingType) {
            switch(defendingType) {
                case BUG:
                case GRASS:
                case FIGHTING:
                    return 2.0f;
                case ELECTRIC:
                case ROCK:
                case STEEL:
                    return 0.5f;
                default:
                    return 1;
            }
        }
    },
    ROCK {
        @Override
        public float attackMultiplier(Type defendingType) {
            switch(defendingType) {
                case FIRE:
                case ICE:
                case BUG:
                case FLYING:
                    return 2.0f;
                case STEEL:
                case FIGHTING:
                case GROUND:
                    return 0.5f;
                default:
                    return 1;
            }
        }
    },
    GROUND {
        @Override
        public float attackMultiplier(Type defendingType) {
            switch(defendingType) {
                case FIRE:
                case ELECTRIC:
                case ROCK:
                case STEEL:
                case POISON:
                    return 2.0f;
                case GRASS:
                case BUG:
                    return 0.5f;
                case FLYING:
                    return 0;
                default:
                    return 1;
            }
        }
    },
    ICE {
        @Override
        public float attackMultiplier(Type defendingType) {
            switch(defendingType) {
                case FLYING:
                case GROUND:
                case GRASS:
                case DRAGON:
                    return 2.0f;
                case WATER:
                case FIRE:
                case ICE:
                case STEEL:
                    return 0.5f;
                default:
                    return 1;
            }
        }
    },
    POISON {
        @Override
        public float attackMultiplier(Type defendingType) {
            switch(defendingType) {
                case GRASS:
                case FAIRY:
                    return 2.0f;
                case POISON:
                case GHOST:
                case ROCK:
                case GROUND:
                    return 0.5f;
                case STEEL:
                    return 0;
                default:
                    return 1;
            }
        }
    },
    FIGHTING {
        @Override
        public float attackMultiplier(Type defendingType) {
            switch(defendingType) {
                case NORMAL:
                case STEEL:
                case ROCK:
                case DARK:
                case ICE:
                    return 2.0f;
                case POISON:
                case FLYING:
                case BUG:
                case PSYCHIC:
                case FAIRY:
                    return 0.5f;
                case GHOST:
                    return 0;
                default:
                    return 1;
            }
        }
    },
    PSYCHIC {
        @Override
        public float attackMultiplier(Type defendingType) {
            switch(defendingType) {
                case FIGHTING:
                case POISON:
                    return 2.0f;
                case STEEL:
                case PSYCHIC:
                    return 0.5f;
                case DARK:
                    return 0;
                default:
                    return 1;
            }
        }
    },
    GHOST {
        @Override
        public float attackMultiplier(Type defendingType) {
            switch(defendingType) {
                case GHOST:
                case PSYCHIC:
                    return 2.0f;
                case DARK:
                    return 0.5f;
                case NORMAL:
                    return 0;
                default:
                    return 1;
            }
        }
    },
    DRAGON {
        @Override
        public float attackMultiplier(Type defendingType) {
            switch(defendingType) {
                case DRAGON:
                    return 2.0f;
                case STEEL:
                    return 0.5f;
                case FAIRY:
                    return 0;
                default: 
                    return 1;
            }
        }
    },
    DARK {
        @Override
        public float attackMultiplier(Type defendingType) {
            switch(defendingType) {
                case GHOST:
                case PSYCHIC:
                    return 2.0f;
                case FIGHTING:
                case FAIRY:
                case DARK:
                    return 0.5f;
                default:
                    return 1;
            }
        }
    },
    STEEL {
        @Override
        public float attackMultiplier(Type defendingType) {
            switch(defendingType) {
                case ICE:
                case FAIRY:
                case ROCK:
                    return 2.0f;
                case FIRE:
                case WATER:
                case ELECTRIC:
                case STEEL:
                    return 0.5f;
                default:
                    return 1;
            }
        }
    },
    FAIRY {
        @Override
        public float attackMultiplier(Type defendingType) {
            switch(defendingType) {
                case DARK:
                case DRAGON:
                case FIGHTING:
                    return 2.0f;
                case STEEL:
                case FIRE:
                case POISON:
                    return 0.5f;
                default:
                    return 1;
            }
        }
    },
    TYPELESS {
        @Override
        public float attackMultiplier(Type defendingType) {
           return 1;
        }
    };

    public abstract float attackMultiplier(Type defendingType);

    public float defenseMultiplier(Type attackingType) {
            return attackingType.attackMultiplier(this);
    }
}
