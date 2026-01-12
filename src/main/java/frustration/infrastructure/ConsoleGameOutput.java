package frustration.infrastructure;

import frustration.ports.GameOutput;
import org.springframework.stereotype.Component;

@Component
public class ConsoleGameOutput implements GameOutput {

    @Override
    public void println(String message) {
        System.out.println(message);
    }
}

