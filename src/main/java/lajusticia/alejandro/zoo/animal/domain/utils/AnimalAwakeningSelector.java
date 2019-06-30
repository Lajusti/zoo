package lajusticia.alejandro.zoo.animal.domain.utils;

import lajusticia.alejandro.zoo.animal.domain.entity.AnimalAwakening;

import java.util.List;
import java.util.Optional;

public interface AnimalAwakeningSelector {

    /**
     * Given a list of {@link AnimalAwakening} select and return one of the list, if the list is empty the optional
     * returned will be empty
     *
     * @param animals list to select one
     * @return {@link Optional<AnimalAwakening>} with the {@link AnimalAwakening} selected if can be selected one
     */
    Optional<AnimalAwakening> selectAnimalAwakening(List<AnimalAwakening> animals);

}
