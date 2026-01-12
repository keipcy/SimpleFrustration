package frustration.application;

import frustration.dice.DiceShaker;
import frustration.dice.RandomDoubleDiceShaker;
import frustration.game.Game;
import frustration.game.GameConfig;
import frustration.game.GameConfiguration;
import frustration.hitrule.HitRule;
import frustration.hitrule.IgnoreHitRule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import frustration.ports.GameOutput;
import frustration.wincondition.OvershootAllowedWinCondition;
import frustration.wincondition.WinCondition;

@Configuration
public class GameBeanConfig {

    @Bean
    public DiceShaker diceShaker() {
        return new RandomDoubleDiceShaker(); // default: two dice
    }

    @Bean
    public WinCondition winCondition() {
        return new OvershootAllowedWinCondition(); // default basic rule
    }

    @Bean
    public HitRule hitRule() {
        return new IgnoreHitRule(); // default: no hit rule
    }

    @Bean
    public GameConfig gameConfig() {
        return GameConfiguration.basicTwoPlayer(); // default config
    }

    @Bean
    public Game game(
            DiceShaker shaker,
            WinCondition win,
            HitRule hit,
            GameConfig config,
            GameOutput out
    ) {
        return new Game(shaker, win, hit, config, out);
    }
}
