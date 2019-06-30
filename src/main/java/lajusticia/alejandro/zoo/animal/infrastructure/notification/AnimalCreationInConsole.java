package lajusticia.alejandro.zoo.animal.infrastructure.notification;

import lajusticia.alejandro.zoo.animal.application.event.NewChickenCreated;
import lajusticia.alejandro.zoo.animal.application.event.NewDogCreated;
import lajusticia.alejandro.zoo.animal.application.event.NewParrotCreated;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class AnimalCreationInConsole {

    @EventListener
    public void handleDogCreated(NewDogCreated newDogCreated) {
        System.out.println("Dog created: " + newDogCreated.getDog());
    }

    @EventListener
    public void handleParrotCreated(NewParrotCreated newParrotCreated) {
        System.out.println("Parrot created: " + newParrotCreated.getParrot());
    }

    @EventListener
    public void handleChickenCreated(NewChickenCreated newChickenCreated) {
        System.out.println("Chicken created: " + newChickenCreated.getChicken());
    }

}
