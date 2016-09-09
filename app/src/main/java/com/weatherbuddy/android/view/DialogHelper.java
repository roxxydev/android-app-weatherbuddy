package com.weatherbuddy.android.view;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Build;
import android.os.Handler;
import android.view.ContextThemeWrapper;
import android.view.Gravity;
import android.widget.TextView;

import com.weatherbuddy.core.utils.os.OsUtil;

import com.weatherbuddy.android.R;

public class DialogHelper {

    /** Event listener of positive and negative button click. */
    public static abstract class IDialogOnClickListener {
        /** Dialog positive button clicked event. */
        public void onPositiveClick() {}

        /** Dialog negative button clicked event. */
        public void onNegativeClick() {}
    }

    public DialogHelper() {
    }

    public static Dialog newInstance(Context ctx, String dlgTitle, String message,
                                     String btnPositiveText, String btnNegativeText,
                                     boolean isShowPositiveBtn, boolean isShowNegativeBtn,
                                     final IDialogOnClickListener onClickListener) {
        AlertDialog alertDlg = new AlertDialog.Builder(
                new ContextThemeWrapper(ctx, R.style.DialogBaseStyle))
                .setMessage(message)
                .create();
        alertDlg.setCancelable(false);
        if (isShowPositiveBtn) {
            alertDlg.setButton(DialogInterface.BUTTON_POSITIVE, btnPositiveText,
                    new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int i) {
                            dialog.dismiss();
                            if(onClickListener != null)
                                onClickListener.onPositiveClick();
                        }
                    });
        }
        if (isShowNegativeBtn) {
            alertDlg.setButton(DialogInterface.BUTTON_NEGATIVE, btnNegativeText,
                    new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int i) {
                            dialog.dismiss();
                            if(onClickListener != null)
                                onClickListener.onNegativeClick();
                        }
                    });
        }
        return alertDlg;
    }

    /**
     * Create new instance of DialogConfimation
     * @param ctx Must be instance  of BaseActionBarActivity or BaseActivity
     * @param dialogTitle String value of dialog title
     * @param message String value of dialog message
     * @param btnPositiveText The button text of positive button
     * @param btnNegativeText The button text of negative button
     * @param dlgListener The onClickListener of positive and negative button, onClickListener can be null.
     *
     * @return DialogConfirmation instance
     *
     *         Example implementation:
     *         <br></br>
     *         <pre>
     *{@code
     *         DialogConfirmation dlgConfirm = DialogConfirmation.newInstance(true,
     *          getResources().getString(R.string.app_name),
     *          "Any changes would be lost, are you sure you want to leave?", "OK", "CANCEL",
     *          new DialogConfirmation.IDialogOnClickListener() {
     *                  @Override
     *                  public void onPositiveClick() {
     *                      getFragmentManager().popBackStack();
     *                  }
     *                  @Override
     *                  public void onNegativeClick() {}
     *              }
     *         );
     *         dlgConfirm.show(getFragmentManager(), "dlgConfirm password update");
     * }
     * </pre>
     */
    @SuppressLint("NewApi")
    public static void showDialog(Context ctx, String dialogTitle, String message,
                                  String btnPositiveText, String btnNegativeText,
                                  boolean isShowPositiveBtn, boolean isShowNegativeBtn,
                                  IDialogOnClickListener dlgListener) {
        if(ctx != null && ctx instanceof Activity && !((Activity) ctx).isFinishing()) {
            final Dialog dlg = newInstance(ctx,
                    dialogTitle, message, btnPositiveText, btnNegativeText,
                    isShowPositiveBtn, isShowNegativeBtn,
                    dlgListener);
            if(OsUtil.getOsApiLevel() > Build.VERSION_CODES.JELLY_BEAN &&
                    ((Activity) ctx).isDestroyed()) {
                // Do not show Activity if it is already destroyed.
                // Method isDestroyed() is added in API level 17 above.
            } else {
                new Handler().post(new Runnable() {
                    @Override
                    public void run() {
                        dlg.show();
                        if (dlg.findViewById(android.R.id.message) != null) {
                            // to make sure the device has the default textView for dialog
                            TextView messageView = (TextView) dlg.findViewById(android.R.id.message);
                            messageView.setGravity(Gravity.CENTER);
                        }
                    }
                });
            }
        }
    }

    /**
     * Show dialog message prompt.
     * @param isShowNegativeBtn True if to show negative button, false if not.
     * @param listener DialogConfirmation.IDialogOnClickListener callback event when
     *                 positive and negative button click.
     */
    public static void showDialog(Context ctx, String message, boolean isShowNegativeBtn,
                                  IDialogOnClickListener listener) {
        showDialog(ctx, ctx.getString(R.string.app_name),
                message,
                ctx.getString(R.string.dlg_btn_ok_label),
                ctx.getString(R.string.dlg_btn_cancel_label),
                true,
                isShowNegativeBtn,
                listener);
    }
}
