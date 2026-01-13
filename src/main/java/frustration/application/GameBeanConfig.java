package frustration.application;

import frustration.dice.DiceShaker;
import frustration.dice.RandomDoubleDiceShaker;
import frustration.dice.RandomSingleDiceShaker;
import frustration.game.GameConfig;
import frustration.game.GameConfiguration;
import frustration.hitrule.ForfeitOnHitRule;
import frustration.hitrule.HitRule;
import frustration.hitrule.IgnoreHitRule;
import frustration.wincondition.ExactLandingWinCondition;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import frustration.wincondition.OvershootAllowedWinCondition;
import frustration.wincondition.WinCondition;

@Configuration
public class GameBeanConfig {

    @Bean("single")
    public DiceShaker singleDice() { return new RandomSingleDiceShaker(); }

    @Bean("double")
    public DiceShaker doubleDice() { return new RandomDoubleDiceShaker(); }

    @Bean("overshoot")
    public WinCondition overshootWin() { return new OvershootAllowedWinCondition(); }

    @Bean("exact")
    public WinCondition exactWin() { return new ExactLandingWinCondition(); }

    @Bean("ignoreHit")
    public HitRule ignoreHit() { return new IgnoreHitRule(); }

    @Bean("forfeitHit")
    public HitRule forfeitHit() { return new ForfeitOnHitRule(); }

    @Bean("twoPlayer")
    public GameConfig twoPlayerConfig() { return GameConfiguration.basicTwoPlayer(); }

    @Bean("fourPlayer")
    public GameConfig fourPlayerConfig() { return GameConfiguration.largeFourPlayer(); }
}
