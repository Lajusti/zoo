package lajusticia.alejandro.zoo.animal.application.update;

import lajusticia.alejandro.zoo.animal.domain.entity.Animal;
import lajusticia.alejandro.zoo.animal.domain.entity.AwakeningResult;
import lajusticia.alejandro.zoo.animal.application.event.NewDayProcessed;
import lajusticia.alejandro.zoo.animal.domain.repository.AnimalRepository;
import lajusticia.alejandro.zoo.animal.domain.service.AwakeningService;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NewDay {

    private final AnimalRepository animalRepository;
    private final AwakeningService awakeningService;
    private final ApplicationEventPublisher applicationEventPublisher;

    public NewDay(
            final AnimalRepository animalRepository,
            final AwakeningService awakeningService,
            final ApplicationEventPublisher applicationEventPublisher)
    {
        this.animalRepository = animalRepository;
        this.awakeningService = awakeningService;
        this.applicationEventPublisher = applicationEventPublisher;
    }

    public synchronized List<Animal> awakeAllAnimalsInZoo() {
        List<Animal> allAnimals = animalRepository.getAll();
        List<AwakeningResult> awakeningsResult = awakeningService.awakeAnimals(allAnimals);

        applicationEventPublisher.publishEvent(new NewDayProcessed(this, awakeningsResult));

        animalRepository.saveAll(allAnimals);

        return allAnimals;
    }

}
