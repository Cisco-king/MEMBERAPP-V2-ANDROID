package InterfaceService;

import android.content.Context;

import java.lang.reflect.Member;

import utilities.SharedPref;

/**
 * Created by mpx-pawpaw on 3/17/17.
 */

public class MemberberAccountRetrieve {

    private MemberAccountCallback callback;
    private Context context;

    public MemberberAccountRetrieve(Context context, MemberAccountCallback callback) {
        this.callback = callback;
        this.context = context;
    }

    public boolean testPinAvailable() {

        return SharedPref.getStringValue(SharedPref.USER, SharedPref.PIN_IS_AVAILABLE, context)
                .equals("TRUE");
    }
}
