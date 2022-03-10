package Bibliotheque.QT.BibliothequeProjet;

import org.json.simple.JSONObject;

public class ObjectJsoner {

    public static JSONObject createJsonObjectString(String key, String value) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put(key, value);
        return jsonObject;
    }

    public static JSONObject createJsonObjectBoolean(String key, Boolean value) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put(key, value);
        return jsonObject;
    }
}
