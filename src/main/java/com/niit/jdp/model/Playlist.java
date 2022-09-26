/*
 * Author Name: Jaival
 * Date: 20-09-2022
 * Createdd with IntelliJ IDEA Community Editiion
 */
package com.niit.jdp.model;

import java.util.List;
import java.util.Objects;

public class Playlist {
    private String playlistName;

    private int playlistId;
    private List<Song> songList;

    private String songIds;

    public Playlist() {
    }

    public Playlist(int playlistId, String playlistName, String songIds) {
        this.playlistName = playlistName;
        this.playlistId = playlistId;
        this.songIds = songIds;
    }

    public Playlist(int playlistId, String playlistName, List<Song> songList) {
        this.playlistName = playlistName;
        this.playlistId = playlistId;
        this.songList = songList;
    }

    public String getPlaylistName() {
        return playlistName;
    }

    public void setPlaylistName(String playlistName) {
        this.playlistName = playlistName;
    }

    public int getPlaylistId() {
        return playlistId;
    }

    public void setPlaylistId(int playlistId) {
        this.playlistId = playlistId;
    }

    public String getSongIds() {
        return songIds;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Playlist playlist = (Playlist) o;
        return playlistId == playlist.playlistId && Objects.equals(playlistName, playlist.playlistName) && Objects.equals(songList, playlist.songList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(playlistName, playlistId, songList);
    }

    @Override
    public String toString() {
        return "Playlist{" + "playlistName='" + playlistName + '\'' + ", playlistId=" + playlistId + ", songList=" + songList + '}';
    }
}
