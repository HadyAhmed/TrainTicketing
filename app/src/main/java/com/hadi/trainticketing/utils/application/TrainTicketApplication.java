package com.hadi.trainticketing.utils.application;

import android.app.Activity;
import android.app.Application;
import android.content.pm.ActivityInfo;
import android.os.Bundle;

/**
 * @author Hady Ahmed
 * @version 1.0
 * @since 08th, April 2019
 * instance of the {@link Application} to handle the life cycle of the activities
 */
public class TrainTicketApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        registerActivityLifecycleCallbacks(new ActivityLifecycleCallbacks() {
            @Override
            public void onActivityCreated(Activity activity, Bundle savedInstanceState) {
                // prevent the user from capture the screen (screenshots or recording)
                activity.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
//                activity.getWindow().setFlags(WindowManager.LayoutParams.FLAG_SECURE, WindowManager.LayoutParams.FLAG_SECURE);
            }

            @Override
            public void onActivityStarted(Activity activity) {
                //
            }

            @Override
            public void onActivityResumed(Activity activity) {
                //
            }

            @Override
            public void onActivityPaused(Activity activity) {
                //
            }

            @Override
            public void onActivityStopped(Activity activity) {
                //
            }

            @Override
            public void onActivitySaveInstanceState(Activity activity, Bundle outState) {
                //
            }

            @Override
            public void onActivityDestroyed(Activity activity) {
                //
            }
        });
    }
}
