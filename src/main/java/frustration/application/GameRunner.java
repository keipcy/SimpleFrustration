package frustration.application;

import frustration.game.Game;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GameRunner {

    @Bean
    CommandLineRunner run(Game game) {
        return args -> game.start();
    }
}