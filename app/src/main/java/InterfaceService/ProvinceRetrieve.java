package InterfaceService;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.View;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;


import Sqlite.DatabaseHandler;
import adapter.ProvinceAdapter;
import adapter.SpecializationAdapter;
import mehdi.sakout.fancybuttons.FancyButton;
import model.Cities;
import model.CitiesAdapter;
import model.GetDoctorsToHospital;
import model.HospitalList;
import model.Loa;
import model.LoaFetch;
import model.Province;
import model.Provinces;
import model.ProvincesAdapter;
import model.SimpleData;
import model.SpecsAdapter;
import utilities.Constant;
import utilities.SharedPref;

/**
 * Created by mpx-pawpaw on 1/25/17.
 */

public class ProvinceRetrieve {

    Context context;

    public ProvinceRetrieve(Context context) {
        this.context = context;
    }


    public ArrayList<ProvincesAdapter> setArrayData(DatabaseHandler databaseHandler, String SPEC_SEARCH) {
        return databaseHandler.retrieveProvince(SPEC_SEARCH);

    }

    public ArrayList<CitiesAdapter> setArrayCity(DatabaseHandler databaseHandler, ArrayList<ProvincesAdapter> prevSelectedProvince, String SPEC_SEARCH) {
        return databaseHandler.retrieveCity(prevSelectedProvince, SPEC_SEARCH);
    }


    public boolean testOriginFromCity(String data) {
        return (data.equals(Constant.SELECT_CITY));
    }

    public boolean testOriginFromDoctors(String origin) {
        return (origin.equals(Constant.SELECT_DOCTOR));
    }

    public boolean testOriginFromSpecialization(String origin) {
        return (origin.equals(Constant.SELECT_SPECIALIZATION));
    }

    public boolean testOriginFromProvince(String origin) {
        return (origin.equals(Constant.SELECT_PROVINCE));
    }


    public boolean testOriginFromLoaReq(String origin) {
        return (origin.equals(Constant.SELECT_LOAREQUEST));
    }

    public void setOkVISIBILITY(boolean b, boolean testOriginFromSpecialization, FancyButton btn_ok) {
        btn_ok.setVisibility((b || testOriginFromSpecialization) ? View.VISIBLE : View.GONE);
    }

    //GIVE PREVSELECTED TO SLECTED ARRAY
    public void setSelectedData(ArrayList<CitiesAdapter> prevSelectedCity, ArrayList<CitiesAdapter> arrayCity, ArrayList<CitiesAdapter> selected) {

        if (prevSelectedCity.size() != 0) {

            for (int sel = 0; sel < prevSelectedCity.size(); sel++) {
                for (int data = 0; data < arrayCity.size(); data++) {
                    if (prevSelectedCity.get(sel).getCityCode().equals(arrayCity.get(data).getCityCode())) {
                        arrayCity.get(data).setSelected("true");
                        Log.d("sel_change", arrayCity.get(data).getSelected());
                        selected.add(prevSelectedCity.get(sel));
                    }
                }
            }
        }


    }


    public ArrayList<SpecsAdapter> setArraySpecs(DatabaseHandler handler, String SPEC_SEARCH) {
        return handler.retrieveSpecs(SPEC_SEARCH);
    }

    public void setSelectedDataSpecs(ArrayList<SpecsAdapter> prevSelectedSpecialization, ArrayList<SpecsAdapter> arraySpecialization, ArrayList<SpecsAdapter> selectedSpecialization) {

        if (prevSelectedSpecialization.size() != 0) {

            for (int sel = 0; sel < prevSelectedSpecialization.size(); sel++) {
                for (int data = 0; data < arraySpecialization.size(); data++) {
                    if (prevSelectedSpecialization.get(sel).getSpecializationCode().equals(arraySpecialization.get(data).getSpecializationCode())) {
                        arraySpecialization.get(data).setSelected("true");
                        Log.d("sel_change", arraySpecialization.get(data).getSelected());
                        selectedSpecialization.add(prevSelectedSpecialization.get(sel));
                    }
                }
            }
        }
    }

