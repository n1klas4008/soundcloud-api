package com.n1klas4008.data.media.track;

import org.json.JSONArray;
import org.json.JSONObject;



public class Media {

    private final Transcoding[] transcoding;

    public Media(JSONObject o) {
        JSONArray array = o.getJSONArray("transcodings");
        this.transcoding = new Transcoding[array.length()];
        for (int i = 0; i < array.length(); i++) {
            this.transcoding[i] = new Transcoding(array.getJSONObject(i));
        }
    }

    public Transcoding[] getTranscoding() {
        return transcoding;
    }
}
