package lajusticia.alejandro.zoo.utils;

public class TestUtils {

    public static String getNextAnimal(String body) {
        return body.substring(body.indexOf('{'), body.indexOf('}'));
    }

    private TestUtils() {

    }

}
