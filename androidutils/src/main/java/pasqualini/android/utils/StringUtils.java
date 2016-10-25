package pasqualini.android.utils;

import java.text.Normalizer;

public class StringUtils {

    public static boolean hasValue(String string) {
        if (string == null) {
            return false;
        }
        if (string.trim().isEmpty()) {
            return false;
        }
        return true;
    }

    public static String checkNullStr(String string) {
        if (StringUtils.hasValue(string) && string.equalsIgnoreCase("null")) {
            return null;
        }
        return string;
    }

    public static String removeDiacriticalMarks(final String string) {
        return Normalizer.normalize(string, Normalizer.Form.NFD)
                .replaceAll("\\p{InCombiningDiacriticalMarks}+", "");
    }

    public static boolean existInString(String string, String query) {
        if (!StringUtils.hasValue(string)) {
            return false;
        }

        string = StringUtils.removeDiacriticalMarks(string.toLowerCase());
        query = StringUtils.removeDiacriticalMarks(query.toLowerCase());
        if (string.contains(query)) {
            return true;
        }
        return false;
    }

    public static boolean isNullOrEmpty(Object obj){
        if(obj != null && obj instanceof String){
            String str = (String)obj;
            return str.isEmpty();
        }
        return true;
    }
}
