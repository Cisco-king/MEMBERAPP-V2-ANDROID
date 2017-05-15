package modules.prescriptionattachment;

/**
 * Created by casjohnpaul on 5/14/2017.
 */

public class PrescriptionAttachmentPresenter
        implements PrescriptionAttachmentMvp.Presenter {


    private PrescriptionAttachmentMvp.View attachmentView;

    public PrescriptionAttachmentPresenter() {
    }

    @Override
    public void attachView(PrescriptionAttachmentMvp.View view) {
        this.attachmentView = view;
    }

    @Override
    public void detachView() {
        this.attachmentView = null;
    }

    @Override
    public void attachCallback() {

    }

    @Override
    public void detachCallback() {

    }

}
