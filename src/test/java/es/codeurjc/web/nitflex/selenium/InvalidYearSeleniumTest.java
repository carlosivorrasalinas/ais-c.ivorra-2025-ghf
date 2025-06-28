package es.codeurjc.web.nitflex.selenium;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.Duration;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class InvalidYearSeleniumTest {

    private WebDriver driver;
    private WebDriverWait wait;
    private String baseUrl;

    @BeforeEach
    public void setUp() {
        baseUrl = System.getProperty("host", "http://localhost:8080");
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    @Test
    public void shouldShowErrorWhenYearIsInvalid() {
        driver.get(baseUrl + "/films/new");

        driver.findElement(By.name("title")).sendKeys("Pelicula de prueba");
        driver.findElement(By.name("releaseYear")).sendKeys("1700");
        driver.findElement(By.cssSelector("form.ui.form")).submit();

        WebElement errorBox = wait.until(
                ExpectedConditions.visibilityOfElementLocated(By.className("error"))
        );

        assertTrue(errorBox.getText().contains("1895"));
    }

    @AfterEach
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
