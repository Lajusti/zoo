package lajusticia.alejandro.zoo.animal.application.event;

import lajusticia.alejandro.zoo.animal.domain.entity.Chicken;
import lombok.Getter;
import org.springframework.context.ApplicationEvent;

import java.util.Objects;

@Getter
public class NewChickenCreated extends ApplicationEvent {

    private final Chicken chicken;

    public NewChickenCreated(Object source, Chicken chicken) {
        super(source);
        this.chicken = chicken;
    }

}
