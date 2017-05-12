package modules.diagnosis;

/**
 * Created by casjohnpaul on 5/8/2017.
 */

public class DiagnosisPresenter implements DiagnosisMvp.Presenter {


    private DiagnosisMvp.View diagnosisView;

    public DiagnosisPresenter() {
    }

    @Override
    public void attachView(DiagnosisMvp.View view) {
        this.diagnosisView = view;
    }

    @Override
    public void detachView() {
        this.diagnosisView = null;
    }

    @Override
    public void attachCallback() {

    }

    @Override
    public void detachCallback() {

    }

}
