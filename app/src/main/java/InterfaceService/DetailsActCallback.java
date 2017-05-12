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

    void onDuplicateRequest(RequestResult requestResult);

    void onErrorConfirm(String message);

    void onBlockRequest(String message);

    void onSuccessConfirm(RequestResult requestResult);
}
