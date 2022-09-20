/*
 * Author Name: Jaival
 * Date: 20-09-2022
 * Createdd with IntelliJ IDEA Community Editiion
 */
package com.niit.jdp.model;

public class Playlist {
    private String playlistName;
    private Songs songs;

    public Playlist() {
    }

    public Playlist(String playlistName, Songs songs) {
        this.playlistName = playlistName;
        this.songs = songs;
    }

    public String getPlaylistName() {
        return playlistName;
    }

    public void setPlaylistName(String playlistName) {
        this.playlistName = playlistName;
    }

    public Songs getSongs() {
        return songs;
    }

    public void setSongs(Songs songs) {
        this.songs = songs;
    }
}
