package stima;

import java.util.EnumMap;
import java.util.List;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.stage.Stage;
import stima.algorithms.RNGSeedGenerator;
import stima.algorithms.TreeBuilder;
import stima.algorithms.TreeNode;
import stima.core.abilities.Defiant;
import stima.core.abilities.Intimidate;
import stima.core.abilities.RoughSkin;
import stima.core.abilities.Unburden;
import stima.core.battle.BattleState;
import stima.core.battle.Battlefield;
import stima.core.battle.MoveAction;
import stima.core.battle.RNGSeed;
import stima.core.battle.TeamState;
import stima.core.battle.TurnManager;
import stima.core.items.ChoiceScarf;
import stima.core.items.FocusSash;
import stima.core.items.SitrusBerry;
import stima.core.items.WhiteHerb;
import stima.core.moves.Move;
import stima.core.pokemon.Nature;
import stima.core.pokemon.Pokemon;
import stima.core.pokemon.PokemonSpecies;
import stima.core.pokemon.Stat;
import stima.core.pokemon.Type;
import stima.core.pokemon.Typing;
import stima.gui.TreeView;


public class App extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
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
        EnumMap<Stat, Integer> evs1 = new EnumMap<>(Stat.class);
        EnumMap<Stat, Integer> evs2 = new EnumMap<>(Stat.class);
        EnumMap<Stat, Integer> evs3 = new EnumMap<>(Stat.class);
        EnumMap<Stat, Integer> evs4 = new EnumMap<>(Stat.class);
        for (Stat s : Stat.values()) {
            evs1.put(s, 0);
            evs2.put(s, 0);
            evs3.put(s, 0);
            evs4.put(s, 0);
        }

        evs1.put(Stat.HP, 32);
        evs1.put(Stat.ATK, 32);
        evs1.put(Stat.DEF, 2);

        evs2.put(Stat.HP, 2);
        evs2.put(Stat.ATK, 32);
        evs2.put(Stat.SPE, 32);

        evs3.put(Stat.HP, 32);
        evs3.put(Stat.ATK, 32);
        evs3.put(Stat.DEF, 2);

        evs4.put(Stat.HP, 2);
        evs4.put(Stat.ATK, 32);
        evs4.put(Stat.SPE, 32);

        Pokemon pokemon1 = new Pokemon(species1, 
                                       Nature.ADAMANT, 
                                       evs1, 
                                       List.of(Move.CLOSE_COMBAT, Move.THROAT_CHOP, Move.FAKE_OUT, Move.HEAT_WAVE),
                                       new SitrusBerry(),
                                       new Intimidate());
        Pokemon pokemon2 = new Pokemon(species2, 
                                       Nature.JOLLY, 
                                       evs2, 
                                       List.of(Move.ROCK_SLIDE, Move.DRAGON_CLAW, Move.PROTECT, Move.EARTHQUAKE),
                                       new ChoiceScarf(),
                                       new RoughSkin());
        Pokemon pokemon3 = new Pokemon(species3, 
                                       Nature.ADAMANT, 
                                       evs3, 
                                       List.of(Move.IRON_HEAD, Move.SUCKER_PUNCH, Move.PROTECT, Move.KOWTOW_CLEAVE),
                                       new FocusSash(),
                                       new Defiant());
        Pokemon pokemon4 = new Pokemon(species4, 
                                       Nature.ADAMANT, 
                                       evs4, 
                                       List.of(Move.CLOSE_COMBAT, Move.DIRE_CLAW, Move.FAKE_OUT, Move.PROTECT), 
                                       new WhiteHerb(),
                                       new Unburden());
                                        
        Battlefield field = new Battlefield();
        TeamState team1 = new TeamState(0, List.of(pokemon1, pokemon2));
        TeamState team2 = new TeamState(1, List.of(pokemon3, pokemon4));
        BattleState battle = new BattleState(team1, team2, field);
        TreeBuilder treeBuilder = new TreeBuilder();
        TreeNode node = treeBuilder.buildRoot(battle, 2);
        if (node != null) {
            TreeView.showTree(primaryStage, node);
        } else {
            System.out.println("No Win conditions found");
        }
    }

    public static void main(String[] args) throws Exception {
        Application.launch(App.class, new String[0]);
        
    }   
}
