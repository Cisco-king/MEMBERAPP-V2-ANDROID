package modules.prescriptionattachment;

import modules.base.Mvp;

/**
 * Created by casjohnpaul on 5/14/2017.
 */

public interface PrescriptionAttachmentMvp {

    interface View extends Mvp.View { }

    interface Presenter extends Mvp.Presenter<PrescriptionAttachmentMvp.View> { }

}
