package modules.doctor;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;

import database.dao.DoctorDao;
import database.entity.Doctor;
import timber.log.Timber;

/**
 * Created by casjohnpaul on 5/7/2017.
 */

public class DoctorPresenter implements DoctorMvp.Presenter {


    private DoctorMvp.View doctorView;
    private DoctorDao doctorDao;

    public DoctorPresenter(Context context) {
        doctorDao = new DoctorDao(context);
    }

    @Override
    public void attachView(DoctorMvp.View view) {
        this.doctorView = view;
    }

    @Override
    public void detachView() {
        this.doctorView = null;
    }

    @Override
    public void attachCallback() {

    }

    @Override
    public void detachCallback() {

    }

    @Override
    public void getAllDoctors() {
        List<Doctor> doctors = doctorDao.findAll();
        Timber.d("total doctors %s", doctors.size());

        doctorView.displayDoctors(doctors);
    }

    @Override
    public void filterDoctors(List<Doctor> doctors, String query) {
        try {
            query = query.toLowerCase();
            List<Doctor> doctorList = new ArrayList<>();

            for (Doctor doctor : doctors) {

                String fullName =
                        doctor.getFullName() != null ? doctor.getFullName().toLowerCase() : "";
                String specialization =
                        doctor.getSpecializationDescription() != null ? doctor.getSpecializationDescription().toLowerCase() : "";

                if (fullName.contains(query) || specialization.contains(query)) {
                    doctorList.add(doctor);
                }
            }

            doctorView.displayFilteredDoctors(doctorList);
        } catch (Exception e) {
            Timber.d("error message %s", e.toString());
        }
    }

}
