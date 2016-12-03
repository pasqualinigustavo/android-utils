package pasqualini.android.utils.alerts;

import android.content.Context;
import android.content.DialogInterface;

public interface IQuestionDialogEvents {
    void onConfirmQuestionDialog(DialogInterface dialog, int idRequest);

    void onRefuseQuestionDialog(DialogInterface dialog, int idRequest);

    void onCancelQuestionDialog(DialogInterface dialog, int idRequest);

    Context getContext();
}
