package es.codeurjc.web.nitflex;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import es.codeurjc.web.nitflex.dto.film.CreateFilmRequest;
import es.codeurjc.web.nitflex.dto.film.FilmDTO;
import es.codeurjc.web.nitflex.model.User;
import es.codeurjc.web.nitflex.repository.UserRepository;
import es.codeurjc.web.nitflex.service.FilmService;
import es.codeurjc.web.nitflex.utils.ImageUtils;
import jakarta.annotation.PostConstruct;

@Component
public class DatabaseInitializer {

	@Autowired
	private FilmService filmService;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private ImageUtils imageUtils;

	@PostConstruct
	public void init() throws IOException {

		if(!isRunningTest()) {
			User michel = new User("Michel","michel.maes@urjc.es");
			userRepository.save(michel);

			saveFilmWithURLImage(new CreateFilmRequest("Dune", "En un lejano futuro, la galaxia conocida es gobernada mediante un sistema feudal de casas nobles bajo el mandato del Emperador.", 2021, "+12"),
			"https://www.themoviedb.org/t/p/w220_and_h330_face/m6XWQpT0biTpe5wBGWd60RXmtEX.jpg"
			);

			saveFilmWithURLImage(new CreateFilmRequest("Interstellar", "Narra las aventuras de un grupo de exploradores que hacen uso de un agujero de gusano recientemente descubierto para superar las limitaciones de los viajes espaciales tripulados y vencer las inmensas distancias que tiene un viaje interestelar.", 2014,"+7"),
			"https://www.themoviedb.org/t/p/w220_and_h330_face/nrSaXF39nDfAAeLKksRCyvSzI2a.jpg");

			saveFilmWithURLImage(
				new CreateFilmRequest("Django", "Dos años antes de estallar la Guerra Civil (1861-1865), Schultz, un cazarrecompensas alemán que le sigue la pista a unos asesinos, le promete al esclavo Django dejarlo en libertad si le ayuda a atraparlos.", 2013, "+18"),
			"https://www.themoviedb.org/t/p/w220_and_h330_face/naaYX64yMGzEFsATOFQDaxxQWbJ.jpg"
			);
		}
	}

	private FilmDTO saveFilmWithURLImage(CreateFilmRequest film, String remotePath) throws IOException {
		return filmService.save(film, imageUtils.remoteImageToBlob(remotePath));
	}

	private boolean isRunningTest() {
		try {
			Class.forName("org.junit.jupiter.api.Test");
		} catch (ClassNotFoundException e) {
			return false;
		}
		return true;
	}

}
