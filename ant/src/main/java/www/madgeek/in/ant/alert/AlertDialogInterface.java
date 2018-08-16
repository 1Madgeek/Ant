package www.madgeek.in.ant.alert;

import android.content.DialogInterface;

/**
 * Created by Madgeek Pvt Ltd on 11/08/18.
 * Copyright 2016-2017. All Rights Reserved
 * www.madgeek.in
 */

public interface AlertDialogInterface<T> {

    //AlertDialog Positive Feedback
    void onPositiveFeedback(DialogInterface dialogInterface);

    //AlertDialog Negative Feedback
    void onNegativeFeedback(DialogInterface dialogInterface);
}
