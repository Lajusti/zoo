package lajusticia.alejandro.zoo.animal.infrastructure.controller;

import lajusticia.alejandro.zoo.animal.application.read.ObtainAllAnimals;
import lajusticia.alejandro.zoo.animal.infrastructure.controller.response.AnimalResponse;
import lajusticia.alejandro.zoo.animal.infrastructure.controller.response.AnimalResponseFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/animals")
public class AnimalsController {

    private final ObtainAllAnimals obtainAllAnimals;

    public AnimalsController(final ObtainAllAnimals obtainAllAnimals) {
        this.obtainAllAnimals = obtainAllAnimals;
    }

    @GetMapping
    public ResponseEntity<List<AnimalResponse>> getAnimals() {
        return new ResponseEntity<>(
                obtainAllAnimals.obtainAllAnimalsInZoo().stream()
                        .map(AnimalResponseFactory::buildResponseOfAnimal)
                        .collect(Collectors.toList()),
                HttpStatus.OK
        );
    }

}
