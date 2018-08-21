package proxy;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * Created by nnkwrik
 * 18/08/11 10:33
 */
public class Utils {

    private static final Gson gson = new GsonBuilder().disableHtmlEscaping().create();

    private Utils() {
    }



    /**
     * obj -> json
     */
    public static String toJson(Object o) {
        try {
            return gson.toJson(o);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * json -> obj
     */
    public static <T> T fromJson(String json, Class<T> classOfT) {
        try {
            return gson.fromJson(json, classOfT);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }



}
