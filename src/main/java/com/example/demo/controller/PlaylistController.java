package com.example.demo.controller;

import com.example.demo.domain.Playlist;
import com.example.demo.service.PlaylistService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/")
public class PlaylistController {

    private PlaylistService playlistService;

    public PlaylistController(PlaylistService playlistService) {
        this.playlistService = playlistService;
    }

    @PostMapping
    public ResponseEntity<Void> createPlaylist(@RequestBody Playlist playlist) {
        playlistService.createPlaylist(playlist);
        return ResponseEntity.ok().build();
    }

    @GetMapping
    public ResponseEntity<List<Playlist>> findAllByUserId(@RequestParam(value = "user-id") String userId) {
        return ResponseEntity.ok(playlistService.findAll(userId));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Playlist> findPlaylistById(@PathVariable String id) {
        return ResponseEntity.ok(playlistService.getPlaylist(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> removePlaylist(@PathVariable String id) {
        playlistService.remove(id);
        return ResponseEntity.ok().build();
    }
}
