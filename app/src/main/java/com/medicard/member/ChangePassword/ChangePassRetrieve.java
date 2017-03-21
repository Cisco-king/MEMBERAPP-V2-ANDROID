package com.medicard.member.ChangePassword;

import android.content.Context;

/**
 * Created by mpx-pawpaw on 2/3/17.
 */

public class ChangePassRetrieve {

    ChangePassInterface callback ;
    Context context ;

    public ChangePassRetrieve(ChangePasswordActivity changePasswordActivity) {
        this.context = changePasswordActivity ;
        callback = changePasswordActivity ;
    }
}


