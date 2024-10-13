package com.n1klas4008.data.media;



public interface ObjectCallback<T> {
    void ping(String link, T t, String... args);
}
