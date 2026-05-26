package pl.umcs.oop.music;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import pl.umcs.oop.database.DatabaseConnection;

import java.util.Optional;

public class SongTest {

    @Test
    void checkReadCorrectIndex() {
        DatabaseConnection.connect("songs.db");

        Optional<Song> s1 = Song.Persistence.read(1);

        Assertions.assertTrue(s1.isPresent());
        Assertions.assertEquals(new Song("The Beatles", "Hey Jude", 431), s1.get());
    }
}
