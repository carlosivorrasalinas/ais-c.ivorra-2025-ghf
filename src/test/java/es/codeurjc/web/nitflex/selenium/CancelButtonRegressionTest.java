package es.codeurjc.web.nitflex.selenium;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.Duration;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CancelButtonRegressionTest {

    private WebDriver driver;
    private WebDriverWait wait;
    private String baseUrl;

    @BeforeEach
    public void setUp() {
        // Usa la propiedad del sistema "host" si se define, o localhost por defecto
        baseUrl = System.getProperty("host", "http://localhost:8080");
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.manage().window().maximize();
        driver.get(baseUrl + "/films/new");
    }

    @AfterEach
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Test
    public void testCancelButtonGoesBack() {
        // Guardamos la URL actual
        String formUrl = driver.getCurrentUrl();

        // Visitamos otra página antes, para simular que venimos desde ella
        driver.navigate().to(baseUrl + "/");
        String previousUrl = driver.getCurrentUrl();

        // Volvemos al formulario
        driver.navigate().to(formUrl);

        // Esperamos al botón tipo <button> con texto "Cancel"
        WebElement cancelButton = wait.until(driver -> {
            List<WebElement> buttons = driver.findElements(By.tagName("button"));
            return buttons.stream()
                    .filter(b -> "Cancel".equals(b.getText()))
                    .findFirst()
                    .orElse(null);
        });

        cancelButton.click();

        // Esperamos a volver a la URL anterior
        wait.until(ExpectedConditions.urlToBe(previousUrl));

        String currentUrl = driver.getCurrentUrl();
        System.out.println("Redirigido a: " + currentUrl);

        assertTrue(
                currentUrl.equals(previousUrl),
                "La URL debería ser la anterior al formulario"
        );
    }
}
