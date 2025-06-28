package es.codeurjc.web.nitflex.unit;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.List;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.ui.Model;

import es.codeurjc.web.nitflex.controller.web.FilmWebController;

@WebMvcTest(FilmWebController.class)
@DisplayName("Validación de año incorrecto en películas")
public class InvalidYearUnitTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    @DisplayName("No se crea película si el año es anterior a 1895 y se muestra error")
    void shouldShowErrorIfYearIsInvalid() throws Exception {
        mockMvc.perform(post("/films/new")
                        .param("title", "Película antigua")
                        .param("releaseYear", "1800"))
                .andExpect(status().isOk())
                .andExpect(view().name("filmForm"))
                .andExpect(model().attribute("errors", Matchers.hasItem("El año debe ser 1895 o posterior.")));
    }
}
