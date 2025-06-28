package es.codeurjc.web.nitflex.unit;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import es.codeurjc.web.nitflex.dto.film.CreateFilmRequest;

public class InvalidYearUnitTest {

    private boolean isValid(CreateFilmRequest film) {
        return film.releaseYear() >= 1895;
    }

    @Test
    void shouldRejectFilmWithInvalidYear() {
        CreateFilmRequest film = new CreateFilmRequest("Título", "Sinopsis", 1700, "PG");
        assertFalse(isValid(film));
    }

    @Test
    void shouldAcceptFilmWithValidYear() {
        CreateFilmRequest film = new CreateFilmRequest("Título", "Sinopsis", 2000, "PG");
        assertTrue(isValid(film));
    }
}
