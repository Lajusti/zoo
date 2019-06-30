package lajusticia.alejandro.zoo.animal.infrastructure.notification;

import lajusticia.alejandro.zoo.animal.application.event.NewDayProcessed;
import lajusticia.alejandro.zoo.animal.domain.entity.Animal;
import lajusticia.alejandro.zoo.animal.domain.entity.AwakeningResult;
import lajusticia.alejandro.zoo.animal.domain.entity.Dog;
import lajusticia.alejandro.zoo.animal.domain.entity.Parrot;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class NewDayDataInConsole {

    private final static String LOST_MESSAGE = "%s %s has lose friendship with %s %s";
    private final static String NEW_MESSAGE = "%s %s has established friendship with %s %s";

    @EventListener
    public void handleNewDayProcessed(NewDayProcessed newDayProcessed) {
        List<AwakeningResult> awakeningResults = newDayProcessed.getResultOfNewDayProcessed();
        awakeningResults.forEach(awakeningResult -> {
            if (awakeningResult.getFriendLost().isPresent()) {
                Animal lostFriend = awakeningResult.getFriendLost().get();
                System.out.println(
                        String.format(
                                LOST_MESSAGE,
                                getTypeOfAnimal(awakeningResult.getAnimal()),
                                awakeningResult.getAnimal().getName(),
                                getTypeOfAnimal(lostFriend),
                                lostFriend.getName()
                        )
                );
            }

            if (awakeningResult.getFriendNew().isPresent()) {
                Animal newFriend = awakeningResult.getFriendNew().get();
                System.out.println(
                        String.format(
                                NEW_MESSAGE,
                                getTypeOfAnimal(awakeningResult.getAnimal()),
                                awakeningResult.getAnimal().getName(),
                                getTypeOfAnimal(newFriend),
                                newFriend.getName()
                        )
                );
            }
        });
    }

    private String getTypeOfAnimal(Animal animal) {
        if (animal instanceof Dog) {
            return "Dog";
        }

        if (animal instanceof Parrot) {
            return "Parrot";
        }

        return "Chicken";
    }

}
