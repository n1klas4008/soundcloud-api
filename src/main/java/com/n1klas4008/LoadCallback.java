package com.n1klas4008;

public interface LoadCallback {

    void onLoadFailure(String link, Exception e, String... args);
}
