package lajusticia.alejandro.zoo.animal.infrastructure.controller;

import lajusticia.alejandro.zoo.ZooApplication;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Arrays;
import java.util.List;

import static lajusticia.alejandro.zoo.utils.TestUtils.getNextAnimal;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(
        classes = { ZooApplication.class },
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT
)
@ExtendWith(SpringExtension.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class NewDayControllerTest {

    private final String ANIMALS_URL = "http://localhost:%d/newday";

    private final int port;

    public NewDayControllerTest(@LocalServerPort final int port) {
        this.port = port;
    }

    @Test
    void doTest() {

        // First call to newDay

        ResponseEntity response = new TestRestTemplate()
                .exchange(
                        String.format(ANIMALS_URL, port),
                        HttpMethod.GET,
                        null,
                        String.class
                );

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());

        String body = (String) response.getBody();
        String dogKillian = getNextAnimal(body);
        body = body.substring(body.indexOf('}') + 1);
        String parrotParrotOne = getNextAnimal(body);
        body = body.substring(body.indexOf('}') + 1);
        String chickenChickenOne = getNextAnimal(body);
        body = body.substring(body.indexOf('}') + 1);
        String dogRocky = getNextAnimal(body);
        body = body.substring(body.indexOf('}') + 1);
        String parrotParrotTwo = getNextAnimal(body);
        body = body.substring(body.indexOf('}') + 1);
        String dogPeter = getNextAnimal(body);
        body = body.substring(body.indexOf('}') + 1);
        String chickenRocky = getNextAnimal(body);

        assertTrue(dogKillian.contains("\"type\":\"Dog\",\"name\":\"Killian\",\"favoriteFood\":\"Meat\",\"friends\":["));
        assertTrue(dogKillian.contains("],\"dogType\":\"HUNTING_DOG\""));

        assertTrue(parrotParrotOne.contains("\"type\":\"Parrot\",\"name\":\"Parrot one\",\"favoriteFood\":\"Grain\",\"friends\":["));
        assertTrue(parrotParrotOne.contains("],\"lengthOfWings\":0.25,\"speak\":false"));

        assertTrue(chickenChickenOne.contains("\"type\":\"Chicken\",\"name\":\"Chicken one\",\"favoriteFood\":\"Corn\",\"friends\":["));
        assertTrue(chickenChickenOne.contains("],\"broiler\":true"));

        assertTrue(dogRocky.contains("\"type\":\"Dog\",\"name\":\"Rocky\",\"favoriteFood\":\"Fresh meat\",\"friends\":["));
        assertTrue(dogRocky.contains("],\"dogType\":\"WORKING_DOG\""));

        assertTrue(parrotParrotTwo.contains("\"type\":\"Parrot\",\"name\":\"Parrot two\",\"favoriteFood\":\"Corn\",\"friends\":["));
        assertTrue(parrotParrotTwo.contains("],\"lengthOfWings\":0.5,\"speak\":true"));

        assertTrue(dogPeter.contains("\"type\":\"Dog\",\"name\":\"Peter\",\"favoriteFood\":\"Pedigree\",\"friends\":["));
        assertTrue(dogPeter.contains("],\"dogType\":\"SPORT_DOG\""));

        assertTrue(chickenRocky.contains("\"type\":\"Chicken\",\"name\":\"Rocky\",\"favoriteFood\":\"Corn\",\"friends\":["));
        assertTrue(chickenRocky.contains("],\"broiler\":false"));

        assertEquals(1, emptyFriends(
                Arrays.asList(
                        dogKillian,
                        parrotParrotOne,
                        chickenChickenOne,
                        dogRocky,
                        parrotParrotTwo,
                        dogPeter,
                        chickenRocky
                )
        ));

        // Second call to newDay validate the data of the animals is the same but all has a different friend

        response = new TestRestTemplate()
                .exchange(
                        String.format(ANIMALS_URL, port),
                        HttpMethod.GET,
                        null,
                        String.class
                );

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());

        body = (String) response.getBody();
        dogKillian = getNextAnimal(body);
        body = body.substring(body.indexOf('}') + 1);
        parrotParrotOne = getNextAnimal(body);
        body = body.substring(body.indexOf('}') + 1);
        chickenChickenOne = getNextAnimal(body);
        body = body.substring(body.indexOf('}') + 1);
        dogRocky = getNextAnimal(body);
        body = body.substring(body.indexOf('}') + 1);
        parrotParrotTwo = getNextAnimal(body);
        body = body.substring(body.indexOf('}') + 1);
        dogPeter = getNextAnimal(body);
        body = body.substring(body.indexOf('}') + 1);
        chickenRocky = getNextAnimal(body);

        assertTrue(dogKillian.contains("\"type\":\"Dog\",\"name\":\"Killian\",\"favoriteFood\":\"Meat\",\"friends\":["));
        assertTrue(dogKillian.contains("],\"dogType\":\"HUNTING_DOG\""));

        assertTrue(parrotParrotOne.contains("\"type\":\"Parrot\",\"name\":\"Parrot one\",\"favoriteFood\":\"Grain\",\"friends\":["));
        assertTrue(parrotParrotOne.contains("],\"lengthOfWings\":0.25,\"speak\":false"));

        assertTrue(chickenChickenOne.contains("\"type\":\"Chicken\",\"name\":\"Chicken one\",\"favoriteFood\":\"Corn\",\"friends\":["));
        assertTrue(chickenChickenOne.contains("],\"broiler\":true"));

        assertTrue(dogRocky.contains("\"type\":\"Dog\",\"name\":\"Rocky\",\"favoriteFood\":\"Fresh meat\",\"friends\":["));
        assertTrue(dogRocky.contains("],\"dogType\":\"WORKING_DOG\""));

        assertTrue(parrotParrotTwo.contains("\"type\":\"Parrot\",\"name\":\"Parrot two\",\"favoriteFood\":\"Corn\",\"friends\":["));
        assertTrue(parrotParrotTwo.contains("],\"lengthOfWings\":0.5,\"speak\":true"));

        assertTrue(dogPeter.contains("\"type\":\"Dog\",\"name\":\"Peter\",\"favoriteFood\":\"Pedigree\",\"friends\":["));
        assertTrue(dogPeter.contains("],\"dogType\":\"SPORT_DOG\""));

        assertTrue(chickenRocky.contains("\"type\":\"Chicken\",\"name\":\"Rocky\",\"favoriteFood\":\"Corn\",\"friends\":["));
        assertTrue(chickenRocky.contains("],\"broiler\":false"));

        assertEquals(1, emptyFriends(
                Arrays.asList(
                        dogKillian,
                        parrotParrotOne,
                        chickenChickenOne,
                        dogRocky,
                        parrotParrotTwo,
                        dogPeter,
                        chickenRocky
                )
        ));
    }

    private int emptyFriends(List<String> animalsAsString) {
        int emptyFriends = 0;
        for (String animalAsString : animalsAsString) {
            if (animalAsString.contains("friends\":[]")) {
                emptyFriends++;
            }
        }
        return emptyFriends;
    }

    private String getFriend(String animal) {
        return animal.substring(animal.indexOf('[') + 1, animal.indexOf(']'));
    }

}
