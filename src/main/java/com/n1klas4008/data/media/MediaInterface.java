package com.n1klas4008.data.media;

import com.n1klas4008.data.media.hydratable.Hydratable;
import org.json.JSONObject;



public interface MediaInterface<T extends Hydratable> {
    T convert(long timestamp, JSONObject object);
}
