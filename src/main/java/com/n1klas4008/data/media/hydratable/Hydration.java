package com.n1klas4008.data.media.hydratable;

import com.n1klas4008.exception.HydrationException;
import com.n1klas4008.ionhttp.request.IonResponse;
import org.json.JSONArray;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class Hydration {
    private static final String SCRIPT_SRC = "<script>window\\.__sc_hydration = (.*);</script>";
    private static final Pattern SCRIPT_PATTERN = Pattern.compile(SCRIPT_SRC);

    public static JSONArray from(IonResponse response) throws IOException {
        Matcher matcher = SCRIPT_PATTERN.matcher(new String(response.body(), StandardCharsets.UTF_8));
        if (matcher.find()) {
            return new JSONArray(matcher.group(1));
        } else {
            throw new HydrationException("Unable to find Hydration");
        }
    }
}
