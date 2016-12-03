package pasqualini.android.utils.alerts;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.support.annotation.DrawableRes;
import android.support.annotation.StringRes;
import android.util.Log;

import pasqualini.android.utils.R;
import pasqualini.android.utils.StringUtils;

/**
 * Created by pasqualini on 19/11/15.
 */
public class AlertUtils {

    /**
     * Show alert with informative message.
     */
    public static void showMessage(final Context context, @StringRes int message) {
        AlertUtils.showMessage(context, context.getString(message));
    }

    /**
     * Show alert with informative message.
     */
    public static void showMessage(final Context context, String message) {
        AlertUtils.showDialog(context, R.drawable.ic_action_info, context.getString(R.string.label_information), message);
    }

    /**
     * Show alert with informative message.
     */
    public static void showMessage(final Context context, String message, int ic_drawable_icon) {
        AlertUtils.showDialog(context, ic_drawable_icon, context.getString(R.string.label_information), message);
    }

    /**
     * Show alert with warning message.
     */
    public static void showWarning(final Context context, @StringRes int message) {
        AlertUtils.showDialog(context, R.drawable.ic_action_info, context.getString(R.string.label_warning), context.getString(message));
    }

    /**
     * Show alert with warning message.
     */
    public static void showWarning(final Context context, @StringRes int message, int ic_drawable_icon) {
        AlertUtils.showDialog(context, ic_drawable_icon, context.getString(R.string.label_warning), context.getString(message));
    }

    /**
     * Show alert with error message.
     */
    public static void showError(final Context context, @StringRes int message) {
        AlertUtils.showDialog(context, R.drawable.ic_content_remove_circle, context.getString(R.string.label_erro), context.getString(message));
    }

    /**
     * Show alert with error message.
     */
    public static void showError(final Context context, @StringRes int message, int ic_drawable_icon) {
        AlertUtils.showDialog(context, ic_drawable_icon, context.getString(R.string.label_erro), context.getString(message));
    }

    /**
     * Show alert with warning message.
     */
    public static void showError(final Context context, Exception e) {
        String erro = "";

        if (e != null && e.getClass() != null) {
            erro = e.getClass().getSimpleName() + "\n";
        }
        if (!StringUtils.isNullOrEmpty(e.getMessage())) {
            erro += e.getMessage();
        }

        int icDialog = R.drawable.ic_content_remove_circle;
        Log.e(context.getClass().getSimpleName(), erro, e);

        AlertUtils.showDialog(context, icDialog, context.getString(R.string.label_erro), erro);
    }

    /**
     * {@link AlertUtils#showDialog(Context, int, String, String, int)}
     */
    public static void showDialog(final Context context, @DrawableRes int icon, String title, String message) {
        AlertUtils.showDialog(context, icon, title, message, 0);
    }

    /**
     * Show dialog message
     *
     * @param context
     * @param icon
     * @param title
     * @param message
     * @param idRequest identification for multi-dialogs.
     */
    private static void showDialog(final Context context, @DrawableRes int icon, String title, String message, final int idRequest) {
        DialogInterface.OnClickListener action = null;
        boolean cancelable = true;
        if (context instanceof IConfirmDialogEvent) {
            action = new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    ((IConfirmDialogEvent) context).onOkConfirmDialog(idRequest);
                    dialog.dismiss();
                }
            };

        } else {
            action = new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                }
            };
            cancelable = false;
        }

        AlertDialog.Builder alertBuilder = new AlertDialog.Builder(context);
        alertBuilder.setTitle(title);
        alertBuilder.setIcon(icon);
        alertBuilder.setMessage(message);
        alertBuilder.setCancelable(cancelable);
        alertBuilder.setPositiveButton(context.getString(R.string.label_ok), action);
        AlertDialog dialog = alertBuilder.create();
        dialog.show();
    }

    /**
     * @see AlertUtils#showQuestionDialog(IQuestionDialogEvents, String, String, int, boolean)
     */
    public static void showQuestionDialog(final IQuestionDialogEvents listener, String title, String message, final int idRequest) {
        showQuestionDialog(listener, title, message, idRequest, false);
    }

    /**
     * @param listener
     * @param title
     * @see AlertUtils#showQuestionDialog(IQuestionDialogEvents, String, String, int, boolean)
     */
    public static void showQuestionDialog(final IQuestionDialogEvents listener, @StringRes int title, @StringRes int message) {
        showQuestionDialog(listener, listener.getContext().getString(title), listener.getContext().getString(message), 0, true);
    }

    /**
     * @param listener
     * @param title
     * @param requestId
     * @see AlertUtils#showQuestionDialog(IQuestionDialogEvents, String, String, int, boolean)
     */
    public static void showQuestionDialog(final IQuestionDialogEvents listener, @StringRes int title, @StringRes int message, int requestId) {
        showQuestionDialog(listener, listener.getContext().getString(title), listener.getContext().getString(message), 0, true);
    }

    /**
     * Dialog with options: confirm, reguse and cancel
     * Implements interface {@link IQuestionDialogEvents}
     *
     * @param idRequest  request actions.
     * @param cancelable cancelable
     */
    public static void showQuestionDialog(final IQuestionDialogEvents listener, String title, String message, final int idRequest, boolean cancelable) {
        Context context = listener.getContext();

        AlertDialog.Builder alertBuilder = new AlertDialog.Builder(context);
        alertBuilder.setTitle(title);
        alertBuilder.setMessage(message);

        alertBuilder.setIcon(R.drawable.ic_maps_directions);

        alertBuilder.setCancelable(cancelable);

        alertBuilder.setPositiveButton(context.getString(R.string.label_yes), new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                listener.onConfirmQuestionDialog(dialog, idRequest);
            }
        });

        alertBuilder.setNegativeButton(context.getString(R.string.label_no), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                listener.onRefuseQuestionDialog(dialog, idRequest);
            }
        });

        if (cancelable) {
            alertBuilder.setOnCancelListener(new DialogInterface.OnCancelListener() {
                public void onCancel(DialogInterface dialog) {
                    listener.onCancelQuestionDialog(dialog, idRequest);
                }
            });
        }

        AlertDialog dialog = alertBuilder.create();
        dialog.show();
    }

}
