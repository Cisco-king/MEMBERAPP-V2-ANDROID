package modules.selecthospital;

import modules.base.Mvp;

/**
 * Created by John Paul Cas on 4/19/2017.
 */

public interface SelectHospital {

    interface View extends Mvp.View {

    }

    interface Presenter extends Mvp.Presenter<SelectHospital.View> {

    }

}
