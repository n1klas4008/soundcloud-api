package com.n1klas4008.data.media.hydratable;



public interface HydratableInterface<T extends Hydratable> {
    void accept(String link, T hydratable, String... args);
}
