package com.example.joao.myweatherapp;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class JSON {

    private final String TAG = JSON.class.getSimpleName();

    private JSONObject json;

    public JSON(JSONObject json) {
        this.json = json;
    }

    public String get(String key) {
        String[] keys = key.split("[/]");
        Object obj = json;
        try {
            for (int i = 0; i < keys.length; i++) {
                String k = keys[i];
                int j = k.indexOf("[");
                if (j > 0) {
                    k = k.substring(0, j);
                    j = Integer.parseInt(keys[i].substring(j + 1, keys[i].length() - 1));
                }
                obj = ((JSONObject) obj).get(k);
                if (j >= 0) {
                    if (obj instanceof JSONArray) {
                        JSONArray a = (JSONArray) obj;
                        if (j < a.length()) {
                            obj = a.get(j);
                        } else {
                            throw new JSONException("JSONArray[\"" + key + "\"] not found.");
                        }
                    } else {
                        throw new JSONException("Invalid JSONArray[\"" + key + "\"].");
                    }
                }
            }
            return obj.toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }
}
