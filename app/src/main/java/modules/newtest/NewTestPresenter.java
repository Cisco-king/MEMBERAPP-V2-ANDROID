package modules.newtest;

/**
 * Created by casjohnpaul on 5/6/2017.
 */

public class NewTestPresenter implements NewTestMvp.Presenter {


    private NewTestMvp.View newTestView;

    public NewTestPresenter() {
    }

    @Override
    public void attachView(NewTestMvp.View view) {
        this.newTestView = view;
    }

    @Override
    public void detachView() {
        this.newTestView = null;
    }

    @Override
    public void attachCallback() {

    }

    @Override
    public void detachCallback() {

    }

}
