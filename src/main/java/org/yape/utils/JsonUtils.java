package org.yape.utils;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.FileReader;
import java.io.IOException;

public class JsonUtils {

    public static JsonObject readJsonObjectFromFile(String filePath) {
        try {
            return JsonParser.parseReader(new FileReader(filePath)).getAsJsonObject();
        } catch (IOException e) {
            throw new RuntimeException("Failed to read JSON file", e);
        }
    }
}