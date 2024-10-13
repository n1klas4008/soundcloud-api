package com.n1klas4008.data.media.hydratable;

public class Hydratable {
    protected final long loadReferenceTimestamp;
    public Hydratable(long loadReferenceTimestamp){
        this.loadReferenceTimestamp=loadReferenceTimestamp;
    }

    public long getLoadReferenceTimestamp() {
        return loadReferenceTimestamp;
    }

    public static void snooze(long ms) {
        try {
            Thread.sleep(ms);
        } catch (InterruptedException ignored) {

        }
    }
}
