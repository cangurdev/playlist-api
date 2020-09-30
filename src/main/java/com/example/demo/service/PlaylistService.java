package com.example.demo.service;

import com.example.demo.domain.Playlist;
import com.example.demo.repository.PlaylistRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PlaylistService {
    private final PlaylistRepository playlistRepository;

    public PlaylistService(PlaylistRepository playlistRepository) {
        this.playlistRepository = playlistRepository;
    }

    public void createPlaylist(Playlist playlist) {
        playlistRepository.insert(playlist);
    }

    public Playlist getPlaylist(String id) {
        return playlistRepository.findById(id);
    }

    public List<Playlist> findAll(String id) {
        return playlistRepository.findAll(id);
    }

    public void remove(String id) {
        playlistRepository.delete(id);
    }

}
