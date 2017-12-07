package InterfaceService;

import model.FAQsModel;

/**
 * Created by IPC on 11/22/2017.
 */

public interface FAQsCallback {

    void onSuccess(FAQsModel body);

    void onError(String s);
}
