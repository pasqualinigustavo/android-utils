package pasqualini.android.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;
import java.util.Date;

/**
 * Created by pasqualini on 19/11/15.
 */
public class SharedPreferencesUtils {

    private final Context context;
    private final SharedPreferences sharedPref;
    private static SharedPreferencesUtils instance;

    private SharedPreferencesUtils(final Context context,String name) {
        super();
        this.context = context;
        if(name == null)
            this.sharedPref = this.context.getSharedPreferences(this.context.getPackageName(), Context.MODE_PRIVATE);
        else
            this.sharedPref = this.context.getSharedPreferences(name, Context.MODE_PRIVATE);
    }

    public static void init(final Context context,String name) {
        if (instance == null) {
            instance = new SharedPreferencesUtils(context,name);
        }
    }

    public static void init(final Context context) {
        if (instance == null) {
            instance = new SharedPreferencesUtils(context,null);
        }
    }

    public static SharedPreferencesUtils getInstance() {
        if (instance == null) {
            throw new RuntimeException("SharedPreferencesUtils need to be start at the main context with init() method ");
        }
        return instance;
    }

    private boolean isInitialized() {
        if (this.context == null) {
            Log.e(this.getClass().getSimpleName(), "SharedPreferencesUtils need to be start at the main context with init() method ");
            return false;
        }

        return true;
    }

    public <T> void save(final String key, final T value) {
        if (!this.isInitialized()) {
            return;
        }

        if (value == null) {
            return;
        }

        SharedPreferences.Editor editor = this.sharedPref.edit();
        if (value.getClass().equals(Integer.class)) {
            editor.putInt(key, (Integer) value);

        } else if (value.getClass().equals(String.class)) {
            editor.putString(key, (String) value);

        } else if (value.getClass().equals(Long.class)) {
            editor.putLong(key, (Long) value);

        } else if (value.getClass().equals(Boolean.class)) {
            editor.putBoolean(key, (Boolean) value);

        } else if (value.getClass().equals(Date.class)) {
            editor.putLong(key, ((Date) value).getTime());

        } else {
            Log.w(SharedPreferencesUtils.class.getSimpleName(), value.getClass() + " wasn't implemented");
        }

        editor.apply();
    }

    public <T> T getValue(final String key, Class<T> type) {
        if (type.equals(Integer.class)) {
            return type.cast(this.sharedPref.getInt(key, 0));

        } else if (type.equals(String.class)) {
            return type.cast(this.sharedPref.getString(key, null));

        } else if (type.equals(Long.class)) {
            return type.cast(this.sharedPref.getLong(key, 0));

        } else if (type.equals(Boolean.class)) {
            return type.cast(this.sharedPref.getBoolean(key, false));

        } else if (type.equals(Date.class)) {
            long longValue = this.sharedPref.getLong(key, 0);
            if (longValue == 0) {
                return null;
            }
            return type.cast(new Date(longValue));
        } else {
            Log.w(SharedPreferencesUtils.class.getSimpleName(), type + " wasn't implemented");
        }

        return null;
    }

    public void clear(final String key) {
        SharedPreferences.Editor editor = this.sharedPref.edit();
        editor.remove(key);
        editor.apply();
    }

    public void clearAll() {
        SharedPreferences.Editor editor = this.sharedPref.edit();
        editor.clear();
        editor.apply();
    }
}
