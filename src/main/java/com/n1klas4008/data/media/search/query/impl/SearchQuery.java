package com.n1klas4008.data.media.search.query.impl;

import com.n1klas4008.data.SHA256;
import com.n1klas4008.data.media.hydratable.impl.track.Track;
import com.n1klas4008.data.media.search.query.AdvancedQuery;
import org.json.JSONObject;

import java.util.function.Function;



public class SearchQuery extends AdvancedQuery {
    private final long timestamp;
    private final String keyword;

    public SearchQuery(long timestamp, String keyword) {
        this.timestamp = timestamp;
        this.keyword = keyword;
    }

    public SearchQuery(String keyword) {
        this(System.currentTimeMillis(), keyword);
    }

    @Override
    public String getKeyword() {
        return keyword;
    }

    @Override
    public String checksum() {
        return SHA256.hash(String.join(getClass().getSimpleName(), keyword));
    }

    @Override
    public Function<JSONObject, Track> getTransformer() {
        return object -> new Track(timestamp, object);
    }
}
