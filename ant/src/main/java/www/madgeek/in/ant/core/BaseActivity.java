package www.madgeek.in.ant.core;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import www.madgeek.in.ant.R;
import www.madgeek.in.ant.alert.AlertDialogInterface;
import www.madgeek.in.ant.alert.AlertDialogManager;


/**
 * Created by Madgeek Pvt Ltd on 11/08/18.
 * Copyright 2016-2017. All Rights Reserved
 * www.madgeek.in
 */

public abstract class BaseActivity extends AppCompatActivity {

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Variables, Class -- initiated
        _initialize();
        //Application logics implemented
        _logics();
    }

    @Override
    public void onBackPressed() {
//        super.onBackPressed();  // Overrive back button press
        AlertDialogManager.init(BaseActivity.this, new AlertDialogInterface<String>() {
            @Override
            public void onPositiveFeedback(DialogInterface dialogInterface) {
                BaseActivity.this.finish();
            }

            @Override
            public void onNegativeFeedback(DialogInterface dialogInterface) {
                dialogInterface.dismiss();
            }
        }).setsTitle(getString(R.string.exit))
                .setsMessage(getString(R.string.exit_message))
                .setsPositiveMessage("I'LL BE BACK")
                .setsNegativeMessage("CANCEL")
                .mShow();
    }

    protected abstract void initialize();


    public void _initialize() {
        // Initialize functions, views, variables
        initialize();
    }

    protected abstract void logics();

    public void _logics(){
        // Add application Logics
        logics();
    }

}
