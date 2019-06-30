package lajusticia.alejandro.zoo.animal.infrastructure.utils;

import lajusticia.alejandro.zoo.animal.domain.entity.AnimalAwakening;
import lajusticia.alejandro.zoo.animal.domain.utils.AnimalAwakeningSelector;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Random;

@Service
public class AnimalAwakeningRandomSelector implements AnimalAwakeningSelector {

    private final Random random = new Random();

    @Override
    public Optional<AnimalAwakening> selectAnimalAwakening(List<AnimalAwakening> animals) {
        if (animals.isEmpty()) {
            return Optional.empty();
        }

        int indexOfSelectedAnimalAwakening = random.nextInt(animals.size());
        AnimalAwakening selectedAnimalAwakening = animals.get(indexOfSelectedAnimalAwakening);
        return Optional.of(selectedAnimalAwakening);
    }

}
