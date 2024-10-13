package com.n1klas4008.data.media.search.query.impl;

import com.n1klas4008.data.SHA256;
import com.n1klas4008.data.media.hydratable.impl.track.Track;
import com.n1klas4008.data.media.search.query.Query;
import org.json.JSONObject;

import java.util.function.Function;
import java.util.function.Predicate;



public class TrackQuery implements Query<Track> {
    private final long timestamp;
    private final long playlistId;
    private final long trackId;
    private final String secret;

    public TrackQuery(long timestamp, long trackId, long playlistId, String secret) {
        this.playlistId = playlistId;
        this.timestamp = timestamp;
        this.trackId = trackId;
        this.secret = secret;
    }

    public TrackQuery(long trackId, long playlistId, String secret) {
        this(System.currentTimeMillis(), trackId, playlistId, secret);
    }

    public TrackQuery(long trackId) {
        this(trackId, 0L, null);
    }

    public long getPlaylistId() {
        return playlistId;
    }

    public String getSecret() {
        return secret;
    }

    @Override
    public String getKeyword() {
        return String.valueOf(trackId);
    }

    @Override
    public String checksum() {
        return SHA256.hash(String.join(getClass().getSimpleName(), String.valueOf(trackId)));
    }

    @Override
    public Predicate<Track> filter() {
        return track -> true;
    }

    @Override
    public Function<JSONObject, Track> getTransformer() {
        return object -> new Track(timestamp, object);
    }
}
