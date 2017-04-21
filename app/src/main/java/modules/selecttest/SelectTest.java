package modules.selecttest;

import modules.base.Mvp;

/**
 * Created by John Paul Cas on 4/21/2017.
 */

public interface SelectTest {

    interface View extends Mvp.View {

    }

    interface Presenter extends Mvp.Presenter<SelectTest.View> {

    }

}
