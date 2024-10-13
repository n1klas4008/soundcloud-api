package com.n1klas4008.data.media.download;

import com.n1klas4008.data.media.hydratable.impl.track.Track;



public interface DownloadCallback {
    void onCompletion(Track track, byte[] b);

    void onFailure(Track track, int fragment);

    void onTrack(Track track, byte[] b);
}
