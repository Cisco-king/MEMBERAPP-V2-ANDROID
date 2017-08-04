package com.medicard.member.module.hospital.fragment;

import android.content.Context;

import com.medicard.member.module.hospital.fragment.HospitalMvp;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import database.dao.HospitalDao;
import database.entity.Doctor;
import model.HospitalList;
import timber.log.Timber;

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

    @Override
    public void filterHospitals(List<HospitalList> hospitals, String query) {
        try {
            query = query.toLowerCase();
            List<HospitalList> hospitalFilteredResults = new ArrayList<>();
            for (HospitalList hospital : hospitals) {
                String hospitalName =
                        hospital.getHospitalName() != null ? hospital.getHospitalName().toLowerCase() : "";
                if (hospitalName.contains(query)) {
                    hospitalFilteredResults.add(hospital);
                }
            }

            hospitalView.displayFilterHospitalClinics(hospitalFilteredResults);
        } catch (Exception e) {
            Timber.d("error message %s", e.toString());
        }
    }

    @Override
    public void loadHospitalClinic() {
        List<HospitalList> hospitals = hospitalDao.findAll();
        List<HospitalList> medicardList = new ArrayList<>();
        List<HospitalList> noHospitalName = new ArrayList<>();
        List<HospitalList> otherList = new ArrayList<>();
        List<HospitalList> finalList = new ArrayList<>();

        for (HospitalList hospital : hospitals) {
            if(hospital.getHospitalName().contains("MEDICard")){
                medicardList.add(hospital);
            }else{
                if(hospital.getHospitalName().isEmpty() && hospital.getHospitalName().length() ==0){
                    noHospitalName.add(hospital);
                }else{
                    otherList.add(hospital);
                }
            }
        }
        this.sortHospitals(medicardList);
        this.sortHospitals(otherList);
        finalList.addAll(medicardList);
        finalList.addAll(otherList);
//        finalList.addAll(medicardList);


        Timber.d("total number of hospital %s", hospitals.size());
        hospitalView.displayHospitalClinic(finalList);
    }

    public List<HospitalList> sortHospitals(List<HospitalList> toBeSorted){
        if(!toBeSorted.isEmpty()){
            Collections.sort(toBeSorted, new Comparator<HospitalList>() {
                @Override
                public int compare(HospitalList o1, HospitalList o2) {
                    return o1.getHospitalName().compareToIgnoreCase(o2.getHospitalName());
                }
            });
        }
        return toBeSorted;
    }

}
