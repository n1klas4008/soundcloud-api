package com.n1klas4008.data.media.track;

import org.json.JSONObject;



public class User {

    private final String permalink, username;

    public User(JSONObject o) {
        this.permalink = !o.isNull("permalink") ? o.getString("permalink") : "";
        this.username = !o.isNull("username") ? o.getString("username") : "";
    }

    public String getUsername() {
        return username;
    }

    public String getPermalink() {
        return permalink;
    }
}
