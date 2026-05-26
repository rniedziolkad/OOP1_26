package pl.umcs.oop.music;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class PlaylistTest {
    @Test
    void newPlaylistIsEmpty() {
        //given
        //when
        Playlist playlist = new Playlist();
        //then
        Assertions.assertTrue(playlist.isEmpty());
    }

    @Test
    void addSongToEmptySizeIs1() {
        //given
        Playlist playlist = new Playlist();
        Song s1 = new Song("artist", "title", 100);
        //when
        playlist.add(s1);
        //then
        Assertions.assertEquals(1, playlist.size());
    }

    @Test
    void checkSongAfterAdding() {
        Playlist playlist = new Playlist();
        Song s2 = new Song("bajo jajo", "hej", 20);

        playlist.add(s2);

        Assertions.assertTrue(playlist.contains(s2));
    }

    @Test
    void checkSongIfEquals() {
        Playlist playlist = new Playlist();
        Song s2 = new Song("bajo jajo", "hej", 20);

        playlist.add(s2);

        Assertions.assertTrue(playlist.contains(new Song("bajo jajo", "hej", 20)));
    }

    @Test
    void checkDuration() {
        Playlist playlist = new Playlist();
        playlist.add(new Song("a", "ab", 50));
        playlist.add(new Song("b", "bc", 30));
        playlist.add(new Song("c", "ca", 80));

        Song test = playlist.atSecond(100);

        Assertions.assertEquals(new Song("c", "ca", 80), test);
    }
}
