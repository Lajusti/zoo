package lajusticia.alejandro.zoo.animal.infrastructure.controller;


import lajusticia.alejandro.zoo.ZooApplication;
import org.junit.jupiter.api.DisplayName;
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

import static lajusticia.alejandro.zoo.utils.TestUtils.getNextAnimal;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(
        classes = { ZooApplication.class },
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT
)
@ExtendWith(SpringExtension.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class AnimalsControllerTest {

    private final String ANIMALS_URL = "http://localhost:%d/animals";

    private final int port;

    public AnimalsControllerTest(@LocalServerPort final int port) {
        this.port = port;
    }

    @Test
    @DisplayName("Test controller AnimalsController get to end point /animals")
    void callController() {
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
    }

}
