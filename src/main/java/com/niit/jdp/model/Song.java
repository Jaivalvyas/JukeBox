/*
 * Author Name: Jaival
 * Date: 20-09-2022
 * Createdd with IntelliJ IDEA Community Editiion
 */
package com.niit.jdp.model;

import java.util.Objects;

public class Song {
    private int id;
    private String name;
    private String albumArtist;
    private String genre;

    public Song() {
    }

    public Song(int id, String name, String albumArtist, String genre) {
        this.id = id;
        this.name = name;
        this.albumArtist = albumArtist;
        this.genre = genre;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
        Song song = (Song) o;
        return id == song.id && Objects.equals(name, song.name) && Objects.equals(albumArtist, song.albumArtist) && Objects.equals(genre, song.genre);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, albumArtist, genre);
    }

    @Override
    public String toString() {
        return "Song{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", albumArtist='" + albumArtist + '\'' +
                ", genre='" + genre + '\'' +
                '}';
    }
}
