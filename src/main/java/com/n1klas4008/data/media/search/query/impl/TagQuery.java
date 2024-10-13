package com.n1klas4008.data.media.search.query.impl;

import com.n1klas4008.data.SHA256;
import com.n1klas4008.data.media.hydratable.impl.track.Track;
import com.n1klas4008.data.media.search.query.AdvancedQuery;
import org.json.JSONObject;

import java.util.function.Function;



public class TagQuery extends AdvancedQuery {
    private final long timestamp;
    private final String tag;

    public TagQuery(long timestamp, String tag) {
        this.timestamp = timestamp;
        this.tag = tag;
    }

    public TagQuery(String tag) {
        this(System.currentTimeMillis(), tag);
    }

    @Override
    public String getKeyword() {
        return tag;
    }

    @Override
    public String checksum() {
        return SHA256.hash(String.join(getClass().getSimpleName(), tag));
    }

    @Override
    public Function<JSONObject, Track> getTransformer() {
        return object -> new Track(timestamp, object);
    }
}
