package modules.testattachment;

import services.AppInterface;
import services.AppService;

/**
 * Created by casjohnpaul on 5/2/2017.
 */

public class TestAttachmentPresenter implements TestAttachment.Presenter {


    private TestAttachment.View testAttachmentView;
    private AppInterface loaService;

    // test upload
    public TestAttachmentPresenter() {
        // creating service for loa
        loaService = AppService.createApiService(AppInterface.class, AppInterface.ENDPOINT);
    }

    @Override
    public void attachView(TestAttachment.View view) {
        testAttachmentView = view;
    }

    @Override
    public void detachView() {
        testAttachmentView = null;
    }

    @Override
    public void attachCallback() {

    }

    @Override
    public void detachCallback() {

    }

}
