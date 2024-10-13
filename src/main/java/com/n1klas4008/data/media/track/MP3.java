package com.n1klas4008.data.media.track;

import com.n1klas4008.data.VirtualClient;
import com.n1klas4008.data.media.MediaLoader;
import com.n1klas4008.data.media.download.DownloadCallback;
import com.n1klas4008.data.media.download.TrackFile;
import com.n1klas4008.data.media.hydratable.impl.track.Track;
import com.n1klas4008.ionhttp.request.IonResponse;
import com.n1klas4008.logger.Logger;
import org.json.JSONObject;

import java.nio.charset.StandardCharsets;


public class MP3 {
    private final String authorization;
    private final Track track;
    private EXTM3U extm3U;

    public static MP3 load(Track track, String authorization, Transcoding... transcodings) {
        try {
            return new MP3(track, authorization, transcodings);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public MP3(Track track, String authorization, Transcoding... transcodings) throws Exception {
        this.track = track;
        this.authorization = authorization;
        for (Transcoding transcoding : transcodings) {
            if (transcoding.getProtocol().equalsIgnoreCase("hls")) {
                String client = String.join("=", "client_id", VirtualClient.getID());
                String auth = String.join("=", "track_authorization", authorization);
                String parameters = String.join("&", client, auth);
                String resource = String.join(transcoding.getUrl().contains("?") ? "&" : "?", transcoding.getUrl(), parameters);
                Logger.debug("stream for track {} at resource {}", track.getId(), resource);
                MediaLoader loader = new MediaLoader(resource);
                IonResponse response = loader.call();
                String target = new JSONObject(new String(response.body(), StandardCharsets.UTF_8)).getString("url");
                extm3U = new EXTM3U(target);
                break;
            }
        }

    }

    public Track getTrack() {
        return track;
    }

    public EXTM3U getEXTM3U() {
        return extm3U;
    }

    public void download(DownloadCallback callback) {
        TrackFile.get(callback, this);
    }

}
