package lajusticia.alejandro.zoo.animal.domain.service;

import lajusticia.alejandro.zoo.animal.domain.entity.Animal;
import lajusticia.alejandro.zoo.animal.domain.entity.AnimalAwakening;
import lajusticia.alejandro.zoo.animal.domain.entity.AwakeningResult;
import lajusticia.alejandro.zoo.animal.domain.utils.AnimalAwakeningSelector;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class AwakeningService {

    private final AnimalAwakeningSelector animalAwakeningSelector;

    public AwakeningService(final AnimalAwakeningSelector animalAwakeningSelector) {
        this.animalAwakeningSelector = animalAwakeningSelector;
    }

    public List<AwakeningResult> awakeAnimals(List<Animal> allAnimals) {
        Stream<AnimalAwakening> allAnimalsAwakenings = allAnimals.stream()
                .map(AnimalAwakening::new);

        List<AnimalAwakening> allAnimalAwakeningAsList = allAnimalsAwakenings.collect(Collectors.toList());

        List<AwakeningResult> awakeningResults = new ArrayList<>();
        allAnimalAwakeningAsList.forEach(currentAnimalAwakening -> {
            AwakeningResult awakeningResult = awakeAnimal(currentAnimalAwakening, allAnimalAwakeningAsList);
            awakeningResults.add(awakeningResult);
        });

        return awakeningResults;
    }

    private AwakeningResult awakeAnimal(
            AnimalAwakening currentAnimalAwakening,
            List<AnimalAwakening> allAnimalsAwakenings)
    {
        if (!currentAnimalAwakening.getFriendLost().isPresent()) {
            removeFriend(currentAnimalAwakening, allAnimalsAwakenings);
        }

        if (!currentAnimalAwakening.getFriendNew().isPresent()) {
            addFriend(currentAnimalAwakening, allAnimalsAwakenings);
        }

        return currentAnimalAwakening.getResult();
    }

    private void removeFriend(
            AnimalAwakening currentAnimalAwakening,
            List<AnimalAwakening> allAnimalsAwakenings)
    {
        List<AnimalAwakening> animalsAwakeningCanBeRemoved = allAnimalsAwakenings.stream()
                .filter(possibleAnimalAwakeningToBeRemoved ->
                        animalAwakeningCanBeRemovedAsFriendOfCurrentAnimalAwakening(
                                currentAnimalAwakening,
                                possibleAnimalAwakeningToBeRemoved)
                )
                .collect(Collectors.toList());

        Optional<AnimalAwakening> friendAwakeningToRemoveAsFriend =
                animalAwakeningSelector.selectAnimalAwakening(animalsAwakeningCanBeRemoved);

        friendAwakeningToRemoveAsFriend.ifPresent(friendAwakeningToRemove -> {
            removeFriendAwakeningFromAnimalAwakening(
                    currentAnimalAwakening,
                    friendAwakeningToRemove);

            removeFriendAwakeningFromAnimalAwakening(
                    friendAwakeningToRemove,
                    currentAnimalAwakening);
        });
    }

    private void addFriend(
            AnimalAwakening currentAnimalAwakening,
            List<AnimalAwakening> allAnimalsAwakenings)
    {
        List<AnimalAwakening> animalsAwakeningCanBeAdded = allAnimalsAwakenings.stream()
                .filter(possibleAnimalAwakeningToBeAdded ->
                        animalAwakeningCanBeAddedAsFriendOfCurrentAnimalAwakening(
                                currentAnimalAwakening,
                                possibleAnimalAwakeningToBeAdded)
                )
                .collect(Collectors.toList());

        Optional<AnimalAwakening> friendAwakeningToAddAsFriend =
                animalAwakeningSelector.selectAnimalAwakening(animalsAwakeningCanBeAdded);

        friendAwakeningToAddAsFriend.ifPresent(friendAwakeningToRemove -> {
            addFriendAwakeningFromAnimalAwakening(
                    currentAnimalAwakening,
                    friendAwakeningToRemove);

            addFriendAwakeningFromAnimalAwakening(
                    friendAwakeningToRemove,
                    currentAnimalAwakening);

        });

    }

    private boolean animalAwakeningCanBeRemovedAsFriendOfCurrentAnimalAwakening(
            AnimalAwakening currentAnimalAwakening,
            AnimalAwakening possibleAnimalAwakeningToBeRemoved)
    {
        Animal currentAnimal = currentAnimalAwakening.getAnimal();
        Animal possibleAnimalToBeRemove = possibleAnimalAwakeningToBeRemoved.getAnimal();

        if (currentAnimalAwakening.getFriendNew().isPresent()) {
            Animal newFriendAnimal = currentAnimalAwakening.getFriendNew().get();
            if (newFriendAnimal.equals(possibleAnimalToBeRemove)) {
                return false;
            }
        }

        return !possibleAnimalToBeRemove.equals(currentAnimal) &&
                !possibleAnimalAwakeningToBeRemoved.getFriendLost().isPresent() &&
                currentAnimal.getFriends().contains(possibleAnimalToBeRemove);
    }

    private boolean animalAwakeningCanBeAddedAsFriendOfCurrentAnimalAwakening(
            AnimalAwakening currentAnimalAwakening,
            AnimalAwakening possibleAnimalAwakeningToBeAdded)
    {
        Animal currentAnimal = currentAnimalAwakening.getAnimal();
        Animal possibleAnimalToAdd = possibleAnimalAwakeningToBeAdded.getAnimal();

        if (currentAnimalAwakening.getFriendLost().isPresent()) {
            Animal lostFriendAnimal = currentAnimalAwakening.getFriendLost().get();
            if (lostFriendAnimal.equals(possibleAnimalToAdd)) {
                return false;
            }
        }

        return !possibleAnimalToAdd.equals(currentAnimal) &&
                !possibleAnimalAwakeningToBeAdded.getFriendNew().isPresent() &&
                !currentAnimal.getFriends().contains(possibleAnimalToAdd);
    }

    private void removeFriendAwakeningFromAnimalAwakening(
            AnimalAwakening animalAwakening,
            AnimalAwakening friendAwakeningToRemove)
    {
        Animal friendToRemove = friendAwakeningToRemove.getAnimal();
        animalAwakening.setFriendLost(friendToRemove);
    }

    private void addFriendAwakeningFromAnimalAwakening(
            AnimalAwakening animalAwakening,
            AnimalAwakening friendAwakeningToAdd)
    {
        Animal friendToAdd = friendAwakeningToAdd.getAnimal();
        animalAwakening.setFriendNew(friendToAdd);
    }

}