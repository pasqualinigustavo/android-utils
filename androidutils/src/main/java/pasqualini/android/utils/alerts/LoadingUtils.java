package pasqualini.android.utils.alerts;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.app.Activity;
import android.view.View;
import android.widget.RelativeLayout;
import pasqualini.android.utils.R;

/**
 * Created by pasqualini on 18/06/2015.
 */
public class LoadingUtils {

    public static void show(final Activity activity) {
        if (activity == null) {
            return;
        }

        activity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                final View view = activity.findViewById(R.id.loadingView);
                if (view != null) {

                    final int visibility = View.VISIBLE;
                    float alpha = 0.8f;

                    view.setVisibility(View.VISIBLE);

                    view.animate()
                            .alpha(alpha)
                            .setDuration(300)
                            .setListener(new AnimatorListenerAdapter() {
                                @Override
                                public void onAnimationEnd(Animator animation) {
                                    super.onAnimationEnd(animation);
                                    view.setVisibility(visibility);
                                }
                            });
                }
            }
        });
    }

    public static void hide(final Activity activity) {
        if (activity == null) {
            return;
        }

        activity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                final View view = activity.findViewById(R.id.loadingView);
                if (view != null) {

                    final int visibility = View.GONE;
                    float alpha = 0.0f;

                    view.setVisibility(View.VISIBLE);

                    view.animate()
                            .alpha(alpha)
                            .setDuration(300)
                            .setListener(new AnimatorListenerAdapter() {
                                @Override
                                public void onAnimationEnd(Animator animation) {
                                    super.onAnimationEnd(animation);
                                    view.setVisibility(visibility);
                                }
                            });
                }
            }
        });
    }
}
