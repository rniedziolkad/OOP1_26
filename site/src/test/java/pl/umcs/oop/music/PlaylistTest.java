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
}
