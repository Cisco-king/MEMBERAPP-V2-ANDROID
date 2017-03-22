package InterfaceService;

import model.Pinned;
import model.ReturnChangePassword;

/**
 * Created by mpx-pawpaw on 2/6/17.
 */

public interface ChangePasswordWithPinCallback {

    void onSuccess();
    void onError();

    void incorrectFiledListener();

    void passwordWrongFormatListener();

    void sendPasswordListener();

    void onErrorChangePassword();

    void onSuccessListener(ReturnChangePassword returnChangePassword);

    void noInternetConnectionListener();

    void notEqualPasswordListener();

    void testInputListener();

    void updatePin(String newPIN, String oldPIN);

    void onSuccessUpdatePin(Pinned pinned, String newPIN);

    void onErrorUpdatePin();

    void registerPin(String text);

    void onSuccessRegisterPin(String responseCode, String newPin);

    void onErrorRegisterPin(Throwable error);

    void testInputPinListener();
}
