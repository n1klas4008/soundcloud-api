package com.n1klas4008.data;

import com.n1klas4008.ionhttp.IonClient;
import com.n1klas4008.ionhttp.request.IonRequest;
import com.n1klas4008.ionhttp.request.IonResponse;
import com.n1klas4008.logger.Logger;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class VirtualClient {

    private static final String SCRIPT_SRC = "<script(.*)src=\"(.*)\"(.*)script>";
    private static final String SEARCH_STRING = "query:{client_id:\"";
    private static final Pattern SCRIPT_PATTERN = Pattern.compile(SCRIPT_SRC);

    private static String clientID;
    private static long timestamp;

    public static String getID() throws Exception {
        return getID(false);
    }

    public static String getID(boolean force) throws Exception {
        if (clientID != null && (timestamp != 0L && !force)) {
            return clientID;
        }
        String clientID = fetch();
        if (clientID == null) throw new Exception("Unable to fetch Soundcloud client_id");
        VirtualClient.timestamp = System.currentTimeMillis();
        return (VirtualClient.clientID = clientID);
    }

    private static String fetch() throws Exception {
        int attempt = 0;
        do {
            String clientID = mimic();
            if (clientID != null) {
                return clientID;
            }
        } while ((attempt++) < 3);
        return null;
    }

    private static String mimic() throws IOException {
        IonRequest request = IonRequest.on("https://soundcloud.com/")
                .addHeader("Host","soundcloud.com")
                .get();
        IonResponse response = IonClient.getDefault().execute(request);
        String body = new String(response.body(), StandardCharsets.UTF_8);
        Matcher matcher = SCRIPT_PATTERN.matcher(body);
        List<String> list = new ArrayList<>();
        while (matcher.find()) {
            list.add(matcher.group(2));
        }
        for (String element : list) {
            String script = loadScript(element);
            if (script == null) continue;
            int index = script.indexOf(SEARCH_STRING);
            if (index == -1) continue;
            int startIndex = index + SEARCH_STRING.length();
            int endIndex = script.indexOf("\"", startIndex + SEARCH_STRING.length());
            return script.substring(startIndex, endIndex);
        }
        return null;
    }

    private static String loadScript(String source) {
        String body = null;
        try {
            IonRequest request = IonRequest.on(source)
                    .addHeader("Host","soundcloud.com")
                    .get();
            IonResponse response = IonClient.getDefault().execute(request);
            body = new String(response.body(), StandardCharsets.UTF_8);
        } catch (IOException e) {
            Logger.error(e);
        }
        return body;
    }
}