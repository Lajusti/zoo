package lajusticia.alejandro.zoo.animal.infrastructure.console;

import lajusticia.alejandro.zoo.ExitApplication;
import lajusticia.alejandro.zoo.animal.application.read.ObtainAllAnimals;
import lajusticia.alejandro.zoo.animal.application.update.NewDay;
import lajusticia.alejandro.zoo.animal.infrastructure.console.exception.WrongCommandException;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.Profile;
import org.springframework.context.event.EventListener;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

@Component
@Order
@Profile("!test")
public class ConsoleReader {

    private final static List<String> COMMANDS = Arrays.asList(
            "list",
            "newDay",
            "exit"
    );

    private final ObtainAllAnimals obtainAllAnimals;
    private final NewDay newDay;
    private final ExitApplication exitApplication;

    public ConsoleReader(
            final ObtainAllAnimals obtainAllAnimals,
            final NewDay newDay,
            final ExitApplication exitApplication)
    {
        this.obtainAllAnimals = obtainAllAnimals;
        this.newDay = newDay;
        this.exitApplication = exitApplication;
    }

    @EventListener(ApplicationReadyEvent.class)
    public void readConsole() {
        Scanner scanner = new Scanner(System.in);
        boolean readNext = true;
        System.out.println("Console is ready to read commands");

        while (readNext && scanner.hasNext()) {
            String command = scanner.next();
            try {
                readNext = processCommand(command);
            } catch (WrongCommandException exception) {
                System.out.println(exception.getMessage());
            }
        }
    }

    private boolean processCommand(String command) throws WrongCommandException {
        if (command.equals(COMMANDS.get(0))) {
            obtainAllAnimals.obtainAllAnimalsInZoo().forEach(animal -> System.out.println(animal.toString()));
        } else if (command.equals(COMMANDS.get(1))) {
            newDay.awakeAllAnimalsInZoo();
        } else if (command.equals(COMMANDS.get(2))) {
            exitApplication.exit();
            return false;
        } else {
            throw new WrongCommandException(command, COMMANDS.toString());
        }
        return true;
    }

}
