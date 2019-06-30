package lajusticia.alejandro.zoo.animal.infrastructure;

import lajusticia.alejandro.zoo.animal.domain.entity.AnimalAwakening;
import lajusticia.alejandro.zoo.animal.domain.utils.AnimalAwakeningSelector;

import java.util.List;
import java.util.Optional;

public class AnimalAwakeningSelectorForTest implements AnimalAwakeningSelector {

    @Override
    public Optional<AnimalAwakening> selectAnimalAwakening(List<AnimalAwakening> animals) {
        if (animals.isEmpty()) {
            return Optional.empty();
        }
        return Optional.of(animals.get(0));
    }

}
