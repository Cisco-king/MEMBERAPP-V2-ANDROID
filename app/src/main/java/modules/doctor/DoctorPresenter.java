package modules.doctor;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;

import database.dao.DoctorDao;
import database.entity.Doctor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import services.AppInterface;
import services.AppService;
import services.ServiceGenerator;
import services.client.DoctorClient;
import services.model.HospitalsToDoctor;
import services.response.HospitalsByDoctorCodeResponse;
import services.response.HospitalsToDoctorResponse;
import timber.log.Timber;

/**
 * Created by casjohnpaul on 5/7/2017.
 */

public class DoctorPresenter implements DoctorMvp.Presenter {


    private DoctorMvp.View doctorView;
    private DoctorDao doctorDao;
    private DoctorClient doctorHttpClient;

    List<Doctor> doctorsWithHospitals;

    public DoctorPresenter(Context context) {
        doctorDao = new DoctorDao(context);
        doctorHttpClient = AppService.createApiService(DoctorClient.class, AppInterface.ENDPOINT);
//        doctorHttpClient = ServiceGenerator.createApiService(DoctorClient.class);
        doctorsWithHospitals = new ArrayList<>();
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

   /* @Override
    public void getAllDoctors() {

       *//* List<Doctor> doctors = doctorDao.findAll();
        Timber.d("total doctors %s", doctors.size());
        Timber.d("doctorCode %s", doctors.get(0).getDoctorCode());
        *//**//*for (Doctor doctor : doctors) {
            doctorHttpClient.getHospitalsByDoctorCode(doctor.getDoctorCode())
                    .enqueue(new Callback<HospitalsByDoctorCodeResponse>() {
                        @Override
                        public void onResponse(Call<HospitalsByDoctorCodeResponse> call, Response<HospitalsByDoctorCodeResponse> response) {
                            
                        }

                        @Override
                        public void onFailure(Call<HospitalsByDoctorCodeResponse> call, Throwable t) {

                        }
                    });
        };*//**//*

        doctorView.displayDoctors(doctors);*//*
    }*/

    @Override
    public void loadAllDoctors() {

        /*doctorHttpClient.getAllDoctorsToHospitalRx()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<HospitalsToDoctorResponse>() {
                    @Override
                    public void onCompleted() {
                        Timber.d("complete download");
                    }

                    @Override
                    public void onError(Throwable e) {
                        Timber.d("error encounter %s", e.toString());
                        doctorView.onErrorRequest(e.toString());
                    }

                    @Override
                    public void onNext(HospitalsToDoctorResponse hospitalsToDoctorResponse) {
                        Timber.d("hospital retrieve %s", hospitalsToDoctorResponse.getDoctorsToHospital().size());
                        doctorView.displayDoctorsByHospital(hospitalsToDoctorResponse.getDoctorsToHospital());
                    }
                });*/
        doctorHttpClient.getAllDoctorsToHospital()
                .enqueue(new Callback<HospitalsToDoctorResponse>() {
                    @Override
                    public void onResponse(Call<HospitalsToDoctorResponse> call, Response<HospitalsToDoctorResponse> response) {
                        Timber.d("response : %s", response.raw().toString());
                        if (response.isSuccessful()) {
                            doctorView.displayDoctorsByHospital(
                                    response.body().getDoctorsToHospital()
                            );
                        } else {
                            doctorView.onErrorRequest("Error Occured");
                        }
                    }

                    @Override
                    public void onFailure(Call<HospitalsToDoctorResponse> call, Throwable t) {

                    }
                });

    }


    @Override
    public void filterDoctors(List<HospitalsToDoctor> doctors, String query) {
        try {
            query = query.toLowerCase();
            List<HospitalsToDoctor> doctorList = new ArrayList<>();
            for (HospitalsToDoctor doctor : doctors) {
                String fullName =
                        doctor.getFullName() != null ? doctor.getFullName().toLowerCase() : "";
                String specialization =
                        doctor.getSpecDesc() != null ? doctor.getSpecDesc().toLowerCase() : "";

                if (fullName.contains(query) || specialization.contains(query)) {
                    doctorList.add(doctor);
                }
            }

            doctorView.displayFilteredDoctors(doctorList);
        } catch (Exception e) {
            Timber.d("error message %s", e.toString());
        }
    }

    public List<Doctor> getDoctorsWithHospitals() {
        return doctorsWithHospitals;
    }

    public void setDoctorsWithHospitals(List<Doctor> doctorsWithHospitals) {
        this.doctorsWithHospitals = doctorsWithHospitals;
    }

    public void addDoctorWithHospitals(Doctor doctor) {
        this.doctorsWithHospitals.add(doctor);
    }

}
