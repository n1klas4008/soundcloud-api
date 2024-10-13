package com.n1klas4008.data.media.download;



public interface FileCallback {
    void onAssembly(int index, IFile file);

    void onFailure(int index, int attempt, String url);
}
