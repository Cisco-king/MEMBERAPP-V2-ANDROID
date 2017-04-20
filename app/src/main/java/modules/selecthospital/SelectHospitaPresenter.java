package modules.selecthospital;

/**
 * Created by John Paul Cas on 4/19/2017.
 */

public class SelectHospitaPresenter implements SelectHospital.Presenter {

    private SelectHospital.View selectHospitalView;

    public SelectHospitaPresenter() {

    }

    @Override
    public void attachView(SelectHospital.View view) {
        this.selectHospitalView = view;
    }

    @Override
    public void detachView() {

    }

    @Override
    public void attachCallback() {

    }

    @Override
    public void detachCallback() {

    }

}
