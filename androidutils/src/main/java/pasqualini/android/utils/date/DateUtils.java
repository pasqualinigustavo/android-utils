package pasqualini.android.utils.date;

/**
 * Created by pasqualini on 9/19/16.
 */

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import pasqualini.android.utils.StringUtils;

public class DateUtils {

    public static final String MASK_DATE = "yyyy-MM-dd";
    public static final String MASK_DATE_TIME = "yyyy-MM-dd HH:mm:ss";

    public static final String MASK_BR_DATE = "dd-MM-yyyy";
    public static final String MASK_BR_DATE_TIME = "dd-MM-yyyy HH:mm:ss";


    public static String dateToString(Date date, String mask) {
        if (date == null || StringUtils.isNullOrEmpty(mask)) {
            return null;
        }
        SimpleDateFormat sdf = new SimpleDateFormat(mask);
        return sdf.format(date);
    }

    public static Date stringToDate(String date, String mask) throws ParseException {
        if (StringUtils.isNullOrEmpty(date) || StringUtils.isNullOrEmpty(mask)) {
            return null;
        }

        SimpleDateFormat sdf = new SimpleDateFormat(mask);
        return sdf.parse(date);
    }

    public static Date tryParser(String strDate, String... patterns) {
        if (!StringUtils.hasValue(strDate) || patterns.length == 0) {
            return null;
        }

        SimpleDateFormat dateFormat = null;
        for (int i = 0; i < patterns.length; i++) {
            try {
                dateFormat = new SimpleDateFormat(patterns[i]);
                dateFormat.setLenient(false);
                Date date = dateFormat.parse(strDate);
                if (date != null) {
                    return date;
                }
            } catch (Exception e) {
            }
        }

        return null;
    }
}

