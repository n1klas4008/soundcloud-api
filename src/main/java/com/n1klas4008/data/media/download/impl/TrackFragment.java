package com.n1klas4008.data.media.download.impl;

import com.n1klas4008.data.media.MediaLoader;
import com.n1klas4008.data.media.download.FileCallback;
import com.n1klas4008.data.media.download.IFile;
import com.n1klas4008.ionhttp.request.IonResponse;
import com.n1klas4008.logger.Logger;


public class TrackFragment implements Runnable, IFile {

    private final FileCallback callback;
    private final String url;
    private final int index;
    private int failures;
    private byte[] b;

    public TrackFragment(FileCallback callback, int index, String url) {
        this.callback = callback;
        this.index = index;
        this.url = url;
    }

    public byte[] getBytes() {
        return b;
    }

    @Override
    public void run() {
        try {
            MediaLoader loader = new MediaLoader(url);
            IonResponse response = loader.call();
            this.b = response.body();
            callback.onAssembly(index, this);
        } catch (Exception e) {
            Logger.error(e);
            callback.onFailure(index, failures++, url);
        }
    }
}
