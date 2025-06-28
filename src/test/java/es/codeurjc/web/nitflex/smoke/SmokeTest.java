package es.codeurjc.web.nitflex.smoke;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

import org.junit.jupiter.api.Test;

public class SmokeTest {

    @Test
    public void testWelcomeMessageIsDisplayed() throws IOException {
        String host = System.getProperty("host");
        if (host == null || host.isEmpty()) {
            throw new RuntimeException("Debe indicarse el host con -Dhost=<URL>");
        }

        URL url = new URL(host);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");

        Scanner scanner = new Scanner(connection.getInputStream());
        StringBuilder response = new StringBuilder();
        while (scanner.hasNext()) {
            response.append(scanner.nextLine());
        }
        scanner.close();

        assertTrue(response.toString().toLowerCase().contains("nitflex"),
                "La página no contiene el texto esperado");
    }
}
