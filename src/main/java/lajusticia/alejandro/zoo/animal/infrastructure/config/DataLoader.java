package lajusticia.alejandro.zoo.animal.infrastructure.config;

import lajusticia.alejandro.zoo.animal.application.create.CreateChicken;
import lajusticia.alejandro.zoo.animal.application.create.CreateDog;
import lajusticia.alejandro.zoo.animal.application.create.CreateParrot;
import lajusticia.alejandro.zoo.animal.domain.type.DogType;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.Profile;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
@Profile("!test")
public class DataLoader {

    private final CreateChicken createChicken;
    private final CreateDog createDog;
    private final CreateParrot createParrot;

    public DataLoader(final CreateChicken createChicken, final CreateDog createDog, final CreateParrot createParrot) {
        this.createChicken = createChicken;
        this.createDog = createDog;
        this.createParrot = createParrot;
    }

    @EventListener(ApplicationReadyEvent.class)
    public void loadData(){
        createDog.create("Killian", "Meat", DogType.HUNTING_DOG);
        createParrot.create("Parrot one", "Grain", 0.25f, false);
        createChicken.create("Chicken one", "Corn", 0.75f, true);
        createDog.create("Rocky", "Fresh meat", DogType.WORKING_DOG);
        createParrot.create("Parrot two", "Corn", 0.5f, true);
        createDog.create("Peter", "Pedigree", DogType.SPORT_DOG);
        createChicken.create("Rocky", "Corn", 0.75f, false);
    }

}
