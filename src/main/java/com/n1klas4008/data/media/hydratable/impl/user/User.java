package com.n1klas4008.data.media.hydratable.impl.user;

import com.n1klas4008.data.media.hydratable.Hydratable;
import org.json.JSONObject;



public class User extends Hydratable {
    private final long userId;
    private final String permalink;

    public User(long timestamp, JSONObject object) {
        super(timestamp);
        boolean nested = object.has("data");
        JSONObject data = nested ? object.getJSONObject("data") : object;
        this.userId = data.getLong("id");
        this.permalink = data.getString("permalink");
    }

    public String getPermalink() {
        return permalink;
    }

    public long getUserId() {
        return userId;
    }
}
