package pfg.com.carlauncher;

import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.net.Uri;

public class RequestPermissionActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_request_permission);

        if (Build.VERSION.SDK_INT >= 23) {
            if(!Settings.canDrawOverlays(getApplicationContext())) {
                //启动Activity让用户授权
                Intent intent = new Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION,Uri.parse("package:" + getPackageName()));
                startActivityForResult(intent,10);
            } else {
                Intent intent = new Intent(this, CardService.class);
                startForegroundService(intent);
                //startService(intent);
                finish();
            }
        } else {
            //执行6.0以下绘制代码、
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode == 10) {
            if (Build.VERSION.SDK_INT >= 23) {
                if (Settings.canDrawOverlays(this)) {
                    Intent intent = new Intent(this, CardService.class);
                    startForegroundService(intent);
                    //startService(intent);
                    /*Intent intent1 = new Intent(this, MainActivity.class);
                    startActivity(intent1);*/

                    finish();

                }
            }
        }
    }
}