    // GET ONLY ITEM WITHOUT REPETITION
    public ArrayList<SimpleData> getOnlyHospitalWithOneCount(ArrayList<LoaFetch> arrayListMaster) {


        ArrayList<SimpleData> array = new ArrayList<>();
        ArrayList<String> arraySorter = new ArrayList<>();

        for (int mid = 0; mid < arrayListMaster.size(); mid++) {
            arraySorter.add(arrayListMaster.get(mid).getHospitalName());
        }
        Set<String> s = new HashSet<String>(arraySorter);
        Set<String> alpha = new TreeSet<>(s);

        arraySorter.clear();
        arraySorter.addAll(alpha);
        for (int x = 0; x < arraySorter.size(); x++) {
            SimpleData data = new SimpleData();
            data.setHospital(arraySorter.get(x));
            data.setSelected("false");
            array.add(data);
        }


        return array;


    }

    public void tagSelectedToMasterList(ArrayList<SimpleData> prevSelected, ArrayList<SimpleData> arrayHospitals) {
        for (int prev = 0; prev < prevSelected.size(); prev++) {
            if (prevSelected.get(prev).getSelected().equals("true")) {
                arrayHospitals.get(prev).setSelected("true");
            }
        }

    }

    public void tagSelectedToMasterList(ArrayList<SimpleData> prevSelected, List<SimpleData> arrayHospitals) {
        for (int prev = 0; prev < prevSelected.size(); prev++) {

            if (prevSelected.get(prev).getSelected().equals("true")) {
                arrayHospitals.get(prev).setSelected("true");
            }
        }

    }


    public ArrayList<SimpleData> getOnlyDoctorWithOneCount(ArrayList<SimpleData> hosp, DatabaseHandler handler) {

        ArrayList<LoaFetch> dataLOA = new ArrayList<>();
        dataLOA.addAll(handler.retrieveHospital(hosp));

        Log.d("DATA_LOA", dataLOA.toString());

        ArrayList<LoaFetch> dataDoc = new ArrayList<>();
        dataDoc.addAll(handler.retrieveDoctor(dataLOA));

        Log.d("DATA_LOA2", dataDoc.toString());

        ArrayList<SimpleData> array = new ArrayList<>();
        ArrayList<String> arraySorter = new ArrayList<>();

        for (int mid = 0; mid < dataDoc.size(); mid++) {
            arraySorter.add(dataDoc.get(mid).getDoctorName());
        }
        Set<String> s = new HashSet<String>(arraySorter);
        Set<String> alpha = new TreeSet<>(s);


        arraySorter.clear();
        arraySorter.addAll(alpha);
        for (int x = 0; x < arraySorter.size(); x++) {
            SimpleData data = new SimpleData();
            data.setHospital(arraySorter.get(x));
            data.setSelected("false");
            array.add(data);
        }

        return array;


    }

    public void setSelectedData(ArrayList<CitiesAdapter> arrayCity, ArrayList<CitiesAdapter> selectedCity) {


        if (selectedCity.size() != 0) {

            for (int sel = 0; sel < selectedCity.size(); sel++) {
                for (int data = 0; data < arrayCity.size(); data++) {
                    if (selectedCity.get(sel).getCityCode().equals(arrayCity.get(data).getCityCode())) {
                        arrayCity.get(data).setSelected("true");
                        Log.d("sel_change", arrayCity.get(data).getSelected());
                    }
                }
            }
        }


    }

    public void setSelectedProvinceData(ArrayList<ProvincesAdapter> prevSelectedProvince, ArrayList<ProvincesAdapter> arrayProvince, ArrayList<ProvincesAdapter> selectedProvince) {


        if (prevSelectedProvince.size() != 0) {

            for (int sel = 0; sel < prevSelectedProvince.size(); sel++) {
                for (int data = 0; data < arrayProvince.size(); data++) {
                    if (prevSelectedProvince.get(sel).getProvinceCode().equals(arrayProvince.get(data).getProvinceCode())) {
                        arrayProvince.get(data).setSelected("true");
                        Log.d("sel_change", arrayProvince.get(data).getSelected());
                        selectedProvince.add(prevSelectedProvince.get(sel));
                    }
                }
            }
        }


    }

    public void setSelectedDataProvince(ArrayList<ProvincesAdapter> arrayProvince, ArrayList<ProvincesAdapter> selectedProvince) {


        if (selectedProvince.size() != 0) {

            for (int sel = 0; sel < selectedProvince.size(); sel++) {
                for (int data = 0; data < arrayProvince.size(); data++) {
                    if (selectedProvince.get(sel).getProvinceCode().equals(arrayProvince.get(data).getProvinceCode())) {
                        arrayProvince.get(data).setSelected("true");
                        Log.d("sel_change", arrayProvince.get(data).getSelected());
                    }
                }
            }
        }


    }
}