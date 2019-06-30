package lajusticia.alejandro.zoo.animal.application.event;

import lajusticia.alejandro.zoo.animal.domain.entity.Dog;
import lombok.Getter;
import org.springframework.context.ApplicationEvent;

@Getter
public class NewDogCreated extends ApplicationEvent {

    private final Dog dog;

    public NewDogCreated(Object source, Dog dog) {
        super(source);
        this.dog = dog;
    }

}
