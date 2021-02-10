package net.thefuturetoday.stockopedia.securityanalytics.util;

import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

public class JsonUtils {
    public static boolean isJson(String value) {
        try {
            new JSONObject(value);
        } catch (JSONException ex) {
            try {
                new JSONArray(value);
            } catch (JSONException ex1) {
                return false;
            }
        }
        return true;
    }
}
