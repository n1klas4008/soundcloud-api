package com.n1klas4008.data.media.search.query;

import org.json.JSONObject;

import java.util.function.Function;
import java.util.function.Predicate;



public interface Query<T> {
    String getKeyword();

    String checksum();

    Predicate<T> filter();

    Function<JSONObject, T> getTransformer();
}
