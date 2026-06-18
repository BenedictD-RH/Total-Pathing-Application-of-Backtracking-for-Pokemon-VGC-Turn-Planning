package stima;

import java.util.EnumMap;
import java.util.List;

import javafx.application.Application;
import javafx.stage.Stage;
import stima.algorithms.RNGSeedGenerator;
import stima.core.battle.BattleState;
import stima.core.battle.Battlefield;
import stima.core.battle.MoveAction;
import stima.core.battle.RNGSeed;
import stima.core.battle.TeamState;
import stima.core.battle.TurnManager;
import stima.core.moves.Move;
import stima.core.pokemon.Nature;
import stima.core.pokemon.Pokemon;
import stima.core.pokemon.PokemonSpecies;
import stima.core.pokemon.Stat;
import stima.core.pokemon.Type;
import stima.core.pokemon.Typing;


public class App extends Application {
    @Override
    public void start(Stage stage) throws Exception {

    }

    public static void main(String[] args) {
        //Application.launch(App.class, new String[0]);
        PokemonSpecies species1 = new PokemonSpecies("Incineroar", 
                                                     new Typing(Type.FIRE, Type.DARK), 
                                                     List.of(95, 115, 90, 80, 90, 60));
        PokemonSpecies species2 = new PokemonSpecies("Garchomp", 
                                                     new Typing(Type.DRAGON, Type.GROUND), 
                                                     List.of(108, 130, 95, 80, 85, 102));
        PokemonSpecies species3 = new PokemonSpecies("Kingambit", 
                                                     new Typing(Type.STEEL, Type.DARK), 
                                                     List.of(100, 135, 120, 60, 85, 50));
        PokemonSpecies species4 = new PokemonSpecies("Sneasler", 
                                                     new Typing(Type.POISON, Type.FIGHTING), 
                                                     List.of(80, 130, 60, 40, 80, 120));
        EnumMap<Stat, Integer> evs = new EnumMap<>(Stat.class);
        for (Stat s : Stat.values()) {
            evs.put(s, 0);
        }

        Pokemon pokemon1 = new Pokemon(species1, 
                                       Nature.ADAMANT, 
                                       evs, 
                                       List.of(Move.CLOSE_COMBAT, Move.THROAT_CHOP, Move.FAKE_OUT, Move.HEAT_WAVE));
        Pokemon pokemon2 = new Pokemon(species2, 
                                       Nature.JOLLY, 
                                       evs, 
                                       List.of(Move.ROCK_SLIDE, Move.DRAGON_CLAW, Move.PROTECT, Move.EARTHQUAKE));
        Pokemon pokemon3 = new Pokemon(species3, 
                                       Nature.ADAMANT, 
                                       evs, 
                                       List.of(Move.IRON_HEAD, Move.SUCKER_PUNCH, Move.PROTECT, Move.KOWTOW_CLEAVE));
        Pokemon pokemon4 = new Pokemon(species4, 
                                       Nature.ADAMANT, 
                                       evs, 
                                       List.of(Move.CLOSE_COMBAT, Move.DIRE_CLAW, Move.FAKE_OUT, Move.PROTECT));
                                       
        Battlefield field = new Battlefield();
        TeamState team1 = new TeamState(0, List.of(pokemon1, pokemon2));
        TeamState team2 = new TeamState(1, List.of(pokemon3, pokemon4));
        BattleState battle = new BattleState(team1, team2, field);
        TurnManager turnManager = new TurnManager(battle);
        
        team1.getPokemonOnSlot(0).setChosenAction(new MoveAction(0, 3, true, 0));
        team1.getPokemonOnSlot(1).setChosenAction(new MoveAction(1, 3, true, 1));
        team2.getPokemonOnSlot(0).setChosenAction(new MoveAction(0, 2, true, 0));
        team2.getPokemonOnSlot(1).setChosenAction(new MoveAction(1, 1, true, 1));
        turnManager.turnStart();
        for (int i = 0; i < turnManager.getTurnOrder().size(); i++) {
            turnManager.doTurn(i, turnManager.getRNGSeedsForTurnOrder(i).get(0));
        }
        turnManager.turnEnd();
        turnManager.printTurnState();
    }   
}
