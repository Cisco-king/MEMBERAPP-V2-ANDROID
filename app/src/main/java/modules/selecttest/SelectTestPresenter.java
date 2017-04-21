package modules.selecttest;

/**
 * Created by John Paul Cas on 4/21/2017.
 */

public class SelectTestPresenter
        implements SelectTest.Presenter {


    SelectTest.View selectTestView;

    @Override
    public void attachView(SelectTest.View view) {
        selectTestView = view;
    }

    @Override
    public void detachView() {
        selectTestView = null;
    }

    @Override
    public void attachCallback() {

    }

    @Override
    public void detachCallback() {

    }

}
