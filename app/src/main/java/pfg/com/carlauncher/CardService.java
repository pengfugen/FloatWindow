package pfg.com.carlauncher;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.graphics.PixelFormat;
import android.os.Build;
import android.os.IBinder;
import android.support.v4.app.NotificationCompat;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import pfg.com.carlauncher.dummy.DummyContent;

public class CardService extends Service implements CardFragment.OnListFragmentInteractionListener {
    private static String TAG = "CardService";

    WindowManager windowManager;
    FrameLayout card_container_layout;
    FrameLayout card_layout;

    public CardService() {
    }

    @Override
    public void onCreate() {
        super.onCreate();
        LogUtil.logd(TAG, "onCreate");
        if (Build.VERSION.SDK_INT >= 26) {
            /*String CHANNEL_ID = "my_channel_01";
            NotificationChannel channel = new NotificationChannel(CHANNEL_ID,
                    "Channel human readable title",
                    NotificationManager.IMPORTANCE_DEFAULT);

            ((NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE)).createNotificationChannel(channel);*/

            Notification notification = new NotificationCompat.Builder(this)
                    .setContentTitle("")
                    .setContentText("").build();

            startForeground(1, notification);
        }
        showCard();
    }

    private void showCard() {
        windowManager = (WindowManager) getSystemService(WINDOW_SERVICE);
        final WindowManager.LayoutParams params = new WindowManager.LayoutParams(
                WindowManager.LayoutParams.WRAP_CONTENT,
                WindowManager.LayoutParams.WRAP_CONTENT,
                getOverlayType(),
                WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE | WindowManager.LayoutParams.FLAG_ALT_FOCUSABLE_IM,
                PixelFormat.TRANSLUCENT);
        params.gravity = Gravity.CENTER_VERTICAL | Gravity.LEFT;
        int layoutId = R.layout.card_container_layout;
        card_container_layout = (FrameLayout) LayoutInflater.from(this).inflate(layoutId, null);

        windowManager.addView(card_container_layout, params);


        CardFragment fragment = new CardFragment();
        FragmentManager fragmentManager = FragmentHostManager.get(this, card_container_layout).getFragmentManager();

        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.card_layout, fragment);
        fragmentTransaction.commit();
    }

    public int getOverlayType() {
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.O
                ? WindowManager.LayoutParams.TYPE_APPLICATION_OVERLAY
                : WindowManager.LayoutParams.TYPE_PHONE;
    }

    private void hideCard() {

    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return START_STICKY;
    }

    @Override
    public void onListFragmentInteraction(DummyContent.DummyItem item) {

    }
}
