/*
 * Author Name: Jaival
 * Date: 20-09-2022
 * Createdd with IntelliJ IDEA Community Editiion
 */
package com.niit.jdp.model;

import java.util.Objects;

public class Songs {
    private int songId;
    private String songName;
    private String albumArtist;
    private String genre;

    public Songs() {
    }

    public Songs(int songId, String songName, String albumArtist, String genre) {
        this.songId = songId;
        this.songName = songName;
        this.albumArtist = albumArtist;
        this.genre = genre;
    }

    public int getSongId() {
        return songId;
    }

    public void setSongId(int songId) {
        this.songId = songId;
    }

    public String getSongName() {
        return songName;
    }

    public void setSongName(String songName) {
        this.songName = songName;
    }

    public String getAlbumArtist() {
        return albumArtist;
    }

    public void setAlbumArtist(String albumArtist) {
        this.albumArtist = albumArtist;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Songs songs = (Songs) o;
        return songId == songs.songId && Objects.equals(songName, songs.songName) && Objects.equals(albumArtist, songs.albumArtist) && Objects.equals(genre, songs.genre);
    }

    @Override
    public int hashCode() {
        return Objects.hash(songId, songName, albumArtist, genre);
    }

    @Override
    public String toString() {
        return "Songs{" +
                "songId=" + songId +
                ", songName='" + songName + '\'' +
                ", albumArtist='" + albumArtist + '\'' +
                ", genre='" + genre + '\'' +
                '}';
    }
}
