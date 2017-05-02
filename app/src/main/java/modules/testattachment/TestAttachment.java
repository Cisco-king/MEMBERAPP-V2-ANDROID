package modules.testattachment;

import modules.base.Mvp;

/**
 * Created by casjohnpaul on 5/2/2017.
 */

public interface TestAttachment {

    interface View extends Mvp.View {

    }

    interface Presenter extends Mvp.Presenter<TestAttachment.View> {

    }

}
