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
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;

import mehdi.sakout.fancybuttons.FancyButton;
import model.CitiesAdapter;
import model.ProvincesAdapter;
import model.SpecsAdapter;
import utilities.Constant;

/**
 * Created by mpx-pawpaw on 1/26/17.
 */

public class DoctorSortRetrieve {

    Context context;
    Dialog dialog;
    DoctorSortInterface callback;

    public DoctorSortRetrieve(Context context, DoctorSortInterface callback) {
        this.context = context;
        this.callback = callback;
    }

    public void showSortBy() {


        FancyButton doctor_family;
        FancyButton tv_specialization;
        FancyButton tv_room;
        FancyButton tv_close;


        dialog = new Dialog(context);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.setContentView(R.layout.dialog_sortby_doctors);

        doctor_family = (FancyButton) dialog.findViewById(R.id.doctor_family);
        tv_specialization = (FancyButton) dialog.findViewById(R.id.tv_specialization);
        tv_room = (FancyButton) dialog.findViewById(R.id.tv_room);
        tv_close = (FancyButton) dialog.findViewById(R.id.tv_close);

        doctor_family.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                callback.onSortListener(context.getString(R.string.doctor_family));
                dialog.dismiss();
            }
        });
        tv_specialization.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                callback.onSortListener(context.getString(R.string.specialization));
                dialog.dismiss();
            }
        });
        tv_room.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                callback.onSortListener(context.getString(R.string.room_number));
                dialog.dismiss();
            }
        });

        tv_close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });

        dialog.show();

        DisplayMetrics metrics = this.context.getResources().getDisplayMetrics();
        int width = metrics.widthPixels;
        dialog.getWindow().setLayout(width, Toolbar.LayoutParams.WRAP_CONTENT);


    }

    public void setMessage(int leftPinIndex, TextView tv_left) {


        tv_left.setText(setMessageData(String.valueOf(leftPinIndex)));
//

    }

    public String setMessageData(String s) {
        String data = "";


        if (s.equals("0")) {
            data = "12:00 AM";
        } else if (s.equals("1")) {
            data = "1:00 AM";
        } else if (s.equals("2")) {
            data = "2:00 AM";
        } else if (s.equals("3")) {
            data = "3:00 AM";
        } else if (s.equals("4")) {
            data = "4:00 AM";
        } else if (s.equals("5")) {
            data = "5:00 AM";
        } else if (s.equals("6")) {
            data = "6:00 AM";
        } else if (s.equals("7")) {
            data = "7:00 AM";
        } else if (s.equals("8")) {
            data = "8:00 AM";
        } else if (s.equals("9")) {
            data = "9:00 AM";
        } else if (s.equals("10")) {
            data = "10:00 AM";
        } else if (s.equals("11")) {
            data = "11:00 AM";
        } else if (s.equals("12")) {
            data = "12:00 PM";
        } else if (s.equals("13")) {
            data = "1:00 PM";
        } else if (s.equals("14")) {
            data = "2:00 PM";
        } else if (s.equals("15")) {
            data = "3:00 PM";
        } else if (s.equals("16")) {
            data = "4:00 PM";
        } else if (s.equals("17")) {
            data = "5:00 PM";
        } else if (s.equals("18")) {
            data = "6:00 PM";
        } else if (s.equals("19")) {
            data = "7:00 PM";
        } else if (s.equals("20")) {
            data = "8:00 PM";
        } else if (s.equals("21")) {
            data = "9:00 PM";
        } else if (s.equals("22")) {
            data = "10:00 PM";
        } else if (s.equals("23")) {
            data = "11:00 PM";
        } else if (s.equals("24")) {
            data = "11:59 PM";
        }


        return data;

    }


    public void setSortBy(TextView tv_sort_by) {

        tv_sort_by.setText(context.getString(R.string.doctor_family));
    }

    public void setSpecText(TextView tv_specialization, ArrayList<SpecsAdapter> selected) {


        String data = "";
        if (selected.size() != 0) {
            for (int x = 0; x < selected.size(); x++) {
                data = data + selected.get(x).getSpecializationDescription().trim() + " , ";
            }
            Log.d("CITY_SEL", data);
            tv_specialization.setText(data.substring(0, data.length() - 3));
        } else {
            tv_specialization.setText(Constant.ALL_SPECIALIZATION);
        }


    }

    public void setPrevDataSortAndSearch(String search_string, String sort_by, TextView tv_sort_by, EditText et_search) {

        if (sort_by.equals("")) {
            setSortBy(tv_sort_by);
        } else {
            tv_sort_by.setText(sort_by);
        }


        et_search.setText(search_string);

    }

    public void setRoom(String room_number, EditText et_room_number) {

        et_room_number.setText(room_number);
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

    public void setProvinceText(TextView tv_province, ArrayList<ProvincesAdapter> selectedProvince) {

        String data = "";
        if (selectedProvince.size() != 0) {
            for (int x = 0; x < selectedProvince.size(); x++) {
                data = data + selectedProvince.get(x).getProvinceName().trim() + ",";
            }
            tv_province.setText(data.substring(0, data.length() - 1));
        } else {
            tv_province.setText(Constant.ALL_PROVINCES);
        }

    }

    public void updateCityList(ArrayList<CitiesAdapter> selectedCity, ArrayList<ProvincesAdapter> selectedProvince, TextView tv_city) {

        ArrayList<CitiesAdapter> temp = new ArrayList<>();

        for (int city = 0; city < selectedCity.size(); city++) {
            for (int prov = 0; prov < selectedProvince.size(); prov++) {
                if (selectedCity.get(city).getProvinceCode().equals(selectedProvince.get(prov).getProvinceCode())) {
                    temp.add(selectedCity.get(city));
                }
            }
        }

        selectedCity.clear();
        selectedCity.addAll(temp);

        setCityText(tv_city, selectedCity);

    }

    public void setResetCity(TextView tv_city, ArrayList<CitiesAdapter> selectedCity, EditText et_search) {
        tv_city.setText(Constant.ALL_CITIES);
        selectedCity.clear();
        et_search.setText("");
    }

    public void setResetProvince(TextView tv_province, ArrayList<ProvincesAdapter> selectedProvince) {
        tv_province.setText(Constant.ALL_PROVINCES);
        selectedProvince.clear();
    }
}
