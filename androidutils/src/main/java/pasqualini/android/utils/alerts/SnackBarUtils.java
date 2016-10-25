package pasqualini.android.utils.alerts;

import android.support.design.widget.Snackbar;
import android.view.View;

import pasqualini.android.utils.StringUtils;

/**
 * Created by pasqualini on 23/10/15.
 */
public class SnackBarUtils {
    public static void presentSnackFromView(View view, String message) {
        if (view != null && !StringUtils.isNullOrEmpty(message)) {
            final Snackbar snack = Snackbar.make(view, message, Snackbar.LENGTH_SHORT);
            snack.getView().setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    snack.dismiss();
                }
            });
            snack.show();
        }
    }
}