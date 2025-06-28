package es.codeurjc.web.nitflex.unit;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

import org.junit.Test;
import org.junit.jupiter.api.Test;
import org.springframework.ui.Model;
import org.springframework.ui.ConcurrentModel;
import org.springframework.web.multipart.MultipartFile;

import es.codeurjc.web.nitflex.controller.web.FilmWebController;
import es.codeurjc.web.nitflex.dto.film.CreateFilmRequest;
import es.codeurjc.web.nitflex.dto.film.FilmDTO;
import es.codeurjc.web.nitflex.service.FilmService;

public class InvalidYearUnitTest {

    static class FakeFilmService extends FilmService {

        public boolean saveCalled = false;

        public FakeFilmService() {
            super(null, null, null, null); // Constructor real con 4 args
        }

        @Override
        public FilmDTO save(CreateFilmRequest request, MultipartFile image) {
            saveCalled = true;
            return new FilmDTO(); // Dummy object
        }
    }

    @Test
    void shouldNotCallSaveWhenYearIsInvalid() throws Exception {
        CreateFilmRequest film = new CreateFilmRequest("Test", "Desc", 1700, "PG");
        MultipartFile image = null;
        Model model = new ConcurrentModel();

        FakeFilmService service = new FakeFilmService();
        FilmWebController controller = new FilmWebController(service);

        String view = controller.newFilmProcess(film, image, model);

        assertEquals("filmForm", view);
        assertFalse(service.saveCalled);
    }
}