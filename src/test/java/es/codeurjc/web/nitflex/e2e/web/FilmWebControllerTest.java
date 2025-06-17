package es.codeurjc.web.nitflex.e2e.web;

import java.time.Duration;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
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
        this.driver = new ChromeDriver();
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
    @DisplayName("Esperomos que la página principal de la web se carga correctamente")
	void simpleTest() throws Exception {
        driver.get("http://localhost:"+this.port+"/");
        // Comprobamos que la página se ha cargado correctamente porque podemos ver el título
        wait.until(ExpectedConditions.presenceOfElementLocated(By.className("header")));
    }
    
}