package InterfaceService;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.View;

import java.util.ArrayList;
import java.util.Collection;

import Sqlite.DatabaseHandler;
import adapter.SpecializationAdapter;
import mehdi.sakout.fancybuttons.FancyButton;
import model.Cities;
import model.CitiesAdapter;
import model.Province;
import model.Provinces;
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


    public ArrayList<Provinces> setArrayData(DatabaseHandler databaseHandler) {
        return databaseHandler.retrieveProvince();

    }

    public ArrayList<CitiesAdapter> setArrayCity(DatabaseHandler databaseHandler, String provinceCode) {
        return databaseHandler.retrieveCity(provinceCode);
    }


    public boolean testOriginFromCity(String data) {
        return (data.equals(Constant.SELECT_CITY));
    }

    public boolean testOriginFromSpecialization(String origin) {
        return (origin.equals(Constant.SELECT_SPECIALIZATION));
    }


    public void setOkVISIBILITY(boolean b, boolean testOriginFromSpecialization, FancyButton btn_ok) {


        btn_ok.setVisibility((b||testOriginFromSpecialization) ? View.VISIBLE : View.GONE);
    }

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


    public ArrayList<SpecsAdapter> setArraySpecs(DatabaseHandler handler) {
        return handler.retrieveSpecs();
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
}
