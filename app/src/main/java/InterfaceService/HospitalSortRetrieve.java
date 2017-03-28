package InterfaceService;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import com.medicard.member.R;
import android.support.v7.widget.Toolbar;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;

import mehdi.sakout.fancybuttons.FancyButton;
import model.CitiesAdapter;
import model.Provinces;
import utilities.Constant;
import utilities.SharedPref;

/**
 * Created by mpx-pawpaw on 1/24/17.
 */

public class HospitalSortRetrieve implements View.OnClickListener {
    private Context context;
    private HospitalSortInterface callback;
    private Dialog dialog;

    public HospitalSortRetrieve(Context context, HospitalSortInterface callback) {
        this.context = context;
        this.callback = callback;

    }

    public void showSortBy() {


        FancyButton hospital_clinic;
        FancyButton med_clinic_first;
        FancyButton city;
        FancyButton province;
        FancyButton cancel;


        dialog = new Dialog(context);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.setContentView(R.layout.dialog_sortby);

        cancel = (FancyButton) dialog.findViewById(R.id.cancel);
        hospital_clinic = (FancyButton) dialog.findViewById(R.id.hospital_clinic);
        med_clinic_first = (FancyButton) dialog.findViewById(R.id.med_clinic_first);
        province = (FancyButton) dialog.findViewById(R.id.province);
        city = (FancyButton) dialog.findViewById(R.id.city);

        hospital_clinic.setOnClickListener(this);
        med_clinic_first.setOnClickListener(this);
        province.setOnClickListener(this);
        city.setOnClickListener(this);
        cancel.setOnClickListener(this);

        dialog.show();

        DisplayMetrics metrics = this.context.getResources().getDisplayMetrics();
        int width = metrics.widthPixels;
        dialog.getWindow().setLayout(width, Toolbar.LayoutParams.WRAP_CONTENT);
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.hospital_clinic:
                setSortBy(0);
                dialog.dismiss();
                break;

            case R.id.med_clinic_first:
                setSortBy(1);
                dialog.dismiss();
                break;

            case R.id.province:
                setSortBy(2);
                dialog.dismiss();
                break;

            case R.id.city:
                setSortBy(3);
                dialog.dismiss();
                break;
            case R.id.cancel:
                dialog.dismiss();
                break;


        }
    }


    private void setSortBy(int data) {
        String stringData = "";

        if (data == 0) {
            stringData = context.getString(R.string.hospital_clinic_name);
        } else if (data == 1) {
            stringData = context.getString(R.string.medicard_first);
        } else if (data == 2) {
            stringData = context.getString(R.string.province);
        } else if (data == 3) {
            stringData = context.getString(R.string.city);
        }

        callback.onSortByListener(stringData);

    }


    public void setCityText(TextView tv_city, ArrayList<CitiesAdapter> selected) {

        String data = "";
        if (selected.size() != 0) {
            for (int x = 0; x < selected.size(); x++) {
                data = data + selected.get(x).getCityName().trim() + ",";
            }
            Log.d("CITY_SEL", data);
            tv_city.setText(data.substring(0, data.length() - 1));
        } else {
            tv_city.setText(Constant.ALL_CITIES);
        }

    }

    public void setProvinceText(TextView tv_province, ArrayList<Provinces> selectedProvince) {

        if (selectedProvince.size() != 0) {

            tv_province.setText(selectedProvince.get(0).getProvinceName().trim());

        } else {
            tv_province.setText(Constant.ALL_PROVINCES);
        }

    }

    public void saveProvinceCode(ArrayList<Provinces> selectedProvince, String PROVINCE_CODE) {
        PROVINCE_CODE = selectedProvince.get(0).getProvinceCode();
        SharedPref.setStringValue(SharedPref.USER, SharedPref.PROVINCE_CODE, selectedProvince.get(0).getProvinceCode(), context);
    }

    public void resetDetails(TextView tv_province, TextView tv_city, TextView tv_sort_by, ArrayList<Provinces> selectedProvince, ArrayList<Provinces> province) {
        SharedPref.setStringValue(SharedPref.USER, SharedPref.PROVINCE_CODE, "NONE", context);
        tv_city.setText(Constant.ALL_CITIES);
        tv_province.setText(Constant.ALL_PROVINCES);
        tv_sort_by.setText(context.getString(R.string.medicard_first));

        selectedProvince.clear();
        province.clear();

    }

    public void setResetCity(TextView tv_city, ArrayList<CitiesAdapter> selectedCity, EditText et_search) {
        tv_city.setText(Constant.ALL_CITIES);
        selectedCity.clear();
        et_search.setText("");

    }



    public String getChecked(CheckBox cb_med_clinic) {

        return cb_med_clinic.isChecked() ? "true" : "false" ;

    }

    public void setCheckBox(CheckBox cb_med_clinic, String stringExtra) {

        if (stringExtra.equals("true"))
            cb_med_clinic.setChecked(true);
        else
            cb_med_clinic.setChecked(false);
    }
}
