package pl.umcs.oop.music;

import java.util.ArrayList;
import java.util.List;

public class Playlist extends ArrayList<Song> {
    public Song atSecond (int sec) {
        int elapsed = 0;
        for(int i = 0; i < this.size(); i++) {
            Song song = this.get(i);
            if (sec >= elapsed || sec < elapsed + song.duration()) {
                return song;
            }
            elapsed += song.duration();
        }
        return null;
    }
}
