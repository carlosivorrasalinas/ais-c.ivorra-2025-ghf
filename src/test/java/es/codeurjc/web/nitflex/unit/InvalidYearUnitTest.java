package es.codeurjc.web.nitflex.unit;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.ui.ConcurrentModel;
import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartFile;

import es.codeurjc.web.nitflex.controller.web.FilmWebController;
import es.codeurjc.web.nitflex.dto.film.CreateFilmRequest;
import es.codeurjc.web.nitflex.service.FilmService;

@DisplayName("Test unitario puro: creación fallida por año inválido")
public class CreateFilmInvalidYearUnitTest {

    @Test
    @DisplayName("No se debe crear la película si el año es anterior a 1895 y debe quedarse en filmForm")
    void shouldNotCreateFilmIfYearIsInvalid() throws Exception {
        // Arrange
        CreateFilmRequest film = new CreateFilmRequest();
        film.setTitle("Película antigua");
        film.setYear(1800); // Año inválido

        MultipartFile mockImage = mock(MultipartFile.class);
        FilmService mockService = mock(FilmService.class); // Servicio simulado

        FilmWebController controller = new FilmWebController(mockService);
        Model model = new ConcurrentModel();

        // Act
        String resultView = controller.newFilmProcess(film, mockImage, model);

        // Assert
        assertEquals("filmForm", resultView); // No redirige
        verify(mockService, never()).save(any(CreateFilmRequest.class), any(MultipartFile.class)); // No llama a save
    }
}
