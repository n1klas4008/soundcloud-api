package com.n1klas4008.data.media.hydratable.impl.playlist;

import com.n1klas4008.data.media.ObjectCallback;
import com.n1klas4008.data.media.hydratable.HydratableInterface;



public class PlaylistManager implements HydratableInterface<Playlist> {
    private final ObjectCallback<Playlist> callback;

    public PlaylistManager(ObjectCallback<Playlist> callback) {
        this.callback = callback;
    }

    @Override
    public void accept(String link, Playlist hydratable, String... args) {
        callback.ping(link, hydratable, args);
    }
}
