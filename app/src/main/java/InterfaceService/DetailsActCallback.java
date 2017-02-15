package InterfaceService;

import model.RequestResult;

/**
 * Created by mpx-pawpaw on 1/4/17.
 */

public interface DetailsActCallback {


    void onTermsAndConditionListener();

    void onError(String message);

    void onSuccess(RequestResult data);

    void emptyFieldsListener();

    void cancelRequest();
}
