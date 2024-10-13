package com.n1klas4008.data.media.hydratable.impl.track;

import com.n1klas4008.data.media.download.FileManager;
import com.n1klas4008.data.media.hydratable.Hydratable;
import com.n1klas4008.data.media.track.MP3;
import com.n1klas4008.data.media.track.Media;
import com.n1klas4008.data.media.track.Tags;
import com.n1klas4008.data.media.track.User;
import com.n1klas4008.logger.Logger;
import org.json.JSONObject;

import java.io.File;
import java.time.Instant;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;



public class Track extends Hydratable {
    private static final ExecutorService EXECUTOR_SERVICE = Executors.newSingleThreadExecutor();
    private Media media;
    private User user;
    private Tags tags;

    private String description, title, genre, link, permalink, artwork, authorization, uri, waveform, secretToken;
    private int likeCount, commentCount, playbackCount;
    private final long id;
    private long duration, createdAt, fullDuration;

    public Track(long timestamp, long t) {
        super(timestamp);
        this.id = t;
    }

    public Track(long timestamp, JSONObject o) {
        super(timestamp);
        this.secretToken = o.isNull("secret_token") ? null : o.getString("secret_token");
        this.waveform = o.isNull("waveform_url") ? null : o.getString("waveform_url");
        this.authorization = o.isNull("track_authorization") ? null : o.getString("track_authorization");
        this.media = new Media(o.getJSONObject("media"));
        this.user = new User(o.getJSONObject("user"));
        this.description = !o.isNull("description") ? o.getString("description") : "";
        this.title = o.getString("title");
        this.genre = !o.isNull("genre") ? o.getString("genre") : "";
        this.permalink = !o.isNull("permalink") ? o.getString("permalink") : "";
        this.uri = !o.isNull("uri") ? o.getString("uri") : "";
        this.artwork = !o.isNull("artwork_url") ? o.getString("artwork_url") : null;
        this.link = o.getString("permalink_url");
        this.tags = new Tags(genre, o.getString("tag_list"));
        this.likeCount = !o.isNull("likes_count") ? o.getInt("likes_count") : 0;
        this.playbackCount = !o.isNull("playback_count") ? o.getInt("playback_count") : 0;
        this.id = !o.isNull("id") ? o.getLong("id") : 0;
        this.duration = !o.isNull("duration") ? o.getLong("duration") : 0;
        this.fullDuration = !o.isNull("full_duration") ? o.getLong("full_duration") : 0;
        this.commentCount = !o.isNull("comment_count") ? o.getInt("comment_count") : 0;
        this.createdAt = Instant.parse(!o.isNull("created_at") ? o.getString("created_at") : String.valueOf(System.currentTimeMillis())).toEpochMilli();
        Logger.debug("loaded metadata for track {}", id);
    }

    public CompletableFuture<MP3> retrieveMP3() {
        return CompletableFuture.supplyAsync(() -> MP3.load(this, authorization, media.getTranscoding()), EXECUTOR_SERVICE);
    }

    public MP3 getMP3() {
        return MP3.load(this, authorization, media.getTranscoding());
    }

    public String getSecretToken() {
        return secretToken;
    }

    public String getWaveformURL() {
        return waveform;
    }

    public String getAuthorization() {
        return authorization;
    }

    public String getUri() {
        return uri;
    }

    public boolean isCached() {
        return FileManager.isCached(this);
    }

    public File getFile() {
        return FileManager.getFile(this);
    }

    public long getCreatedAt() {
        return createdAt;
    }

    public long getDuration() {
        return duration;
    }

    public long getFullDuration() {
        return fullDuration;
    }

    public boolean isPro() {
        return duration != fullDuration;
    }


    public int getPlaybackCount() {
        return playbackCount;
    }

    public String getArtwork() {
        return artwork;
    }

    public long getId() {
        return id;
    }

    public String getPermalink() {
        return permalink;
    }

    public Media getMedia() {
        return media;
    }

    public User getUser() {
        return user;
    }

    public Tags getTags() {
        return tags;
    }

    public String getDescription() {
        return description;
    }

    public String getTitle() {
        return title;
    }

    public String getGenre() {
        return genre;
    }

    public String getLink() {
        return link;
    }

    public int getLikeCount() {
        return likeCount;
    }

    public int getCommentCount() {
        return commentCount;
    }

    public static ExecutorService getExecutorService() {
        return EXECUTOR_SERVICE;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Track)) return false;
        Track track = (Track) o;
        return track.getId() == id;
    }

    @Override
    public int hashCode() {
        return Long.hashCode(id);
    }
}
