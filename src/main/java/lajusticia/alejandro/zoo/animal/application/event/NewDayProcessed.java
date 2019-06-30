package lajusticia.alejandro.zoo.animal.application.event;

import lajusticia.alejandro.zoo.animal.domain.entity.AwakeningResult;
import lombok.Getter;
import org.springframework.context.ApplicationEvent;

import java.util.List;

@Getter
public class NewDayProcessed extends ApplicationEvent {

    private final List<AwakeningResult> resultOfNewDayProcessed;

    public NewDayProcessed(Object source, List<AwakeningResult> resultOfNewDayProcessed) {
        super(source);
        this.resultOfNewDayProcessed = resultOfNewDayProcessed;
    }

}
