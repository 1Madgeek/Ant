package www.madgeek.in.ant.session;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;

/**
 * Created by Madgeek Pvt Ltd on 16/08/18.
 * Copyright 2016-2017. All Rights Reserved
 * www.madgeek.in
 */

public class SessionBuilder extends SessionManager {


    private SessionBuilder(Context context) {
        super(context);
    }


    public static SessionBuilder withContext(@NonNull Context context) {
        return new SessionBuilder(context);
    }

    public static SessionBuilder withActivity(@NonNull Activity activity) {
        return new SessionBuilder(activity.getApplicationContext());
    }

    //Key & Value
    public void put(SessionConstants.VALUETYPE valuetype, Object... param) {
         putKey(valuetype, (Object[]) param);
    }

    public Object get(String key, SessionConstants.VALUETYPE valuetype) {
        return getKey(key, valuetype);
    }

}
