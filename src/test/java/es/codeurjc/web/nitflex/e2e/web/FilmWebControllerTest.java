package es.codeurjc.web.nitflex.e2e.web;

import java.time.Duration;
import java.util.UUID;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;

import es.codeurjc.web.nitflex.Application;
import es.codeurjc.web.nitflex.TestUtils;
import es.codeurjc.web.nitflex.repository.UserRepository;

@SpringBootTest(classes = Application.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class FilmWebControllerTest {

    @LocalServerPort
    protected int port;

    protected WebDriver driver;
    protected WebDriverWait wait;

    @Autowired
    private UserRepository userRepository;

    @BeforeEach
    void setupTest() {
        userRepository.save(TestUtils.createSampleUser());

        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless=new");
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("--disable-gpu");
        options.addArguments("--remote-allow-origins=*");
        options.addArguments("--user-data-dir=/tmp/chrome-data-" + UUID.randomUUID());

        this.driver = new ChromeDriver(options);
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(5));
    }

    @AfterEach
    void teardown() {
        if (this.driver != null) {
            this.driver.quit();
        }
        userRepository.deleteAll();
    }

    @Test
    @DisplayName("Esperamos que la página principal de la web se cargue correctamente")
    void simpleTest() throws Exception {
        driver.get("http://localhost:" + this.port + "/");
        wait.until(ExpectedConditions.presenceOfElementLocated(By.className("header")));
    }
}
