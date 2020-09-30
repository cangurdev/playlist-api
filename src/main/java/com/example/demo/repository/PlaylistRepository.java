package com.example.demo.repository;

import com.couchbase.client.java.Cluster;
import com.couchbase.client.java.Collection;
import com.couchbase.client.java.kv.GetResult;
import com.couchbase.client.java.query.QueryResult;
import com.example.demo.domain.Playlist;
import com.example.demo.domain.Track;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class PlaylistRepository {

    private final Cluster couchbaseCluster;
    private final Collection playlistCollection;

    public PlaylistRepository(Cluster couchbaseCluster, Collection playlistCollection) {
        this.couchbaseCluster = couchbaseCluster;
        this.playlistCollection = playlistCollection;
    }

    public void insert(Playlist playlist) {
        playlistCollection.insert(playlist.getId(), playlist);
    }

    public void delete(String id) {
        playlistCollection.remove(id);
    }

    public Playlist findById(String id) {
        GetResult getResult = playlistCollection.get(id);
        return getResult.contentAs(Playlist.class);
    }

    public List<Playlist> findAll(String id) {
        String statement = String.format("Select playlist.* from playlist where userId = %s", id);
        QueryResult query = couchbaseCluster.query(statement);
        return query.rowsAs(Playlist.class);
    }

    public void addTrack(String id, Track track) {
        Playlist playlist = findById(id);
        playlist.getTracks().add(track);
        playlist.updateTrackCount(1);
        playlistCollection.replace(playlist.getId(), playlist);
    }

    public void deleteTrack(String id, Track track) {
        Playlist playlist = findById(id);
        playlist.getTracks().remove(track);
        playlist.updateTrackCount(-1);
        playlistCollection.replace(playlist.getId(), playlist);
    }
}
