package com.example.demo.controller;

import com.example.demo.domain.Track;
import com.example.demo.service.TrackService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/{id}/tracks")
public class TrackController {
    private TrackService trackService;

    public TrackController(TrackService trackService) {
        this.trackService = trackService;
    }

    @PostMapping
    public ResponseEntity<Void> addTrackToPlaylist(@PathVariable String id,@RequestBody Track track) {
        trackService.addTrack(id,track);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping
    public ResponseEntity<Void> removeTrackFromPlaylist(@PathVariable String id,@RequestBody Track track) {
        trackService.delete(id,track);
        return ResponseEntity.ok().build();
    }

}
