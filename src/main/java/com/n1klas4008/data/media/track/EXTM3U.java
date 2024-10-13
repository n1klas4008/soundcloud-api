package com.n1klas4008.data.media.track;

import com.n1klas4008.data.media.MediaLoader;
import com.n1klas4008.ionhttp.request.IonResponse;

import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;


public class EXTM3U {

    private final Map<String, String> map = new HashMap<>();
    private final List<String> list = new LinkedList<>();

    public EXTM3U(String target) throws Exception {
        MediaLoader loader = new MediaLoader(target);
        IonResponse response = loader.call();
        String plain = new String(response.body(), StandardCharsets.UTF_8);
        String[] lines = plain.split("\n");
        for (String line : lines) {
            if (line.startsWith("#")) parse(line);
            else {
                list.add(line);
            }
        }
    }

    private void parse(String line) {
        String[] data = line.substring(1).split(":");
        if (data.length != 2 || data[0].equalsIgnoreCase("EXTINF")) return;
        map.put(data[0], data[1]);
    }

    public Map<String, String> getEXTM3UTags() {
        return map;
    }

    public List<String> getFragmentList() {
        return list;
    }
}
