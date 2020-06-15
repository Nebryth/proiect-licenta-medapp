package project_root.utilities.logging_in;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import javax.servlet.http.HttpSession;

public class SessionManager {
    private static Gson gson = new GsonBuilder().serializeSpecialFloatingPointValues().serializeNulls().create();

    public static Boolean sessionExists(HttpSession httpSession) {
        System.out.println(gson.toJsonTree(httpSession.getAttributeNames()));
        return !gson.toJson(httpSession.getAttributeNames()).equals("null");
    }
}
