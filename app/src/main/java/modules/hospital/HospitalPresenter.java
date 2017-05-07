package modules.hospital;

import android.content.Context;

import database.dao.HospitalDao;
import database.entity.Doctor;

/**
 * Created by casjohnpaul on 5/7/2017.
 */

public class HospitalPresenter implements HospitalMvp.Presenter {


    private HospitalMvp.View hospitalView;
    private HospitalDao hospitalDao;

    public HospitalPresenter(Context context) {
        hospitalDao = new HospitalDao(context);
    }

    @Override
    public void attachView(HospitalMvp.View view) {
        hospitalView = view;
    }

    @Override
    public void detachView() {
        hospitalView = null;
    }

    @Override
    public void attachCallback() {

    }

    @Override
    public void detachCallback() {

    }

    @Override
    public void getHospitalListByDoctor(Doctor doctor) {
//        hospitalDao.findAllHospitalByHospitalCode(doctor.get)
    }
}
