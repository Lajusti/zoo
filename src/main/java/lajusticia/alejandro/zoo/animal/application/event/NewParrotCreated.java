package lajusticia.alejandro.zoo.animal.application.event;

import lajusticia.alejandro.zoo.animal.domain.entity.Parrot;
import lombok.Getter;
import org.springframework.context.ApplicationEvent;

@Getter
public class NewParrotCreated extends ApplicationEvent {

    private final Parrot parrot;

    public NewParrotCreated(Object source, Parrot parrot) {
        super(source);
        this.parrot = parrot;
    }

}
