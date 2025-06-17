package es.codeurjc.web.nitflex;

import java.util.ArrayList;
import java.util.List;

import es.codeurjc.web.nitflex.model.Film;
import es.codeurjc.web.nitflex.model.User;

public class TestUtils {

    public static List<User> createSampleUsers() {
        List<User> users = new ArrayList<>();
        users.add(new User("FAKE USER1", "fakeUser1@gmail.com"));
        users.add(new User("FAKE USER2", "fakeUser2@gmail.com"));
        users.add(new User("FAKE USER3", "fakeUser3@gmail.com"));
        return users;
    }

    public static User createSampleUser() {
        return new User("FAKE USERX", "fakeUserX@gmail.com");
    }

    public static Film createSampleFilm() {
        return new Film("FAKE FILM", "FAKE DESCRIPTION", 1900, "+12");
    }

}
