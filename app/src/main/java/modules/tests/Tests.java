package modules.tests;

import modules.base.Mvp;

/**
 * Created by John Paul Cas on 4/11/2017.
 */

public interface Tests {

    interface View extends Mvp.View {}

    interface Presenter extends Mvp.Presenter<Tests.View> {

    }

}
