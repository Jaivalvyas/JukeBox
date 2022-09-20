/*
 * Author Name: Jaival
 * Date: 20-09-2022
 * Createdd with IntelliJ IDEA Community Editiion
 */
package com.niit.jdp.model;

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
}
