package InterfaceService;

import model.Loa;

/**
 * Created by mpx-pawpaw on 1/17/17.
 */

public interface LOARequestCallback {


    void onErrorLoaListener(String message);

    void onSuccessLoaListener(Loa loa);

    void onDbLoaSuccessListener();
}
