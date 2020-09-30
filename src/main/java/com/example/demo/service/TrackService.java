package com.example.demo.service;

import com.example.demo.domain.Playlist;
import com.example.demo.domain.Track;
import com.example.demo.repository.PlaylistRepository;
import org.springframework.stereotype.Service;

@Service
public class TrackService {
    private PlaylistRepository playlistRepository;

    public TrackService(PlaylistRepository playlistRepository) {
        this.playlistRepository = playlistRepository;
    }

    public void addTrack(String id, Track track) {
        playlistRepository.addTrack(id,track);
    }

    public void delete(String id, Track track) {
        playlistRepository.deleteTrack(id,track);
    }
}
