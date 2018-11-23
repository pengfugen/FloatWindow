package pfg.com.carlauncher;


import android.app.Activity;
import android.os.Bundle;
import android.app.AlertDialog;

import pfg.com.carlauncher.dummy.DummyContent;

public class MainActivity extends Activity implements CardFragment.OnListFragmentInteractionListener{

    private String TAG = "MainActivity";
    AlertDialog dialog;

    TestReceiver mReceiver = new TestReceiver();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        //registerReceiver(mReceiver, new IntentFilter("pfg.com.carlauncher.send_to_test"));
    }

    @Override
    protected void onResume() {
        super.onResume();
        // Intent intent = new Intent("pfg.com.carlauncher.send_to_test");
        //sendBroadcast(intent);
    }

    @Override
    public void onListFragmentInteraction(DummyContent.DummyItem item) {

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //unregisterReceiver(mReceiver);
    }
}
