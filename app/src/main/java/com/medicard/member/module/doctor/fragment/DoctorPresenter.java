package com.medicard.member.module.doctor.fragment;

import android.content.Context;

import com.medicard.member.module.doctor.fragment.DoctorMvp;

import java.util.ArrayList;
import java.util.List;

import database.dao.DoctorDao;
import database.entity.Doctor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import services.AppInterface;
import services.AppService;
import services.ServiceGenerator;
import services.client.DoctorClient;
import services.model.HospitalsToDoctor;
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
//        doctorHttpClient = AppService.createApiService(DoctorClient.class, AppInterface.ENDPOINT);
        doctorHttpClient = ServiceGenerator.createApiService(DoctorClient.class);
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

    @Override
    public void loadAllDoctors() {

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
    public void loadDoctorsByName(String partialDoctorName) {

        doctorHttpClient.getAllDoctorsToHospitalByName(partialDoctorName, 1000)
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
            List<HospitalsToDoctor> doctorList= new ArrayList<>();
            if(null == query || query.isEmpty()){

            }else{
                query = query.toLowerCase();

                for (HospitalsToDoctor doctor : doctors) {
                    String fullName =
                            doctor.getFullName() != null ? doctor.getFullName().toLowerCase() : "";
                    String specialization =
                            doctor.getSpecDesc() != null ? doctor.getSpecDesc().toLowerCase() : "";

                    if (fullName.contains(query) || specialization.contains(query)) {
                        doctorList.add(doctor);
                    }
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
