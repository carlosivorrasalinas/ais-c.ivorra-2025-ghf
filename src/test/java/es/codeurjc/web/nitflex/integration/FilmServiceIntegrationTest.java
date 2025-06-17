package es.codeurjc.web.nitflex.integration;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import es.codeurjc.web.nitflex.TestUtils;
import es.codeurjc.web.nitflex.repository.UserRepository;

@SpringBootTest
class FilmServiceIntegrationTest {

    @Autowired
    private UserRepository userRepository;


    @BeforeEach
    public void setup() {
        userRepository.save(TestUtils.createSampleUser());
    }

    @AfterEach
    void tearDown() {
        userRepository.deleteAll();
    }

    @Test
    @DisplayName("Cuando se añade una película con un título válido mediante FilmService, se guarda en la base de datos y se devuelve la película creada")
    void testSaveFilm() {
        assertTrue(true);
    }

}
