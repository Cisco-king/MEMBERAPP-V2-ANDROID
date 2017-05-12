package modules.diagnosis;

import modules.base.Mvp;

/**
 * Created by casjohnpaul on 5/8/2017.
 */

public interface DiagnosisMvp {

    interface View extends Mvp.View {

    }

    interface Presenter extends Mvp.Presenter<View> {

    }

}
