package www.madgeek.in.ant.alert;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;

/**
 * Created by Madgeek Pvt Ltd on 11/08/18.
 * Copyright 2016-2017. All Rights Reserved
 * www.madgeek.in
 */

public class AlertDialogManager {

    //AlertDialogInterface Listener
    private final AlertDialogInterface<String> vListener;
    //AlertDialog Builder object
    private AlertDialog.Builder vBuilder;

    //AlertDialog
    private AlertDialog vDialog;

    //Constructor
    private AlertDialogManager(Activity activity, AlertDialogInterface<String> listener) {
        vBuilder = new AlertDialog.Builder(activity);
        this.vListener = listener;
    }

    public static AlertDialogManager init(Activity activity, AlertDialogInterface<String> listener){
        return new AlertDialogManager(activity,listener);
    }

    //Set Alert Title
    public AlertDialogManager setsTitle(String sTitle) {
        this.vBuilder.setTitle(sTitle);
        return this;
    }

    //Set Alert Message
    public AlertDialogManager setsMessage(String sMessage) {
        this.vBuilder.setMessage(sMessage);
        return this;
    }

    //Set Alert Positive Feedback Message
    public AlertDialogManager setsPositiveMessage(String sPositiveMessage) {
        this.vBuilder.setPositiveButton(sPositiveMessage, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                vListener.onPositiveFeedback(dialogInterface);
            }
        });
        return this;
    }

    //Set Alert Negative Feedback Message
    public AlertDialogManager setsNegativeMessage(String sNegativeMessage) {
        this.vBuilder.setNegativeButton(sNegativeMessage, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                vListener.onNegativeFeedback(dialogInterface);
            }
        });
        return this;
    }

    //Create the AlertDialog
    private void mCreate() {
        this.vDialog = this.vBuilder.create();
    }

    //Get AlertDialog Object
    public AlertDialog getvDialog() throws NullPointerException {
        return this.vDialog;
    }

    //Show the AlertDialog
    public void mShow() {
        mCreate();
        this.vBuilder.show();
    }

    //Check if the dialog is visible
    public boolean mIsVisible() {
        return this.vDialog.isShowing();
    }

    //Dismiss Dialog
    public void mDismiss() {
        if (this.vDialog.isShowing())
            this.vDialog.dismiss();
    }
}
