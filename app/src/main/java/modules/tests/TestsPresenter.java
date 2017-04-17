package modules.tests;

import modules.base.Mvp;

/**
 * Created by John Paul Cas on 4/11/2017.
 */

public class TestsPresenter implements Tests.Presenter {

    private Tests.View testsView;


    @Override
    public void attachView(Tests.View view) {
        this.testsView = view;
    }

    @Override
    public void detachView() {
        this.testsView = null;
    }

    @Override
    public void attachCallback() {

    }

    @Override
    public void detachCallback() {

    }

}
