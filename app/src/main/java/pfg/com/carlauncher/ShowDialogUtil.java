package pfg.com.carlauncher;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;

/**
 * Created by fpeng3 on 2018/11/19.
 */

import pfg.com.carlauncher.R;

public class ShowDialogUtil {

    public static void showDialog(Context context) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setMessage(R.string.dialog_message)
                .setPositiveButton(R.string.dialog_btn_positive, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        // Send the positive button event back to the host activity
                        dialog.dismiss();
                    }
                })
                .setNegativeButton(R.string.dialog_btn_negative, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        // Send the negative button event back to the host activity
                        dialog.cancel();
                    }
                });
        /*dialog = builder.create();
        dialog.show();*/

        builder.show();
    }

}
