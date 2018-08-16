package www.madgeek.in.ant.session;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;

import static www.madgeek.in.ant.session.SessionConstants.PRIVATE_MODE;

/**
 * Created by Madgeek Pvt Ltd on 16/08/18.
 * Copyright 2016-2017. All Rights Reserved
 * www.madgeek.in
 */

class SessionManager {

    private static final String PREF_NAME = "ant";

    // Shared Preferences
    private static SharedPreferences pref;

    private static SharedPreferences.Editor editor;
    private Context _context;

    //Empty Constructor
    public SessionManager() {
    }

    //Constructor
    SessionManager(Context context) {
        this._context = context;
        pref = _context.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
        editor = pref.edit();
    }

    //Clear session
    protected void clearSession() {
        editor.clear();
        editor.commit();
    }

    //Save key
    static void putKey(SessionConstants.VALUETYPE valuetype, Object... param) {
        switch (valuetype) {
            case STRING:
                editor.putString(String.valueOf(param[0]), String.valueOf(param[1]));
                break;
            case BOOLEAN:
                editor.putBoolean(String.valueOf(param[0]), (Boolean) param[1]);
                break;
        }
        editor.commit();
    }

    //Get key
    static Object getKey(@NonNull String key, SessionConstants.VALUETYPE valuetype) {
        switch (valuetype) {
            case STRING:
                return pref.getString(key, "");
            case BOOLEAN:
                return pref.getBoolean(key, false);
        }
        return null;
    }
}
